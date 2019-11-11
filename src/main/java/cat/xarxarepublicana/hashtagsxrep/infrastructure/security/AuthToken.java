package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

public class AuthToken {

  private final String userId;
  private final String username;
  private final String userToken;

  public AuthToken(String userId, String username, String userToken) {
    this.userId = userId;
    this.username = username;
    this.userToken = userToken;
  }

  public String getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public String getUserToken() {
    return userToken;
  }
}
