package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class RestoreUserFilter extends UsernamePasswordAuthenticationFilter {

  @Autowired
  private AuthenticationContext authenticationContext;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    authenticationContext.restoreSecurityContext((HttpServletRequest) request, (HttpServletResponse) response);
    chain.doFilter(request, response);
  }
}
