package cat.xarxarepublicana.hashtagsxrep.domain.invite;

public class Invite {

  public static final String REASON_ADMIN = "ADMIN";
  public static final String REASON_TAGGER = "TAGGER";
  public static final String REASON_SCORE = "SCORE";
  public static final String REASON_RANDOM = "RANDOM";
  public static final String REASON_INFLUENCE = "INFLUENCE";

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
