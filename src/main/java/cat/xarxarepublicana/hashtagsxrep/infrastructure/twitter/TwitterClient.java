package cat.xarxarepublicana.hashtagsxrep.infrastructure.twitter;

import cat.xarxarepublicana.hashtagsxrep.domain.Role;
import cat.xarxarepublicana.hashtagsxrep.domain.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class TwitterClient {

  private static final Logger LOG = Logger.getLogger(TwitterClient.class.getName());

  private ObjectMapper objectMapper = new ObjectMapper();

  private final OAuth10aService service;
  private final String appKey;
  private final String appSecret;

  public TwitterClient(OAuth10aService oAuth10aService, String appKey, String appSecret) {
    LOG.info(">> oAuth10aService.getApiKey(): " + oAuth10aService.getApiKey());
    this.service = oAuth10aService;
    this.appKey = appKey;
    this.appSecret = appSecret;
  }

  public String authTokenUrl() throws Exception {
    final OAuth1RequestToken requestToken = service.getRequestToken();
    return service.getAuthorizationUrl(requestToken);
  }

  public User getUser(String requestToken, String oauthVerifier) throws Exception {
    OAuth1RequestToken token = new OAuth1RequestToken(requestToken, oauthVerifier);
    OAuth1AccessToken accessToken = service.getAccessToken(token, oauthVerifier);
    return getTwitterApiUser(accessToken);
  }

  private User getTwitterApiUser(OAuth1AccessToken accessToken) throws InterruptedException, ExecutionException, IOException {
    final OAuthRequest request = TwitterApi.verifyCredentials();
    service.signRequest(accessToken, request);
    final Response response = service.execute(request);
    JsonNode jsonNode = objectMapper.readTree(response.getBody());
    return new User(
        jsonNode.get("id_str").asText(),
        accessToken.getToken(),
        accessToken.getTokenSecret(),
        jsonNode.get("screen_name").asText(),
        jsonNode.get("name").asText(),
        Role.ROLE_USER.getAuthority(),
        jsonNode.get("location").asText(),
        jsonNode.get("profile_image_url_https").asText()
    );
  }
}
