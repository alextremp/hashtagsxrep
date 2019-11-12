package cat.xarxarepublicana.hashtagsxrep.domain.ranking;

public class Score {

  private final String nickname;
  private final Integer score;

  public Score(String nickname, Integer score) {
    this.nickname = nickname;
    this.score = score;
  }

  public String getNickname() {
    return nickname;
  }

  public Integer getScore() {
    return score;
  }
}
