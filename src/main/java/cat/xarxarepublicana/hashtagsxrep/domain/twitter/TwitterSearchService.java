package cat.xarxarepublicana.hashtagsxrep.domain.twitter;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cat.xarxarepublicana.hashtagsxrep.domain.extraction.TwitterExtraction.*;

public class TwitterSearchService {

    private static final Logger LOG = Logger.getLogger(TwitterSearchService.class.getName());

    public static final Integer MAX_TWEETS_PER_QUERY = 100;

    private final TwitterRepository twitterRepository;

    public TwitterSearchService(TwitterRepository twitterRepository) {
        this.twitterRepository = twitterRepository;
    }

    public Integer countTweets(String hashtag, Integer max) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("count=").append(MAX_TWEETS_PER_QUERY)
                .append("&q=").append(hashtag);

        SearchTweetsResult searchTweetsResult;
        int count = 0;
        boolean stop = false;
        while (!stop) {
            searchTweetsResult = twitterRepository.searchTweets(queryString.toString());
            count += Arrays.stream(searchTweetsResult.getStatuses())
                    .filter(tweet -> !TYPE_RETWEET.equals(type(tweet)))
                    .count();
            if (LOG.isLoggable(Level.FINE)) {
                LOG.log(Level.FINE, hashtag + " >> count >> " + count);
            }
            stop = searchTweetsResult.getStatuses().length < MAX_TWEETS_PER_QUERY || count > max;
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
