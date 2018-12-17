package cat.xarxarepublicana.hashtagsxrep.domain.poll;

import java.time.LocalDateTime;

public class Poll {

    private final String id;
    private final String authorId;
    private final String authorNickname;
    private final String description;
    private final LocalDateTime creationDate;
    private final LocalDateTime startProposalsTime;
    private final LocalDateTime endProposalsTime;
    private final LocalDateTime endVotingTime;

    public Poll(String id, String authorId, String authorNickname, String description, LocalDateTime creationDate, LocalDateTime startProposalsTime, LocalDateTime endProposalsTime, LocalDateTime endVotingTime) {
        this.id = id;
        this.authorId = authorId;
        this.authorNickname = authorNickname;
        this.description = description;
        this.creationDate = creationDate;
        this.startProposalsTime = startProposalsTime;
        this.endProposalsTime = endProposalsTime;
        this.endVotingTime = endVotingTime;
    }

    public String getId() {
        return id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartProposalsTime() {
        return startProposalsTime;
    }

    public LocalDateTime getEndProposalsTime() {
        return endProposalsTime;
    }

    public LocalDateTime getEndVotingTime() {
        return endVotingTime;
    }
}
