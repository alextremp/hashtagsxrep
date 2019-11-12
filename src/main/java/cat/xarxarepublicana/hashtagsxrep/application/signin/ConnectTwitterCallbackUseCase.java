package cat.xarxarepublicana.hashtagsxrep.application.signin;

import cat.xarxarepublicana.hashtagsxrep.application.signin.io.ConnectTwitterCallbackRequest;
import cat.xarxarepublicana.hashtagsxrep.application.signin.io.ConnectTwitterCallbackResponse;
import cat.xarxarepublicana.hashtagsxrep.domain.core.error.LoginDeniedError;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthCookieService;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthToken;
import java.time.LocalDateTime;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Mono;

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

  public Mono<ConnectTwitterCallbackResponse> execute(ConnectTwitterCallbackRequest request) {
    return Mono.fromCallable(() -> StringUtils.isEmpty(request.getDenied()))
        .flatMap(allowed -> allowed
            ? authenticationToken(request.getOauthToken(), request.getOauthVerifier())
            : Mono.error(new LoginDeniedError(request.getDenied())))
        .map(authCookieService::serialize)
        .map(serializedToken -> new ConnectTwitterCallbackResponse().setAuthenticationToken(serializedToken));
  }

  private Mono<AuthToken> authenticationToken(String oauthToken, String oauthVerifier) {
    return Mono.fromCallable(() -> twitterRepository.verifyCredentials(oauthToken, oauthVerifier))
        .doOnNext(user -> {
          user.updateSignedInDate(LocalDateTime.now());
          userRepository.saveLoggedUser(user);
        })
        .map(user -> new AuthToken(user.getId(), user.getNickname(), user.getToken()));
  }
}
