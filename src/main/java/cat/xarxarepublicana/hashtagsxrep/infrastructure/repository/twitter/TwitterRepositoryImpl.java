package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.SearchTweetsResult;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterException;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterUser;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.utils.OAuthEncoder;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

public class TwitterRepositoryImpl implements TwitterRepository {

  private static final Logger LOG = Logger.getLogger(TwitterRepositoryImpl.class.getName());

  private static final String VERIFY_CREDENTIALS = "https://api.twitter.com/1.1/account/verify_credentials.json";
  private static final String SEARCH_TWEETS = "https://api.twitter.com/1.1/search/tweets.json";

  private final UserFactory userFactory;
  private final OAuth10aService service;
  private final OAuth1AccessToken applicationToken;
  private final ObjectMapper objectMapper;

  public TwitterRepositoryImpl(
      UserFactory userFactory,
      OAuth10aService oAuth10aService,
      OAuth1AccessToken applicationToken,
      ObjectMapper objectMapper) {
    this.service = oAuth10aService;
    this.applicationToken = applicationToken;
    this.objectMapper = objectMapper;
    this.userFactory = userFactory;
  }

  @Override
  public User verifyCredentials(String oauthToken, String oauthVerifier) {
    try {
      OAuth1RequestToken token = new OAuth1RequestToken(oauthToken, oauthVerifier);
      OAuth1AccessToken accessToken = service.getAccessToken(token, oauthVerifier);
      OAuthRequest request = new OAuthRequest(Verb.GET, VERIFY_CREDENTIALS);
      service.signRequest(accessToken, request);
      Response response = service.execute(request);
      TwitterUser twitterUser = objectMapper.readValue(response.getStream(), TwitterUser.class);
      User user = userFactory.createFromTwitterLoggedUser(
          twitterUser,
          accessToken.getToken(),
          accessToken.getTokenSecret()
      );
      return user;
    } catch (IOException | InterruptedException | ExecutionException e) {
      throw new TwitterException("Error verifying user", e);
    }
  }

  @Override
  public SearchTweetsResult searchTweets(String queryString) {
    OAuthRequest request = new OAuthRequest(Verb.GET, SEARCH_TWEETS);
    setQueryString(request, queryString);

    if (LOG.isLoggable(Level.FINE)) {
      LOG.log(Level.FINE, ">> QUERY: " + request.getQueryStringParams().asFormUrlEncodedString());
    }
    service.signRequest(applicationToken, request);
    Response response;
    SearchTweetsResult searchTweetsResult;
    try {
      response = service.execute(request);
    } catch (IOException | InterruptedException | ExecutionException e) {
      throw new TwitterException("Error realitzant la cerca: " + queryString, e);
    }
    try {
      searchTweetsResult = objectMapper.readValue(response.getStream(), SearchTweetsResult.class);
    } catch (IOException e) {
      throw new TwitterException("Error llegint la resposta de Twitter: " + queryString, e);
    }
    return searchTweetsResult;
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

  private void setQueryString(OAuthRequest request, String queryString) {
    String[] pair;
    for (String part : StringUtils.split(StringUtils.removeStart(queryString, "?"), '&')) {
      pair = part.split("=");
      request.addQuerystringParameter(
          OAuthEncoder.decode(pair[0]),
          pair.length > 1 ? OAuthEncoder.decode(pair[1]) : "");
    }
  }
}
