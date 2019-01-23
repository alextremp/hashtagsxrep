package cat.xarxarepublicana.hashtagsxrep;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class HashtagsXRepApplication {

    public HashtagsXRepApplication(@Value("${app.timeZone}") String timeZone) {
        if (StringUtils.isNotBlank(timeZone)) {
            TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(HashtagsXRepApplication.class, args);
    }
}
