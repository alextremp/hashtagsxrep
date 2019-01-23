package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public Mono<String> login() {
        return Mono.just("login");
    }
}
