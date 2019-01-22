package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class CookiedAuthenticationManager implements ReactiveAuthenticationManager {

    private static final Logger LOG = Logger.getLogger(CookiedAuthenticationManager.class.getName());

    private static final String USER_ID_CLAIM = "userId";
    private final String secret;
    private final ReactiveUserDetailsService reactiveUserDetailsService;

    public CookiedAuthenticationManager(
            @Value("${app.usertoken.secret}") String secret,
            ReactiveUserDetailsService reactiveUserDetailsService) {
        this.secret = secret;
        this.reactiveUserDetailsService = reactiveUserDetailsService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.fromCallable(() -> authentication.getCredentials().toString())
                .map(token -> Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody())
                .map(claims -> claims.get(USER_ID_CLAIM, String.class))
                .flatMap(userId -> reactiveUserDetailsService.findByUsername(userId))
                .map(authenticationUser -> new UsernamePasswordAuthenticationToken(authenticationUser, null, authenticationUser.getAuthorities()));
    }
}
