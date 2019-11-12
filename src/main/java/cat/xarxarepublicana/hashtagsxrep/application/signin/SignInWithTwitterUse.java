package cat.xarxarepublicana.hashtagsxrep.application.signin;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;

public class SignInWithTwitterUse {

  private final TwitterRepository twitterRepository;

  public SignInWithTwitterUse(TwitterRepository twitterRepository) {
    this.twitterRepository = twitterRepository;
  }

  public SignInWithTwitterResponse signInWithTwitter() {
    String redirectTo = twitterRepository.getAuthorizationUrl();
    return new SignInWithTwitterResponse(redirectTo);
  }

  public class SignInWithTwitterResponse {
    private final String redirectTo;

    private SignInWithTwitterResponse(String redirectTo) {
      this.redirectTo = redirectTo;
    }

    public String getRedirectTo() {
      return redirectTo;
    }
  }
}
