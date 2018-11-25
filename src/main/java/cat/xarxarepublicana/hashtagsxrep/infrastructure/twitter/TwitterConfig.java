package cat.xarxarepublicana.hashtagsxrep.infrastructure.twitter;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitterConfig {

  @Value("${twitter.apiKey}")
  private String apiKey;

  @Value("${twitter.apiSecret}")
  private String apiSecret;

  @Value("${twitter.oauthCallback}")
  private String oauthCallback;

  @Bean
  public OAuth10aService oAuth10aService() {
    return new ServiceBuilder(apiKey)
        .apiSecret(apiSecret)
        .callback(oauthCallback)
        .build(TwitterApi.instance());
  }

  @Bean
  public TwitterClient twitterClient(OAuth10aService oAuth10aService) {
    return new TwitterClient(oAuth10aService);
  }
}
