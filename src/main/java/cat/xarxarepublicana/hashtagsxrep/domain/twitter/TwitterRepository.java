package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

public interface TwitterRepository {

    String getAuthorizationUrl();

    User verifyCredentials(String oauthToken, String oauthVerifier);

}
