package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import reactor.core.publisher.Mono;

public interface TwitterRepository {

    Mono<String> getAuthorizationUrl();

    Mono<User> verifyCredentials(String oauthToken, String oauthVerifier);

    Mono<SearchTweetsResult> searchTweets(String queryString);
}
