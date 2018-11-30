package cat.xarxarepublicana.hashtagsxrep.domain.user;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterUser;

import java.time.LocalDateTime;

import static cat.xarxarepublicana.hashtagsxrep.domain.service.TimeConverter.toLocalDateTime;

public class User {
    private String id;
    private String nickname;
    private String name;
    private String token;
    private String secret;
    private ACCESS access;
    private LocalDateTime signInDate;
    private LocalDateTime systemCreationDate;
    private LocalDateTime twitterCreationDate;
    private Long followers;
    private Long following;
    private String language;
    private String location;
    private String profileImageUrl;
    private boolean verified;
    private boolean locked;

    public User(String id, String nickname, String name, LocalDateTime twitterCreationDate, Long followers, Long following,
                String language, String location, String profileImageUrl,
                boolean verified, boolean locked) {
        this(id, nickname, name, null, null, ACCESS.VIEWER, null,
                null, twitterCreationDate, followers, following, language,
                location, profileImageUrl, verified, locked);
    }

    public User(String id, String nickname, String name, String token, String secret,
                ACCESS access, LocalDateTime signedInDate, LocalDateTime systemCreationDate, LocalDateTime twitterCreationDate,
                Long followers, Long following, String language, String location,
                String profileImageUrl, boolean verified, boolean locked) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.token = token;
        this.secret = secret;
        this.access = access;
        this.signInDate = signedInDate;
        this.systemCreationDate = systemCreationDate;
        this.twitterCreationDate = twitterCreationDate;
        this.followers = followers;
        this.following = following;
        this.language = language;
        this.location = location;
        this.profileImageUrl = profileImageUrl;
        this.verified = verified;
        this.locked = locked;
    }

    public void updateFromTwitter(TwitterUser twitterUser) {
        this.nickname = twitterUser.getScreenName();
        this.name = twitterUser.getName();
        this.twitterCreationDate = toLocalDateTime(twitterUser.getCreatedAt());
        this.followers = twitterUser.getFollowersCount();
        this.following = twitterUser.getFriendsCount();
        this.language = twitterUser.getLang();
        this.location = twitterUser.getLocation();
        this.profileImageUrl = twitterUser.getProfileImageUrlHttps();
        this.verified = twitterUser.isVerified();
        this.locked = twitterUser.isProtected();
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public String getSecret() {
        return secret;
    }

    public ACCESS getAccess() {
        return access;
    }

    public LocalDateTime getSystemCreationDate() {
        return systemCreationDate;
    }

    public LocalDateTime getTwitterCreationDate() {
        return twitterCreationDate;
    }

    public Long getFollowers() {
        return followers;
    }

    public Long getFollowing() {
        return following;
    }

    public String getLanguage() {
        return language;
    }

    public String getLocation() {
        return location;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public boolean isVerified() {
        return verified;
    }

    public boolean isLocked() {
        return locked;
    }

    public LocalDateTime getSignInDate() {
        return signInDate;
    }
}
