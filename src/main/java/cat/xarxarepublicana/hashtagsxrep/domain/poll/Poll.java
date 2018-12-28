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
    private final LocalDateTime startEventTime;
    private final LocalDateTime instanceTime;

    public Poll(String id, String authorId, String authorNickname, String description, LocalDateTime creationDate, LocalDateTime startProposalsTime, LocalDateTime endProposalsTime, LocalDateTime endVotingTime, LocalDateTime startEventTime) {
        this.id = id;
        this.authorId = authorId;
        this.authorNickname = authorNickname;
        this.description = description;
        this.creationDate = creationDate;
        this.startProposalsTime = startProposalsTime;
        this.endProposalsTime = endProposalsTime;
        this.endVotingTime = endVotingTime;
        this.startEventTime = startEventTime;
        this.instanceTime = LocalDateTime.now();
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

    public LocalDateTime getStartEventTime() {
        return startEventTime;
    }

    public boolean isProposalsTime() {
        return instanceTime.isBefore(getEndProposalsTime());
    }

    public boolean isVotingTime() {
        return !isProposalsTime() && instanceTime.isBefore(getEndVotingTime());
    }
}
