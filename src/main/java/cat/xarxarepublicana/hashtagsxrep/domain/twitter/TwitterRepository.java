package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

public interface TwitterRepository {

    String getAuthorizationUrl();
    TwitterUser verifyCredentials(String oauthToken, String oauthVerifier);

}
