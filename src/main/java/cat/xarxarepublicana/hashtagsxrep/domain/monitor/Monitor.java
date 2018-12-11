package cat.xarxarepublicana.hashtagsxrep.domain.monitor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Monitor {

    private final String id;
    private final String authorId;
    private final String authorNickname;
    private final Boolean active;
    private final String twitterQuery;
    private final LocalDateTime creationDate;
    private final LocalDateTime startDate;
    private final LocalDateTime lastUpdateDate;
    private final String lastUpdateCursor;

    public Monitor(String id, String authorId, String authorNickname, Boolean active, String twitterQuery, LocalDateTime creationDate, LocalDateTime startDate, LocalDateTime lastUpdateDate, String lastUpdateCursor) {
        this.id = id;
        this.authorId = authorId;
        this.authorNickname = authorNickname;
        this.active = active;
        this.twitterQuery = twitterQuery;
        this.creationDate = creationDate;
        this.startDate = startDate;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateCursor = lastUpdateCursor;
    }

    public Monitor(String id, String authorId, String authorNickname, Boolean active, String twitterQuery, Timestamp creationDate, Timestamp startDate, Timestamp lastUpdateDate, String lastUpdateCursor) {
        this(id, authorId, authorNickname, active, twitterQuery,
                creationDate != null ? creationDate.toLocalDateTime() : null,
                startDate != null ? startDate.toLocalDateTime() : null,
                lastUpdateDate != null ? lastUpdateDate.toLocalDateTime() : null,
                lastUpdateCursor);
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

    public String getTwitterQuery() {
        return twitterQuery;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public String getLastUpdateCursor() {
        return lastUpdateCursor;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
