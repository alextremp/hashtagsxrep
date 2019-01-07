package cat.xarxarepublicana.hashtagsxrep.domain.invite;

public class Invite {

    private final String nickname;
    private final String reason;

    public Invite(String nickname, String reason) {
        this.nickname = nickname;
        this.reason = reason;
    }

    public String getNickname() {
        return nickname;
    }

    public String getReason() {
        return reason;
    }
}
