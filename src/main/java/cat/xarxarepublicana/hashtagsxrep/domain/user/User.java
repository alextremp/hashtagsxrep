package cat.xarxarepublicana.hashtagsxrep.domain.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {
    private String id;
    private String nickname;
    private String name;
    private String token;
    private String secret;
    private String role;
    private LocalDateTime signInDate;
    private LocalDateTime systemCreationDate;
    private LocalDateTime twitterCreationDate;
    private Integer followers;
    private Integer following;
    private String language;
    private String location;
    private String profileImageUrl;
    private boolean verified;
    private boolean locked;

    public User(String id, String nickname, String name, String token, String secret,
                String role, LocalDateTime signedInDate, LocalDateTime systemCreationDate, LocalDateTime twitterCreationDate,
                Integer followers, Integer following, String language, String location,
                String profileImageUrl, boolean verified, boolean locked) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.token = token;
        this.secret = secret;
        this.role = role;
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

    public User(String id, String nickname, String name, String token, String secret,
                String role, Timestamp signedInDate, Timestamp systemCreationDate, Timestamp twitterCreationDate,
                Integer followers, Integer following, String language, String location,
                String profileImageUrl, boolean verified, boolean locked) {
        this(id, nickname, name, token, secret, role,
                signedInDate != null ? signedInDate.toLocalDateTime() : null,
                systemCreationDate != null ? signedInDate.toLocalDateTime() : null,
                twitterCreationDate != null ? twitterCreationDate.toLocalDateTime() : null,
                followers, following, language, location,
                profileImageUrl, verified, locked);
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

    public String getRole() {
        return role;
    }

    public LocalDateTime getSystemCreationDate() {
        return systemCreationDate;
    }

    public LocalDateTime getTwitterCreationDate() {
        return twitterCreationDate;
    }

    public Integer getFollowers() {
        return followers;
    }

    public Integer getFollowing() {
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

    public void updateSignedInDate(LocalDateTime signInDate) {
        this.signInDate = signInDate;
    }
}
