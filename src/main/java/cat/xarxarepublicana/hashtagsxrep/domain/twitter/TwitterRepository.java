package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

public interface TwitterRepository {

  Integer MAX_STATUSES_PER_REQUEST = 100;

  String getAuthorizationUrl();

  User verifyCredentials(String oauthToken, String oauthVerifier);

  SearchTweetsResult searchTweets(String queryString);
}
