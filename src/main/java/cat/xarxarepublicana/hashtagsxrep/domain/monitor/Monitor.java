package cat.xarxarepublicana.hashtagsxrep.domain.monitor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Monitor {

    private static final DateTimeFormatter DDMMYYYY_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final String id;
    private final String authorId;
    private final String authorNickname;
    private final Boolean active;
    private final String twitterQuery;
    private final LocalDateTime creationDate;
    private final LocalDateTime endDate;
    private LocalDateTime lastUpdateDate;
    private String nextQueryString;

    public Monitor(String id, String authorId, String authorNickname, Boolean active, String twitterQuery, LocalDateTime creationDate, LocalDateTime endDate, LocalDateTime lastUpdateDate, String nextQueryString) {
        this.id = id;
        this.authorId = authorId;
        this.authorNickname = authorNickname;
        this.active = active;
        this.twitterQuery = twitterQuery;
        this.creationDate = creationDate;
        this.endDate = endDate;
        this.lastUpdateDate = lastUpdateDate;
        this.nextQueryString = nextQueryString;
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

    public String getNextQueryString() {
        return nextQueryString;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void updateCursor(String nextQueryString) {
        this.nextQueryString = nextQueryString;
        this.lastUpdateDate = LocalDateTime.now();
    }

    public String getFormattedCreationDate() {
        return creationDate != null ? DDMMYYYY_FORMATTER.format(creationDate) : "";
    }
}
