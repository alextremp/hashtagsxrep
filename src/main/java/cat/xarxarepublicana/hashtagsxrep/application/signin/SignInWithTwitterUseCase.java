package cat.xarxarepublicana.hashtagsxrep.application.signin;

import cat.xarxarepublicana.hashtagsxrep.application.signin.io.SignInWithTwitterResponse;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import reactor.core.publisher.Mono;

public class SignInWithTwitterUseCase {

  private final TwitterRepository twitterRepository;

  public SignInWithTwitterUseCase(TwitterRepository twitterRepository) {
    this.twitterRepository = twitterRepository;
  }

  public Mono<SignInWithTwitterResponse> signInWithTwitter() {
    return twitterRepository.getAuthorizationUrl()
        .map(url -> new SignInWithTwitterResponse().setSigninUrl(url));
  }
}
