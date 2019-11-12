package cat.xarxarepublicana.hashtagsxrep.application.signin;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthCookieService;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthToken;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletResponse;

public class ConnectTwitterCallbackUseCase {

  private final UserRepository userRepository;
  private final TwitterRepository twitterRepository;
  private final AuthCookieService authCookieService;

  public ConnectTwitterCallbackUseCase(
      UserRepository userRepository,
      TwitterRepository twitterRepository,
      AuthCookieService authCookieService) {
    this.userRepository = userRepository;
    this.twitterRepository = twitterRepository;
    this.authCookieService = authCookieService;
  }

  public ConnectResponse connect(HttpServletResponse response, String oauthToken, String oauthVerifier, String denied) {
    if (denied != null) {
      return new ConnectResponse(Views.URL_LOGIN);
    }
    User user = twitterRepository.verifyCredentials(oauthToken, oauthVerifier);
    user.updateSignedInDate(LocalDateTime.now());
    userRepository.saveLoggedUser(user);

    AuthToken authToken = new AuthToken(user.getId(), user.getNickname(), user.getToken());
    authCookieService.putAuthToken(authToken, response);
    return new ConnectResponse(Views.URL_INDEX);
  }

  public static class ConnectResponse {
    private final String redirectTo;

    public ConnectResponse(String redirectTo) {
      this.redirectTo = redirectTo;
    }

    public String getRedirectTo() {
      return redirectTo;
    }
  }
}
