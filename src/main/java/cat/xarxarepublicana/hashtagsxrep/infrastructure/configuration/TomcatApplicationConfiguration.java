package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.HashtagsXRepApplication;
import java.util.TimeZone;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Profile;

@Profile("!integration-test")
public class TomcatApplicationConfiguration extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Madrid"));

    return builder.sources(HashtagsXRepApplication.class);
  }
}
