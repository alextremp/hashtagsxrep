package cat.xarxarepublicana.hashtagsxrep.domain.poll;

import java.time.LocalDateTime;

public class Proposal {

    private final String pollId;
    private final String authorId;
    private final String authorNickname;
    private String hashtag;
    private String subject;
    private final LocalDateTime creationDate;
    private final Integer votes;

    public Proposal(String pollId, String authorId, String authorNickname, String hashtag, String subject, LocalDateTime creationDate, Integer votes) {
        this.pollId = pollId;
        this.authorId = authorId;
        this.authorNickname = authorNickname;
        this.hashtag = hashtag;
        this.subject = subject;
        this.creationDate = creationDate;
        this.votes = votes;
    }

    public void update(String hashtag, String subject) {
        this.hashtag = hashtag;
        this.subject = subject;
    }

    public String getPollId() {
        return pollId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public String getHashtag() {
        return hashtag;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getVotes() {
        return votes;
    }

}
