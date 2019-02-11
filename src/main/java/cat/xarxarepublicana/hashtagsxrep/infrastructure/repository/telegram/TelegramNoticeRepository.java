package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.telegram;

import cat.xarxarepublicana.hashtagsxrep.domain.notice.*;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.telegram.dto.SendMessageDTO;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.service.StringEscapeService;
import org.springframework.http.HttpEntity;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramNoticeRepository implements NoticeRepository {

    private static final Logger LOG = Logger.getLogger(TelegramNoticeRepository.class.getName());

    private final FreeMarkerConfig freeMarkerConfig;
    private final String apiKey;
    private final String channel;
    private final StringEscapeService stringEscapeService;

    public TelegramNoticeRepository(FreeMarkerConfig freeMarkerConfig, String apiKey, String channel, StringEscapeService stringEscapeService) {
        this.freeMarkerConfig = freeMarkerConfig;
        this.apiKey = apiKey;
        this.channel = channel;
        this.stringEscapeService = stringEscapeService;
    }

    @Override
    public void publishPollClosedNotice(PollClosedNotice pollClosedNotice) {
        publish(pollClosedNotice, "telegram-poll-closed-notice.ftl");
    }

    @Override
    public void publishProposalSavedNotice(ProposalSavedNotice proposalSavedNotice) {
        publish(proposalSavedNotice, "telegram-proposal-saved-notice.ftl");
    }

    @Override
    public void publishJoiningUser(JoiningUserNotice joiningUserNotice) {
        publish(joiningUserNotice, "telegram-joining-user-notice.ftl");
    }

    private void publish(Notice notice, String template) {
        try {
            Map<String, Object> model = new HashMap<>();
            model.put("notice", notice);
            model.put("stringEscapeService", stringEscapeService);
            String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freeMarkerConfig.getConfiguration().getTemplate(template),
                    model
            );
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
