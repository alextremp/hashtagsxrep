package cat.xarxarepublicana.hashtagsxrep.domain.report;

public class Report {

    private final TweetedContentResume tweetedContentResume;
    private final InteractorResume interactorResume;
    private final Integer taggersCount;
    private final Integer maxImpressions;
    private final Integer maxAudience;
    private final UserContentResume userContentResume;

    public Report(TweetedContentResume tweetedContentResume, InteractorResume interactorResume, Integer taggersCount, Integer maxImpressions, Integer maxAudience, UserContentResume userContentResume) {
        this.tweetedContentResume = tweetedContentResume != null ? tweetedContentResume : new TweetedContentResume(0, 0, 0, 0);
        this.interactorResume = interactorResume != null ? interactorResume : new InteractorResume(0, 0);
        this.taggersCount = taggersCount != null ? taggersCount : 0;
        this.maxImpressions = maxImpressions != null ? maxImpressions : 0;
        this.maxAudience = maxAudience != null ? maxAudience : 0;
        this.userContentResume = userContentResume != null ? userContentResume : new UserContentResume(0, 0, 0, 0);
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
