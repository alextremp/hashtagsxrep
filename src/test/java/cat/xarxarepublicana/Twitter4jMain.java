package cat.xarxarepublicana;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter4jMain {

  private static Properties P;

  public static void main(String args[]) throws Exception {

    String search = "elecciones";

    P = new Properties();
    P.load(new FileReader(new File("twitter.properties")));
    //Twitter twitter = TwitterFactory.getSingleton();
    //Query query = new Query("source:twitter4j yusukey");
    //QueryResult result = twitter.search(query);
    //for (Status status : result.getTweets()) {
    //  System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
    //}

    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true)
        .setOAuthAuthenticationURL("https://api.twitter.com/oauth/request_token")
        .setOAuthConsumerKey(P.getProperty("HASHTAGS_OAUTH_TWITTER_CONSUMER_API_KEY"))
        .setOAuthConsumerSecret(P.getProperty("HASHTAGS_OAUTH_TWITTER_CONSUMER_API_SECRET"))
        .setOAuthAccessToken(P.getProperty("HASHTAGS_OAUTH_TWITTER_ACCESS_TOKEN_KEY"))
        .setOAuthAccessTokenSecret(P.getProperty("HASHTAGS_OAUTH_TWITTER_ACCESS_TOKEN_SECRET"));
    TwitterFactory twitterFactory = new TwitterFactory(cb.build());
    Twitter twitter = twitterFactory.getInstance();
    try {
      Query query = new Query(search);
      QueryResult result;
      do {
        result = twitter.search(query);
        List<Status> tweets = result.getTweets();
        for (Status tweet : tweets) {
          System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
        }
      } while ((query = result.nextQuery()) != null);
      System.exit(0);
    } catch (TwitterException te) {
      te.printStackTrace();
      System.out.println("Failed to search tweets: " + te.getMessage());
      System.exit(-1);
    }
  }
}
