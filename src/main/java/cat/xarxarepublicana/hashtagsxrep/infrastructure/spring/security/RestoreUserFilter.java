package cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RestoreUserFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private SecurityContext securityContext;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        securityContext.restoreSecurityContext((HttpServletRequest) request);
        chain.doFilter(request, response);
    }
}
