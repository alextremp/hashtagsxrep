package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtraction.TYPE_COMMENT;
import static cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtraction.TYPE_QUOTE;
import static cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtraction.TYPE_RETWEET;
import static cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtraction.TYPE_TWEET;

public class TwitterSearchService {

  public static final Integer MAX_TWEETS_PER_QUERY = 100;
  private static final Logger LOG = Logger.getLogger(TwitterSearchService.class.getName());
  private final TwitterRepository twitterRepository;

  public TwitterSearchService(TwitterRepository twitterRepository) {
    this.twitterRepository = twitterRepository;
  }

  public Integer countTweets(String hashtag, Integer max) {
    String queryString = new StringBuffer()
        .append("count=").append(MAX_TWEETS_PER_QUERY)
        .append("&q=").append(hashtag)
        .toString();

    SearchTweetsResult searchTweetsResult;
    int count = 0;
    boolean stop = false;
    while (!stop) {
      searchTweetsResult = twitterRepository.searchTweets(queryString);
      count += Arrays.stream(searchTweetsResult.getStatuses())
          .filter(tweet -> !TYPE_RETWEET.equals(type(tweet)))
          .count();
      if (LOG.isLoggable(Level.FINE)) {
        LOG.log(Level.FINE, queryString + " >> count >> " + count);
      }
      stop = searchTweetsResult.getStatuses().length < MAX_TWEETS_PER_QUERY
          || searchTweetsResult.getSearchMetadata().getNextResults() == null
          || count > max;
      if (!stop) {
        queryString = searchTweetsResult.getSearchMetadata().getNextResults();
      }
    }
    return count;
  }

  public String type(Tweet tweet) {
    if (tweet.getQuotedStatus() != null) {
      return TYPE_QUOTE;
    } else if (tweet.getInReplyToStatusIdStr() != null) {
      return TYPE_COMMENT;
    } else if (tweet.getRetweetedStatus() != null) {
      return TYPE_RETWEET;
    } else {
      return TYPE_TWEET;
    }
  }
}
