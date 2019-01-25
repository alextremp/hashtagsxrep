package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.login.LoginWithTwitterUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.login.TwitterConnectUseCase;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.http.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.RedirectView;
import reactor.core.publisher.Mono;

@Controller
public class AuthenticationController {

    private final LoginWithTwitterUseCase loginWithTwitterUseCase;
    private final TwitterConnectUseCase twitterConnectUseCase;
    private final CookieService cookieService;

    @Autowired
    public AuthenticationController(LoginWithTwitterUseCase loginWithTwitterUseCase, TwitterConnectUseCase twitterConnectUseCase, CookieService cookieService) {
        this.loginWithTwitterUseCase = loginWithTwitterUseCase;
        this.twitterConnectUseCase = twitterConnectUseCase;
        this.cookieService = cookieService;
    }

    @GetMapping("/login")
    public Mono<String> login() {
        return Mono.just("login");
    }

    @GetMapping("/login/twitter")
    public Mono<String> loginTwitter() {
        return loginWithTwitterUseCase.getAuthorizationUrl()
                .map(this::redirect);
    }

    @GetMapping("/connect/twitter")
    public Mono<String> connectTwitter(
            ServerHttpResponse response,
            @RequestParam(value = "oauth_token", required = false) String oauthToken,
            @RequestParam(value = "oauth_verifier", required = false) String oauthVerifier,
            @RequestParam(value = "denied", required = false) String denied) {
        return twitterConnectUseCase.twitterConnect(oauthToken, oauthVerifier, denied)
                .zipWhen(x -> cookieService.save(oauthToken))
                .map(zipped -> {
                    response.addCookie(zipped.getT2());
                    return zipped.getT1();
                })
                .log()
                .map(this::redirect);
    }

    private String redirect(String url) {
        return "redirect:" + url;
    }

}
