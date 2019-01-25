package cat.xarxarepublicana.hashtagsxrep.application.login;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TwitterConnectUseCase {

    // TODO V2
    public Mono<String> twitterConnect(String oauthToken, String oauthVerifier, String denied) {
        return Mono.fromCallable(() -> validate(denied))
                .map(valid -> "/");
    }

    private Mono<Boolean> validate(String denied) {
        return Mono.just(StringUtils.isEmpty(denied))
                .flatMap(empty -> empty ?
                        Mono.just(true)
                        : Mono.error(new RuntimeException("Not accepted: " + denied)));
    }
}
