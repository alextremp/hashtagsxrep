package cat.xarxarepublicana.hashtagsxrep.domain;

public class User {
  private String id;
  private String nickname;
  private String name;
  private String role;
  private String token;
  private String secret;
  private String location;
  private String profileImageUrl;

  public User(String id, String token, String secret, String nickname, String name, String role, String location, String profileImageUrl) {
    this.id = id;
    this.token = token;
    this.secret = secret;
    this.nickname = nickname;
    this.name = name;
    this.role = role;
    this.location = location;
    this.profileImageUrl = profileImageUrl;
  }

  public String getId() {
    return id;
  }

  public String getRole() {
    return role;
  }

  public String getToken() {
    return token;
  }

  public String getSecret() {
    return secret;
  }

  public String getNickname() {
    return nickname;
  }

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }


}
