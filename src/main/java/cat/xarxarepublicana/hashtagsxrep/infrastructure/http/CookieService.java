package cat.xarxarepublicana.hashtagsxrep.infrastructure.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;

@Component
public class CookieService {

    private static final int COOKIE_MAX_AGE = 6 * 30 * 24 * 3600;

    private final String cookieName;

    @Autowired
    public CookieService(
            @Value("${app.usertoken.cookiename}") String cookieName) {
        this.cookieName = cookieName;
    }

    public Mono<ResponseCookie> save(String token) {
        return Mono.fromCallable(() -> ResponseCookie.from(cookieName, token)
                .httpOnly(true)
                .maxAge(COOKIE_MAX_AGE)
                .path("/")
                .build()
        );
    }

    public Mono<String> extract(MultiValueMap<String, HttpCookie> cookies) {
        return Mono.fromCallable(() -> cookies.getFirst(cookieName))
                .map(HttpCookie::getValue);
    }
}
