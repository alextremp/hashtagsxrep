package cat.xarxarepublicana.hashtagsxrep.domain.notice;

public class PollClosedNotice {

    private final String title;
    private final String hashtag;
    private final String subject;
    private final String pollId;
    private final String hour;

    public PollClosedNotice(String title, String hashtag, String subject, String pollId, String hour) {
        this.title = title;
        this.hashtag = hashtag;
        this.subject = subject;
        this.pollId = pollId;
        this.hour = hour;
    }

    public String getTitle() {
        return title;
    }

    public String getHashtag() {
        return hashtag;
    }

    public String getSubject() {
        return subject;
    }

    public String getPollId() {
        return pollId;
    }

    public String getHour() {
        return hour;
    }
}
