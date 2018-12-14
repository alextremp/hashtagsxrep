package cat.xarxarepublicana.hashtagsxrep.domain.report;

public class UserContentResume {

    private final Integer tweets;
    private final Integer quotes;
    private final Integer comments;
    private final Integer retweets;

    public UserContentResume(Integer tweets, Integer quotes, Integer comments, Integer retweets) {
        this.tweets = tweets;
        this.quotes = quotes;
        this.comments = comments;
        this.retweets = retweets;
    }

    public Integer getTweets() {
        return tweets;
    }

    public Integer getQuotes() {
        return quotes;
    }

    public Integer getComments() {
        return comments;
    }

    public Integer getRetweets() {
        return retweets;
    }
}
