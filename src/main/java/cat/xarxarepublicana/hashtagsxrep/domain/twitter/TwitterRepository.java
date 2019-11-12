package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import reactor.core.publisher.Mono;

public interface TwitterRepository {

  Integer MAX_STATUSES_PER_REQUEST = 100;

  Mono<String> getAuthorizationUrl();

  User verifyCredentials(String oauthToken, String oauthVerifier);

  SearchTweetsResult searchTweets(String queryString);
}
