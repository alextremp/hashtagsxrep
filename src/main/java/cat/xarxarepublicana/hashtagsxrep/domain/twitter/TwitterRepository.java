package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

import java.time.LocalDateTime;

public interface TwitterRepository {

    String getAuthorizationUrl();

    User verifyCredentials(String oauthToken, String oauthVerifier);

    SearchTweetsResult searchTweets(String twitterQuery, LocalDateTime startDate, String sinceStatusId);

    Integer getStatusesBlockSize();
}
