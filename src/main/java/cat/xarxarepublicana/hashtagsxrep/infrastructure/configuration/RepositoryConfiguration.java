package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.local.InMemoryUserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter.TwitterApi;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.twitter.TwitterRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Value("${twitter.apiKey}")
    private String apiKey;

    @Value("${twitter.apiSecret}")
    private String apiSecret;

    @Value("${twitter.appKey}")
    private String appKey;

    @Value("${twitter.appSecret}")
    private String appSecret;

    @Value("${twitter.oauthCallback}")
    private String oauthCallback;

    @Bean
    public TwitterRepository twitterRepository(OAuth10aService oAuth10aService, OAuth1AccessToken applicationToken) {
        ObjectMapper objectMapper = new ObjectMapper();
        return new TwitterRepositoryImpl(oAuth10aService, applicationToken, objectMapper);
    }

    @Bean
    public OAuth10aService oAuth10aService() {
        return new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .callback(oauthCallback)
                .build(TwitterApi.instance());
    }

    @Bean
    public OAuth1AccessToken applicationToken() {
        return new OAuth1AccessToken(appKey, appSecret);
    }

    @Bean
    public UserRepository inMemoryUserRepository(UserFactory userFactory) {
        return new InMemoryUserRepository(userFactory);
    }

    @Bean
    public UserFactory defaultUserFactory() {
        return new UserFactory();
    }
}
