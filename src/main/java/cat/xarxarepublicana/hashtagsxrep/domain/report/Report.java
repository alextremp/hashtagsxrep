package cat.xarxarepublicana.hashtagsxrep.domain.report;

public class Report {

    private final TweetedContentResume tweetedContentResume;
    private final InteractorResume interactorResume;
    private final Integer taggersCount;
    private final Integer maxImpressions;
    private final Integer maxAudience;
    private final UserContentResume userContentResume;

    public Report(TweetedContentResume tweetedContentResume, InteractorResume interactorResume, Integer taggersCount, Integer maxImpressions, Integer maxAudience, UserContentResume userContentResume) {
        this.tweetedContentResume = tweetedContentResume;
        this.interactorResume = interactorResume;
        this.taggersCount = taggersCount;
        this.maxImpressions = maxImpressions;
        this.maxAudience = maxAudience;
        this.userContentResume = userContentResume;
    }

    public InteractorResume getInteractorResume() {
        return interactorResume;
    }

    public TweetedContentResume getTweetedContentResume() {
        return tweetedContentResume;
    }

    public Integer getTaggersCount() {
        return taggersCount;
    }

    public Integer getMaxImpressions() {
        return maxImpressions;
    }

    public Integer getMaxAudience() {
        return maxAudience;
    }

    public UserContentResume getUserContentResume() {
        return userContentResume;
    }
}
