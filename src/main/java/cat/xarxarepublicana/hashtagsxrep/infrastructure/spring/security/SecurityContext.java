package cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class SecurityContext {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityContext.class);

    private static final int COOKIE_MAX_AGE = 2 * 30 * 24 * 3600; //two months
    private static final String COOKIE_LOGOUT = "_"; //support for browser versions not deleting the cookie

    private static final String USER_ID_CLAIM = "userId";

    private UserDetailsService userDetailsService;
    private String cookieName;
    private String secret;

    public SecurityContext(UserDetailsService userDetailsService, String cookieName, String secret) {
        this.userDetailsService = userDetailsService;
        this.cookieName = cookieName;
        this.secret = secret;
    }

    public void restoreSecurityContext(HttpServletRequest request) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            String authToken = getRequestToken(request);
            if (authToken != null) {
                Claims tokenClaims = extractTokenClaims(authToken);
                String username = tokenClaims.getSubject();
                if (username != null) {
                    String userId = tokenClaims.get(USER_ID_CLAIM, String.class);
                    UserAuth userAuth = null;

                    try {
                        userAuth = (UserAuth) userDetailsService.loadUserByUsername(userId);
                    } catch (UsernameNotFoundException e) {
                        LOG.warn(String.format(">>> bad user token, username [%s] not found. id [%s]", username, userId));
                    }

                    if (userAuth != null) {
                        if (username.equals(userAuth.getUsername())) {
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userAuth, null, userAuth.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                            SecurityContextHolder.getContext().setAuthentication(authentication);

                        } else {
                            LOG.warn(String.format(">>> bad user token, username [%s] not related to userId [%s]", username, userId));
                        }
                    }
                }
            }
        }
    }



    public void put(UserAuth userAuth, HttpServletResponse response) {
        String token = generateToken(userAuth);
        Cookie apiCookie = new Cookie(cookieName, token);
        apiCookie.setHttpOnly(true);
        apiCookie.setMaxAge(COOKIE_MAX_AGE);
        apiCookie.setPath("/");
        response.addCookie(apiCookie);
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookieName.equals(cookie.getName())) {
                    Cookie apiCookie = new Cookie(cookieName, COOKIE_LOGOUT);
                    apiCookie.setHttpOnly(true);
                    apiCookie.setMaxAge(0);
                    apiCookie.setPath("/");
                    response.addCookie(apiCookie);
                }
            }
        }
    }

    private Claims extractTokenClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private String generateToken(UserAuth userAuth) {
        Claims claims = Jwts.claims().setSubject(userAuth.getUsername());
        claims.put(USER_ID_CLAIM, userAuth.getId());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private String getRequestToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if (cookieName.equals(c.getName()) && !COOKIE_LOGOUT.equals(c.getValue())) {
                    return c.getValue();
                }
            }
        }
        return null;
    }

}
