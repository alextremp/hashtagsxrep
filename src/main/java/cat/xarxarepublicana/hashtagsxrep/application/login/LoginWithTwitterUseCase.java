package cat.xarxarepublicana.hashtagsxrep.application.login;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class LoginWithTwitterUseCase {

    private final TwitterRepository twitterRepository;

    @Autowired
    public LoginWithTwitterUseCase(TwitterRepository twitterRepository) {
        this.twitterRepository = twitterRepository;
    }

    public Mono<String> getAuthorizationUrl() {
        return twitterRepository.getAuthorizationUrl();
    }
}
