package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.telegram;

import cat.xarxarepublicana.hashtagsxrep.domain.notice.NoticeRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.notice.PollClosedNotice;
import cat.xarxarepublicana.hashtagsxrep.domain.notice.ProposalSavedNotice;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.telegram.dto.SendMessageDTO;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.service.StringEscapeService;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

public class TelegramNoticeRepository implements NoticeRepository {

  private static final Logger LOG = Logger.getLogger(TelegramNoticeRepository.class.getName());

  private final FreeMarkerConfig freeMarkerConfig;
  private final String apiKey;
  private final String channel;
  private final StringEscapeService stringEscapeService;

  public TelegramNoticeRepository(
      FreeMarkerConfig freeMarkerConfig,
      String apiKey,
      String channel,
      StringEscapeService stringEscapeService) {
    this.freeMarkerConfig = freeMarkerConfig;
    this.apiKey = apiKey;
    this.channel = channel;
    this.stringEscapeService = stringEscapeService;
  }

  @Override
  public void publishPollClosedNotice(PollClosedNotice notice) {
    try {
      Map<String, Object> model = new HashMap<>();
      model.put("notice", notice);
      model.put("stringEscapeService", stringEscapeService);
      String message = FreeMarkerTemplateUtils.processTemplateIntoString(
          freeMarkerConfig.getConfiguration().getTemplate("telegram-poll-closed-notice.ftl"),
          model
      );
      HttpEntity<SendMessageDTO> request = new HttpEntity<>(new SendMessageDTO(
          channel,
          message
      ));

      LOG.info("Publishing: " + channel + " >>> " + message);
      RestTemplate restTemplate = new RestTemplate();

      restTemplate.postForLocation("https://api.telegram.org/bot" + apiKey + "/sendMessage", request, Void.class);
    } catch (RestClientException | IOException | TemplateException e) {
      LOG.log(Level.SEVERE, "Error publishing message", e);
    }
  }

  @Override
  public void publishProposalSavedNotice(ProposalSavedNotice notice) {
    try {
      Map<String, Object> model = new HashMap<>();
      model.put("notice", notice);
      model.put("stringEscapeService", stringEscapeService);
      String message = FreeMarkerTemplateUtils.processTemplateIntoString(
          freeMarkerConfig.getConfiguration().getTemplate("telegram-proposal-saved-notice.ftl"),
          model
      );
      HttpEntity<SendMessageDTO> request = new HttpEntity<>(new SendMessageDTO(
          channel,
          message
      ));

      LOG.info("Publishing: " + channel + " >>> " + message);
      RestTemplate restTemplate = new RestTemplate();

      restTemplate.postForLocation("https://api.telegram.org/bot" + apiKey + "/sendMessage", request, Void.class);
    } catch (RestClientException | IOException | TemplateException e) {
      LOG.log(Level.SEVERE, "Error publishing message", e);
    }
  }
}
