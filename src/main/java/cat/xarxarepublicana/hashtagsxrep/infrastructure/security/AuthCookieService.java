package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthCookieService {

  private static final Logger LOG = LoggerFactory.getLogger(AuthCookieService.class);

  private static final int COOKIE_MAX_AGE = 2 * 30 * 24 * 3600; //two months
  private static final String COOKIE_LOGOUT = "_"; //support for browser versions not deleting the cookie

  private static final String USER_ID_CLAIM = "userId";
  private static final String USER_TOKEN_CLAIM = "userToken";

  private final String cookieName;
  private final String secret;

  public AuthCookieService(String cookieName, String secret) {
    this.cookieName = cookieName;
    this.secret = secret;
  }

  public AuthToken extractAuthToken(HttpServletRequest request) {
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if (cookieName.equals(cookie.getName()) && !COOKIE_LOGOUT.equals(cookie.getValue())) {
          return deserialize(cookie.getValue());
        }
      }
    }
    return null;
  }

  public void putAuthToken(AuthToken authToken, HttpServletResponse response) {
    String token = serialize(authToken);
    Cookie apiCookie = new Cookie(cookieName, token);
    apiCookie.setHttpOnly(true);
    apiCookie.setMaxAge(COOKIE_MAX_AGE);
    apiCookie.setPath("/");
    response.addCookie(apiCookie);
  }

  public AuthToken deserialize(String authToken) {
    Claims jwtClaims = Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(authToken)
        .getBody();
    if (jwtClaims == null || jwtClaims.getSubject() == null || jwtClaims.getSubject().isEmpty()) {
      return null;
    }
    return new AuthToken(
        jwtClaims.get(USER_ID_CLAIM, String.class),
        jwtClaims.getSubject(),
        jwtClaims.get(USER_TOKEN_CLAIM, String.class)
    );
  }

  public String serialize(AuthToken authToken) {
    Claims claims = Jwts.claims().setSubject(authToken.getUsername());
    claims.put(USER_ID_CLAIM, authToken.getUserId());
    claims.put(USER_TOKEN_CLAIM, authToken.getUserToken());
    return Jwts.builder()
        .setClaims(claims)
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }
}
