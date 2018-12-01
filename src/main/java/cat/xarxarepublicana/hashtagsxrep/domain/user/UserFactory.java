package cat.xarxarepublicana.hashtagsxrep.domain.user;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterUser;

import java.time.LocalDateTime;

import static cat.xarxarepublicana.hashtagsxrep.domain.service.TimeConverter.toLocalDateTime;

public class UserFactory {

    public User createFromTwitter(TwitterUser twitterUser, String token, String secret) {
        User user = new User(
                twitterUser.getIdStr(),
                twitterUser.getScreenName(),
                twitterUser.getName(),
                token,
                secret,
                Role.VIEWER,
                null,
                LocalDateTime.now(),
                toLocalDateTime(twitterUser.getCreatedAt()),
                twitterUser.getFollowersCount(),
                twitterUser.getFriendsCount(),
                twitterUser.getLang(),
                twitterUser.getLocation(),
                twitterUser.getProfileImageUrlHttps(),
                twitterUser.isVerified(),
                twitterUser.isProtected()
        );
        return user;
    }
}
