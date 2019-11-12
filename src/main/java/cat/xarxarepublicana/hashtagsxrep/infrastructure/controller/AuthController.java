package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.signin.ConnectTwitterCallbackUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.signin.SignInWithTwitterUse;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

  private static final Logger LOG = Logger.getLogger(AuthController.class.getName());

  private final SignInWithTwitterUse signInWithTwitterUse;
  private final ConnectTwitterCallbackUseCase connectTwitterCallbackUseCase;

  @Autowired
  public AuthController(
      SignInWithTwitterUse signInWithTwitterUse,
      ConnectTwitterCallbackUseCase connectTwitterCallbackUseCase) {
    this.signInWithTwitterUse = signInWithTwitterUse;
    this.connectTwitterCallbackUseCase = connectTwitterCallbackUseCase;
  }

  @GetMapping(Views.URL_LOGIN_TWITTER)
  public RedirectView loginTwitter() throws Exception {
    SignInWithTwitterUse.SignInWithTwitterResponse signInWithTwitterResponse = signInWithTwitterUse.signInWithTwitter();
    return new RedirectView(signInWithTwitterResponse.getRedirectTo());
  }

  @GetMapping(Views.URL_CONNECT_TWITTER)
  public RedirectView connectTwitter(
      HttpServletResponse response,
      @RequestParam(value = "oauth_token", required = false) String oauthToken,
      @RequestParam(value = "oauth_verifier", required = false) String oauthVerifier,
      @RequestParam(value = "denied", required = false) String denied) {
    ConnectTwitterCallbackUseCase.ConnectResponse connectResponse =
        connectTwitterCallbackUseCase.connect(response, oauthToken, oauthVerifier, denied);
    return new RedirectView(connectResponse.getRedirectTo());
  }

  @GetMapping(Views.URL_LOGIN)
  public String login() {
    return "login";
  }
}
