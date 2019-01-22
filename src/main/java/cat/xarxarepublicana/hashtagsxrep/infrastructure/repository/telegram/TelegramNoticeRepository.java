package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.telegram;

import cat.xarxarepublicana.hashtagsxrep.domain.notice.Notice;
import cat.xarxarepublicana.hashtagsxrep.domain.notice.NoticeRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.telegram.dto.SendMessageDTO;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.service.StringEscapeService;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramNoticeRepository implements NoticeRepository {

    private static final Logger LOG = Logger.getLogger(TelegramNoticeRepository.class.getName());

    private final String apiKey;
    private final String channel;
    private final StringEscapeService stringEscapeService;

    public TelegramNoticeRepository(String apiKey, String channel, StringEscapeService stringEscapeService) {
        this.apiKey = apiKey;
        this.channel = channel;
        this.stringEscapeService = stringEscapeService;
    }

    @Override
    public void publish(Notice notice) {
        try {
            Map<String, Object> model = new HashMap<>();
            model.put("notice", notice);
            model.put("stringEscapeService", stringEscapeService);
            // TODO V2 : do a telegram notice template without the need of freemarker dependency
            String message = "";
//            String message = FreeMarkerTemplateUtils.processTemplateIntoString(
//                    freeMarkerConfig.getConfiguration().getTemplate("telegram.ftl"),
//                    model
//            );
            HttpEntity<SendMessageDTO> request = new HttpEntity<>(new SendMessageDTO(
                    channel,
                    message
            ));

            LOG.info("Publishing: " + channel + " >>> " + message);
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.postForLocation("https://api.telegram.org/bot" + apiKey + "/sendMessage", request, Void.class);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error publishing message", e);
        }
    }
}
