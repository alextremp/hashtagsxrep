package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.http.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@Primary
public class CookiedSecurityContextRepository implements ServerSecurityContextRepository {

    private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    private final CookieService cookieService;

    @Autowired
    public CookiedSecurityContextRepository(ReactiveAuthenticationManager reactiveAuthenticationManager, CookieService cookieService) {
        this.reactiveAuthenticationManager = reactiveAuthenticationManager;
        this.cookieService = cookieService;
    }

    @Override
    public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        return Mono.fromCallable(() -> serverWebExchange.getRequest().getCookies())
                .flatMap(cookieService::extract)
                .flatMap(this::restore)
                .flatMap(reactiveAuthenticationManager::authenticate)
                .map(SecurityContextImpl::new);
    }

    private Mono<Authentication> restore(String token) {
        return Mono.just(new AuthenticationUser(new User("-1", "auto", "test", token, token, "CREATOR", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 1, 1, "ca", null, null, true, false)));
    }

}