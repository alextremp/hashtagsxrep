package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller.dto;

public class HashtagCountResponse {
    private final boolean accepted;
    private final String reason;

    public HashtagCountResponse(boolean accepted, String reason) {
        this.accepted = accepted;
        this.reason = reason;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getReason() {
        return reason;
    }
}
