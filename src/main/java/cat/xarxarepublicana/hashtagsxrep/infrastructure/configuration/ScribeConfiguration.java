package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.infrastructure.twitter.ScribeTwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScribeConfiguration {

    @Bean
    public OAuth10aService oAuth10aService(
            @Value("${twitter.apiKey}") String apiKey,
            @Value("${twitter.apiSecret}") String apiSecret,
            @Value("${twitter.oauthCallback}") String oauthCallback) {
        return new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .callback(oauthCallback)
                .build(ScribeTwitterApi.instance());
    }

    @Bean
    public OAuth1AccessToken applicationToken(
            @Value("${twitter.appKey}") String appKey,
            @Value("${twitter.appSecret}") String appSecret) {
        return new OAuth1AccessToken(appKey, appSecret);
    }
}
