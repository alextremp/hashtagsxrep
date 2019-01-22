package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.signin.ConnectTwitterCallbackUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.signin.SignInWithTwitterUse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Controller
public class AuthController {

    private static final Logger LOG = Logger.getLogger(AuthController.class.getName());

    private final SignInWithTwitterUse signInWithTwitterUse;

    @Autowired
    public AuthController(SignInWithTwitterUse signInWithTwitterUse) {
        this.signInWithTwitterUse = signInWithTwitterUse;
    }

    @GetMapping(Views.URL_LOGIN_TWITTER)
    public Mono<String> loginTwitter() {
        return signInWithTwitterUse.signInWithTwitter()
                .map(path -> "redirect:" + path);
    }

    @GetMapping(Views.URL_CONNECT_TWITTER)
    public Mono<String> connectTwitter(
            @RequestParam(value = "oauth_token", required = false) String oauthToken,
            @RequestParam(value = "oauth_verifier", required = false) String oauthVerifier,
            @RequestParam(value = "denied", required = false) String denied) {
        //ConnectTwitterCallbackUseCase.ConnectResponse connectResponse = connectTwitterCallbackUseCase.connect(response, oauthToken, oauthVerifier, denied);
        return Mono.just("redirect:/");
    }

    @GetMapping(Views.URL_LOGIN)
    public String login() {
        return "login";
    }
}
