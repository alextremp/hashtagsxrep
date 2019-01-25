package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class AuthenticationUser implements UserDetails, Authentication {

    private static final Logger LOG = Logger.getLogger(AuthenticationUser.class.getName());

    private List<AuthenticationRole> authorities;
    private User user;

    public AuthenticationUser(User user) {
        LOG.info(">>> user >>> " + user.getId());
        this.user = user;
        this.authorities = AuthenticationRole.fromRole(user.getRole());
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return getUser().getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return user.getToken();
    }

    @Override
    public Object getDetails() {
        return this;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
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

    @Override
    public String getName() {
        return getUser().getName();
    }
}
