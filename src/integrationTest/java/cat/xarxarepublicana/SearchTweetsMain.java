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
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Logger;

public class SearchTweetsMain {

  private static final Logger LOG = Logger.getLogger(SearchTweetsMain.class.getName());

  private static Properties P;
  private final TwitterRepository twitterRepository;

  public SearchTweetsMain(TwitterRepository twitterRepository) {
    this.twitterRepository = twitterRepository;
  }

  public static void main(String[] args) throws Exception {
    P = new Properties();
    P.load(new FileReader(new File("twitter.properties")));
    SearchTweetsMain searchTweetsMain = new SearchTweetsMain(createTwitterRepository());
    searchTweetsMain.find("#conquis16");
  }

  private static TwitterRepository createTwitterRepository() {
    return new TwitterRepositoryImpl(new UserFactory(), oAuth10aService(), oAuth1AccessToken(), new ObjectMapper());
  }

  private static OAuth10aService oAuth10aService() {
    return new ServiceBuilder(P.getProperty("HASHTAGS_OAUTH_TWITTER_CONSUMER_API_KEY"))
        .apiSecret(P.getProperty("HASHTAGS_OAUTH_TWITTER_CONSUMER_API_SECRET"))
        .callback("http://localhost:5000/connect/twitter")
        .build(TwitterApi.instance());
  }

  private static OAuth1AccessToken oAuth1AccessToken() {
    return new OAuth1AccessToken(
        P.getProperty("HASHTAGS_OAUTH_TWITTER_ACCESS_TOKEN_KEY"),
        P.getProperty("HASHTAGS_OAUTH_TWITTER_ACCESS_TOKEN_SECRET"));
  }

  public void find(String hashtag) {
    int contents = 0;
    boolean stop = false;
    int calls = 0;
    int maxResults = 100;
    String query = "count=" + maxResults + "&q=" + hashtag;
    String nextQuery = query;
    //String query = "since_id=1123356509134622722&count=100&q=" + hashtag;
    //String query = "max_id=1123233417435414529&q=#conquis16&count=100&include_entities=1";
    //String query = "count=100&q=#conquis16";
    String sinceId = null;
    String maxId = null;
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
    while (!stop) {
      LOG.info(">> QUERY: " + nextQuery);
      calls++;

      SearchTweetsResult result = twitterRepository.searchTweets(nextQuery);

      if (result.getStatuses() != null && result.getStatuses().length > 0) {
        BigInteger id = new BigInteger(result.getStatuses()[result.getStatuses().length - 1].getIdStr());
        maxId = id.subtract(BigInteger.ONE).toString();
      } else {
        maxId = null;
      }

      if (result.getStatuses() != null) {
        if (sinceId == null && calls == 1) {
          sinceId = result.getStatuses()[0].getIdStr();
        }
        contents += result.getStatuses().length;
        LOG.info(">> " + result.getStatuses().length);
        for (Tweet tweet : result.getStatuses()) {
          LOG.info(">> " + tweet.getIdStr() + "-" + dateFormat.format(tweet.getCreatedAt()));
        }
      }

      stop = result.getSearchMetadata().getNextResults() == null || result.getStatuses().length < maxResults;
      if (!stop) {
        //nextQuery = query + "&max_id=" + maxId;
        nextQuery = result.getSearchMetadata().getNextResults();
      } else {
        nextQuery = query + "&since_id=" + sinceId;
      }
    }
    LOG.info(">> NEXT: " + nextQuery);
    LOG.info(">> CALLS: " + calls);
    LOG.info(">> CONTENTS: " + contents);
  }
}
