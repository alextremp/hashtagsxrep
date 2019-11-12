package cat.xarxarepublicana.hashtagsxrep.domain.extraction;

import cat.xarxarepublicana.hashtagsxrep.domain.monitor.Monitor;
import cat.xarxarepublicana.hashtagsxrep.domain.twitter.Tweet;

import static cat.xarxarepublicana.hashtagsxrep.domain.service.TimeConverter.toLocalDateTime;

public class TwitterExtractionFactory {

  public TwitterExtraction createFromMonitorExtractedTweet(Monitor monitor, Tweet tweet) {
    final Interaction interaction;
    if (tweet.getQuotedStatus() != null) {
      interaction = new Interaction(TwitterExtraction.TYPE_QUOTE,
                                    tweet.getQuotedStatus().getIdStr(),
                                    tweet.getQuotedStatus().getUser().getIdStr());
    } else if (tweet.getInReplyToStatusIdStr() != null) {
      interaction = new Interaction(TwitterExtraction.TYPE_COMMENT,
                                    tweet.getInReplyToStatusIdStr(),
                                    tweet.getInReplyToUserIdStr());
    } else if (tweet.getRetweetedStatus() != null) {
      interaction = new Interaction(TwitterExtraction.TYPE_RETWEET,
                                    tweet.getRetweetedStatus().getIdStr(),
                                    tweet.getRetweetedStatus().getUser().getIdStr());
    } else {
      interaction = new Interaction(TwitterExtraction.TYPE_TWEET, null, null);
    }

    return new TwitterExtraction(
        monitor.getId(),
        tweet.getIdStr(),
        tweet.getUser().getIdStr(),
        interaction.type,
        toLocalDateTime(tweet.getCreatedAt()),
        interaction.statusId,
        interaction.userId,
        tweet.getLang(),
        TwitterExtraction.TYPE_RETWEET != interaction.type ? tweet.getText() : null
    );
  }

  private class Interaction {
    private final String type;
    private final String statusId;
    private final String userId;

    private Interaction(String type, String statusId, String userId) {
      this.type = type;
      this.statusId = statusId;
      this.userId = userId;
    }
  }
}
