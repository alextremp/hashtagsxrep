package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterException;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TwitterRepositoryImpl implements TwitterRepository {

    private static final String VERIFY_CREDENTIALS = "https://api.twitter.com/1.1/account/verify_credentials.json";

    private final OAuth10aService service;
    private final OAuth1AccessToken applicationToken;
    private final ObjectMapper objectMapper;

    public TwitterRepositoryImpl(OAuth10aService oAuth10aService, OAuth1AccessToken applicationToken, ObjectMapper objectMapper) {
        this.service = oAuth10aService;
        this.applicationToken = applicationToken;
        this.objectMapper = objectMapper;
    }

    @Override
    public TwitterUser verifyCredentials(String oauthToken, String oauthVerifier) {
        try {
            OAuth1RequestToken token = new OAuth1RequestToken(oauthToken, oauthVerifier);
            OAuth1AccessToken accessToken = service.getAccessToken(token, oauthVerifier);
            OAuthRequest request = new OAuthRequest(Verb.GET, VERIFY_CREDENTIALS);
            service.signRequest(accessToken, request);
            Response response = this.service.execute(request);
            return this.objectMapper.readValue(response.getStream(), TwitterUser.class);
        } catch (Exception e) {
            throw new TwitterException("Error verifying user", e);
        }
    }

    @Override
    public String getAuthorizationUrl() {
        try {
            final OAuth1RequestToken requestToken = service.getRequestToken();
            return service.getAuthorizationUrl(requestToken);
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new TwitterException("Error requesting token", e);
        }
    }
}
