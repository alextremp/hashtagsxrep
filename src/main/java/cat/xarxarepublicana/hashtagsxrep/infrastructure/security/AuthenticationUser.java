package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationUser implements UserDetails {

  private List<AuthenticationRole> authorities;
  private User user;

  public AuthenticationUser(User user) {
    this.user = user;
    this.authorities = AuthenticationRole.fromRole(user.getRole());
  }

  public User getUser() {
    return user;
  }

  public String getId() {
    return getUser().getId();
  }

  public String getPublicToken() {
    return getUser().getToken();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return getUser().getNickname();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}