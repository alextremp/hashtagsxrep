package cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.statics;

import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@EnableWebMvc
public class StaticCacheRegistry implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/js/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.DAYS));
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.DAYS));
    }
}
