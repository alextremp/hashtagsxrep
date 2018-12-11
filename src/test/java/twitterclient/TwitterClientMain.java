package twitterclient;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserFactory;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter.TwitterApi;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter.TwitterRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class TwitterClientMain {

    private static final Logger LOG = Logger.getLogger(TwitterClientMain.class.getName());

    protected final Properties env;
    protected final TwitterRepository twitterRepository;

    protected String appToken;
    protected String appSecret;

    public TwitterClientMain() {
        this.env = new Properties();
        try {
            FileInputStream in = new FileInputStream(".env");
            this.env.load(in);
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Twitter Client Main error", e);
        }

        appToken = env.getProperty("OAUTH_TWITTER_ACCESS_TOKEN_KEY");
        appSecret = env.getProperty("OAUTH_TWITTER_ACCESS_TOKEN_SECRET");

        OAuth10aService oAuth10aService = new ServiceBuilder(env.getProperty("OAUTH_TWITTER_CONSUMER_API_KEY"))
                .apiSecret(env.getProperty("OAUTH_TWITTER_CONSUMER_API_SECRET"))
                .callback(env.getProperty("OAUTH_CALLBACK"))
                .build(TwitterApi.instance());
        OAuth1AccessToken oAuth1AccessToken = new OAuth1AccessToken(appToken, appSecret);
        UserFactory userFactory = new UserFactory();
        this.twitterRepository = new TwitterRepositoryImpl(userFactory, oAuth10aService, oAuth1AccessToken, new ObjectMapper());
    }
}
