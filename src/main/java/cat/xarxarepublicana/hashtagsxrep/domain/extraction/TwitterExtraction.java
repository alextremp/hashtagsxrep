package cat.xarxarepublicana.hashtagsxrep.domain.extraction;

import java.time.LocalDateTime;

public class TwitterExtraction {

  public static final String TYPE_TWEET = "T";
  public static final String TYPE_RETWEET = "R";
  public static final String TYPE_QUOTE = "Q";
  public static final String TYPE_COMMENT = "C";

  private final String monitorId;
  private final String tweetId;
  private final String userId;
  private final String type;
  private final LocalDateTime creationDate;
  private final String interactedStatusId;
  private final String interactedUserId;
  private final String language;
  private final String text;

  public TwitterExtraction(
      String monitorId,
      String tweetId,
      String userId,
      String type,
      LocalDateTime creationDate,
      String interactedStatusId,
      String interactedUserId,
      String language,
      String text) {
    this.monitorId = monitorId;
    this.tweetId = tweetId;
    this.userId = userId;
    this.type = type;
    this.creationDate = creationDate;
    this.interactedStatusId = interactedStatusId;
    this.interactedUserId = interactedUserId;
    this.language = language;
    this.text = text;
  }

  public String getMonitorId() {
    return monitorId;
  }

  public String getTweetId() {
    return tweetId;
  }

  public String getUserId() {
    return userId;
  }

  public String getType() {
    return type;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public String getInteractedStatusId() {
    return interactedStatusId;
  }

  public String getInteractedUserId() {
    return interactedUserId;
  }

  public String getLanguage() {
    return language;
  }

  public String getText() {
    return text;
  }
}
