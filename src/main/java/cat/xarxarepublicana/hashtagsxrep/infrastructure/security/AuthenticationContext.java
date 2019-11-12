package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class AuthenticationContext {

  private static final Logger LOG = LoggerFactory.getLogger(AuthenticationContext.class);

  private final UserDetailsService userDetailsService;
  private final AuthCookieService authCookieService;

  public AuthenticationContext(UserDetailsService userDetailsService, AuthCookieService authCookieService) {
    this.userDetailsService = userDetailsService;
    this.authCookieService = authCookieService;
  }

  public void restoreSecurityContext(HttpServletRequest request, HttpServletResponse response) {
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      AuthToken authToken = authCookieService.extractAuthToken(request);
      if (authToken != null) {
        AuthenticationUser authenticationUser = null;
        try {
          authenticationUser = (AuthenticationUser) userDetailsService.loadUserByUsername(authToken.getUserId());
        } catch (UsernameNotFoundException e) {
          LOG.warn(String.format(
              ">>> bad user token, username [%s] not found. id [%s]",
              authToken.getUsername(),
              authToken.getUserId()));
        }

        if (authenticationUser != null && authToken.getUserToken() != null) {
          if (StringUtils.equals(authToken.getUsername(), authenticationUser.getUsername()) && StringUtils.equals(
              authToken.getUserToken(),
              authenticationUser.getPublicToken())) {
            UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(authenticationUser, null, authenticationUser.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            authCookieService.putAuthToken(authToken, response);
          } else {
            LOG.warn(String.format(
                ">>> bad user token, username [%s] not related to userId [%s]",
                authToken.getUsername(),
                authToken.getUserId()));
          }
        }
      }
    }
  }
}
