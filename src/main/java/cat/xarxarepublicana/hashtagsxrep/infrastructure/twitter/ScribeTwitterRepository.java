package cat.xarxarepublicana.hashtagsxrep.infrastructure.twitter;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.SearchTweetsResult;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterException;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.logging.Logger;

@Repository
public class ScribeTwitterRepository implements TwitterRepository {

    private static final Logger LOG = Logger.getLogger(ScribeTwitterRepository.class.getName());

    private static final String VERIFY_CREDENTIALS = "https://api.twitter.com/1.1/account/verify_credentials.json";
    private static final String SEARCH_TWEETS = "https://api.twitter.com/1.1/search/tweets.json";

    //private final UserFactory userFactory;
    private final OAuth10aService service;
    private final OAuth1AccessToken applicationToken;
    private final ObjectMapper objectMapper;

    @Autowired
    public ScribeTwitterRepository(
            OAuth10aService service,
            OAuth1AccessToken applicationToken,
            ObjectMapper objectMapper) {
        this.service = service;
        this.applicationToken = applicationToken;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<String> getAuthorizationUrl() {
        return Mono.fromCallable(() -> service.getRequestToken())
                .map(service::getAuthorizationUrl)
                .onErrorResume(error -> Mono.error(new TwitterException("Error requesting token", error)));
    }

    @Override
    public Mono<User> verifyCredentials(String oauthToken, String oauthVerifier) {
        return Mono.empty();
    }

    @Override
    public Mono<SearchTweetsResult> searchTweets(String queryString) {
        return Mono.empty();
    }
}
