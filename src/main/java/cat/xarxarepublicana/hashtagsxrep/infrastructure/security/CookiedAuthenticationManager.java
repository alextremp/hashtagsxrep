package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CookiedAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.fromCallable(() -> authentication.getClass())
                .log()
                .flatMap(authenticationClass -> AuthenticationUser.class.isAssignableFrom(authenticationClass)
                        ? Mono.just(authentication)
                        : Mono.empty());
    }

}
