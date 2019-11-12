package cat.xarxarepublicana.hashtagsxrep.domain.ranking;

public class HashtagScore {

  private final String hashtag;
  private final String type;
  private final Integer quantity;
  private final Integer score;

  public HashtagScore(String hashtag, String type, Integer quantity, Integer score) {
    this.hashtag = hashtag;
    this.type = type;
    this.quantity = quantity;
    this.score = score;
  }

  public String getHashtag() {
    return hashtag;
  }

  public String getType() {
    return type;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public Integer getScore() {
    return score;
  }
}
