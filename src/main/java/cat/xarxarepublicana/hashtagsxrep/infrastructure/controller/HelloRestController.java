package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/api/hello")
public class HelloRestController {

    @GetMapping
    @Secured("ROLE_ADMIN")
    public Mono<String> hello(Mono<Principal> principal) {
        return Mono.fromCallable(() -> true)
                .flatMap(x -> principal)
                .cast(AuthenticationUser.class)
                .map(AuthenticationUser::getUser)
                .map(User::getNickname)
                .map(nickname -> "HOLA!! @" + nickname);
    }
}
