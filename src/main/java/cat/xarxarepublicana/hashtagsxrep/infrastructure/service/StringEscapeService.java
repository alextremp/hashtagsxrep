package cat.xarxarepublicana.hashtagsxrep.infrastructure.service;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

@Component
public class StringEscapeService {

    public String escapeHTML(String string) {
        return escape(StringEscapeUtils.escapeHtml4(string));
    }

    public String escape(String string) {
        return StringEscapeUtils.escapeJava(string);
    }

    public String unescape(String string) {
        return StringEscapeUtils.unescapeJava(string);
    }
}
