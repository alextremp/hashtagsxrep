package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CookiedSecurityContextRepository implements ServerSecurityContextRepository {

    private final String cookieName;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public CookiedSecurityContextRepository(
            @Value("${app.usertoken.cookiename}") String cookieName,
            AuthenticationManager authenticationManager) {
        this.cookieName = cookieName;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        return Mono.error(new UnsupportedOperationException("Not supported: save"));
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        return Mono.fromCallable(() -> serverWebExchange.getRequest())
                .map(serverHttpRequest -> serverHttpRequest.getCookies())
                .map(cookiesMap -> cookiesMap.getFirst(cookieName))
                .map(httpCookie -> httpCookie.getValue())
                .map(token -> new UsernamePasswordAuthenticationToken(token, token))
                .map(authenticationToken -> authenticationManager.authenticate(authenticationToken))
                .map(authentication -> new SecurityContextImpl(authentication));
    }
}