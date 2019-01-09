package cat.xarxarepublicana;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.SearchTweetsResult;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.Tweet;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserFactory;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter.TwitterApi;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter.TwitterRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Logger;

public class SearchTweetsMain {

    private static final Logger LOG = Logger.getLogger(SearchTweetsMain.class.getName());

    private static Properties P;
    private final TwitterRepository twitterRepository;

    public SearchTweetsMain(TwitterRepository twitterRepository) {
        this.twitterRepository = twitterRepository;
    }

    public void find(String hashtag) {
        SearchTweetsResult result = twitterRepository.searchTweets("count=100&q=" + hashtag);
        if (result.getStatuses() != null) {
            LOG.info(">> " + result.getStatuses().length);
            for (Tweet tweet : result.getStatuses()) {
                LOG.info(">> " + tweet.getText());
            }
        }
    }

    public static void main(String args[]) throws Exception {
        P = new Properties();
        P.load(new FileReader(new File(".env")));
        SearchTweetsMain searchTweetsMain = new SearchTweetsMain(createTwitterRepository());
        searchTweetsMain.find("#JUSTICEWORLDWIDE");
    }

    private static TwitterRepository createTwitterRepository() {
        return new TwitterRepositoryImpl(new UserFactory(), oAuth10aService(), oAuth1AccessToken(), new ObjectMapper());
    }

    private static OAuth10aService oAuth10aService() {
        return new ServiceBuilder(P.getProperty("OAUTH_TWITTER_CONSUMER_API_KEY"))
                .apiSecret(P.getProperty("OAUTH_TWITTER_CONSUMER_API_SECRET"))
                .callback("http://localhost:5000/connect/twitter")
                .build(TwitterApi.instance());
    }

    private static OAuth1AccessToken oAuth1AccessToken() {
        return new OAuth1AccessToken(P.getProperty("OAUTH_TWITTER_ACCESS_TOKEN_KEY"),
                P.getProperty("OAUTH_TWITTER_ACCESS_TOKEN_SECRET"));
    }
}
