package cat.xarxarepublicana.hashtagsxrep.domain.report;

public class Report {

    private final TweetedContentResume tweetedContentResume;
    private final InteractorResume interactorResume;
    private final Integer taggersCount;
    private final UserContentResume userContentResume;

    public Report(TweetedContentResume tweetedContentResume, InteractorResume interactorResume, Integer taggersCount, UserContentResume userContentResume) {
        this.tweetedContentResume = tweetedContentResume;
        this.interactorResume = interactorResume;
        this.taggersCount = taggersCount;
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

    public UserContentResume getUserContentResume() {
        return userContentResume;
    }
}
