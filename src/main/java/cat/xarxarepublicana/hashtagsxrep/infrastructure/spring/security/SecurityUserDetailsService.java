package cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private InMemoryUserRepository userRepository;

    public SecurityUserDetailsService() {

    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserAuth user = userRepository.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + id);
        }
        return user;
    }

}
