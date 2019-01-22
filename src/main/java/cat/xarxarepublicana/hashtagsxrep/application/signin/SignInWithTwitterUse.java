package cat.xarxarepublicana.hashtagsxrep.application.signin;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import reactor.core.publisher.Mono;

public class SignInWithTwitterUse {

    private final TwitterRepository twitterRepository;

    public SignInWithTwitterUse(TwitterRepository twitterRepository) {
        this.twitterRepository = twitterRepository;
    }

    public Mono<String> signInWithTwitter() {
        return Mono.fromCallable(() -> twitterRepository.getAuthorizationUrl());
    }

}
