package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationUserService implements ReactiveUserDetailsService {

    private UserRepository userRepository;

    public AuthenticationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String id) {
        return Mono.fromCallable(() -> {
            User user = userRepository.findById(id);
            if (user == null) {
                throw new UsernameNotFoundException("Not found: " + id);
            }
            return new AuthenticationUser(user);
        });
    }
}
