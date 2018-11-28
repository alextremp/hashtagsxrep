package twitterclient;

import cat.xarxarepublicana.hashtagsxrep.infrastructure.twitter.TwitterClient;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.twitter.TwitterConfig;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.FileInputStream;
import java.util.Properties;

public class TwitterClientMain {

    protected final Properties env;
    protected final TwitterConfig twitterConfig;
    protected final TwitterClient twitterClient;
    protected final OAuth10aService oAuth10aService;
    protected final OAuth1AccessToken appAccessToken;

    public TwitterClientMain() {
        this.env = new Properties();
        try {
            FileInputStream in = new FileInputStream(".env");
            this.env.load(in);
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Twitter Client Main error", e);
        }

        this.twitterConfig = new TwitterConfig(
                env.getProperty("OAUTH_TWITTER_CONSUMER_API_KEY"),
                env.getProperty("OAUTH_TWITTER_CONSUMER_API_SECRET"),
                env.getProperty("OAUTH_TWITTER_ACCESS_TOKEN_KEY"),
                env.getProperty("OAUTH_TWITTER_ACCESS_TOKEN_SECRET"),
                env.getProperty("OAUTH_CALLBACK")
        );

        this.oAuth10aService = twitterConfig.oAuth10aService();

        this.twitterClient = this.twitterConfig.twitterClient(this.oAuth10aService);

        this.appAccessToken = new OAuth1AccessToken(
                env.getProperty("OAUTH_TWITTER_ACCESS_TOKEN_KEY"),
                env.getProperty("OAUTH_TWITTER_ACCESS_TOKEN_SECRET")
        );
    }
}
