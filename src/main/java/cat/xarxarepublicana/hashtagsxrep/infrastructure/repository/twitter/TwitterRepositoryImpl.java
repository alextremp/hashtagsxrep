package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.SearchTweetsResult;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterException;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterUser;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class TwitterRepositoryImpl implements TwitterRepository {

    private static final Integer MAX_STATUSES_PER_REQUEST = 100;
    private static final String VERIFY_CREDENTIALS = "https://api.twitter.com/1.1/account/verify_credentials.json";
    private static final String SEARCH_TWEETS = "https://api.twitter.com/1.1/search/tweets.json";

    private final UserFactory userFactory;
    private final OAuth10aService service;
    private final OAuth1AccessToken applicationToken;
    private final ObjectMapper objectMapper;

    public TwitterRepositoryImpl(UserFactory userFactory, OAuth10aService oAuth10aService, OAuth1AccessToken applicationToken, ObjectMapper objectMapper) {
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
            User user = userFactory.createFromTwitterLoggedUser(twitterUser, oauthToken, oauthVerifier);
            return user;
        } catch (Exception e) {
            throw new TwitterException("Error verifying user", e);
        }
    }

    @Override
    public SearchTweetsResult searchTweets(String twitterQuery, LocalDateTime startDate, String sinceStatusId) {
        OAuthRequest request = new OAuthRequest(Verb.GET, SEARCH_TWEETS);
        request.addQuerystringParameter("q", twitterQuery);
        request.addQuerystringParameter("since", startDate.format(ISO_LOCAL_DATE));
        request.addQuerystringParameter("count", MAX_STATUSES_PER_REQUEST.toString());
        request.addQuerystringParameter("result_type", "mixed");
        request.addQuerystringParameter("include_entities", "false");
        request.addQuerystringParameter("since_id", sinceStatusId == null ? "0" : sinceStatusId);

        service.signRequest(applicationToken, request);
        Response response;
        SearchTweetsResult searchTweetsResult;
        try {
            response = service.execute(request);
        } catch (Exception e) {
            throw new TwitterException("Error realitzant la cerca: " + twitterQuery, e);
        }
        try {
            searchTweetsResult = objectMapper.readValue(response.getStream(), SearchTweetsResult.class);
        } catch (Exception e) {
            throw new TwitterException("Error llegint la resposta de Twitter: " + twitterQuery, e);
        }
        return searchTweetsResult;
    }

    @Override
    public Integer getStatusesBlockSize() {
        return MAX_STATUSES_PER_REQUEST;
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
