package cat.xarxarepublicana.hashtagsxrep.domain.poll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Poll {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/LL HH:mm'h'");
    public static final String PUBLIC_POLL = "public";

    private final String id;
    private final String authorId;
    private final String authorNickname;
    private final String description;
    private final String monitorId;
    private final LocalDateTime creationDate;
    private final LocalDateTime startProposalsTime;
    private final LocalDateTime endProposalsTime;
    private final LocalDateTime endVotingTime;
    private final LocalDateTime startEventTime;
    private final LocalDateTime endRankingTime;
    private final LocalDateTime instanceTime;

    public Poll(String id, String authorId, String authorNickname, String description, String monitorId, LocalDateTime creationDate, LocalDateTime startProposalsTime, LocalDateTime endProposalsTime, LocalDateTime endVotingTime, LocalDateTime startEventTime, LocalDateTime endRankingTime) {
        this.id = id;
        this.authorId = authorId;
        this.authorNickname = authorNickname;
        this.description = description;
        this.monitorId = monitorId;
        this.creationDate = creationDate;
        this.startProposalsTime = startProposalsTime;
        this.endProposalsTime = endProposalsTime;
        this.endVotingTime = endVotingTime;
        this.startEventTime = startEventTime;
        this.endRankingTime = endRankingTime;
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

    public String getMonitorId() {
        return monitorId;
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

    public LocalDateTime getEndRankingTime() {
        return endRankingTime;
    }

    public boolean isNotStarted() {
        return instanceTime.isBefore(getStartProposalsTime());
    }

    public boolean isProposalsTime() {
        return instanceTime.isAfter(getStartProposalsTime()) && instanceTime.isBefore(getEndProposalsTime());
    }

    public boolean isVotingTime() {
        return instanceTime.isAfter(getEndProposalsTime()) && instanceTime.isBefore(getEndVotingTime());
    }

    public boolean isVotingClosed() {
        return instanceTime.isAfter(getEndVotingTime());
    }

    public String asString(LocalDateTime date) {
        return FORMATTER.format(date);
    }
}
