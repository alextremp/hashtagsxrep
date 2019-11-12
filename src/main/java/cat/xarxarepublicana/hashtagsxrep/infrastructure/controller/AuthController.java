package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.signin.ConnectTwitterCallbackUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.signin.SignInWithTwitterUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.signin.io.ConnectTwitterCallbackRequest;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthCookieService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import reactor.core.publisher.Mono;

@Controller
public class AuthController {

  private final AuthCookieService authCookieService;
  private final SignInWithTwitterUseCase signInWithTwitterUseCase;
  private final ConnectTwitterCallbackUseCase connectTwitterCallbackUseCase;

  @Autowired
  public AuthController(
      AuthCookieService authCookieService,
      SignInWithTwitterUseCase signInWithTwitterUseCase,
      ConnectTwitterCallbackUseCase connectTwitterCallbackUseCase) {
    this.authCookieService = authCookieService;
    this.signInWithTwitterUseCase = signInWithTwitterUseCase;
    this.connectTwitterCallbackUseCase = connectTwitterCallbackUseCase;
  }

  @GetMapping(Views.URL_LOGIN_TWITTER)
  public Mono<RedirectView> loginTwitter() {
    return signInWithTwitterUseCase.signInWithTwitter()
        .map(useCaseResponse -> new RedirectView(useCaseResponse.getSigninUrl()));
  }

  @GetMapping(Views.URL_CONNECT_TWITTER)
  public Mono<RedirectView> connectTwitter(
      HttpServletResponse response,
      @RequestParam(value = "oauth_token", required = false) String oauthToken,
      @RequestParam(value = "oauth_verifier", required = false) String oauthVerifier,
      @RequestParam(value = "denied", required = false) String denied) {
    return Mono.fromCallable(() -> new ConnectTwitterCallbackRequest(oauthToken, oauthVerifier, denied))
        .flatMap(connectTwitterCallbackUseCase::execute)
        .doOnNext(useCaseResponse -> authCookieService.putAuthToken(useCaseResponse.getAuthenticationToken(), response))
        .map(useCaseResponse -> Views.URL_INDEX)
        .onErrorResume(throwable -> Mono.just(Views.URL_LOGIN + "?message=" + throwable.getMessage()))
        .map(RedirectView::new);
  }

  @GetMapping(Views.URL_LOGIN)
  public String login() {
    return "login";
  }
}
