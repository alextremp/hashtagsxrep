package cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security;

import cat.xarxarepublicana.hashtagsxrep.domain.Role;
import cat.xarxarepublicana.hashtagsxrep.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserAuth implements UserDetails {

    private List<Role> authorities;
    private User user;

    public UserAuth(User user) {
        this.user = user;
        this.authorities = Role.ACCESS.get(user.getRole());
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