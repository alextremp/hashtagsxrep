package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.telegram;

import cat.xarxarepublicana.hashtagsxrep.domain.notice.Notice;
import cat.xarxarepublicana.hashtagsxrep.domain.notice.NoticeRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.telegram.dto.SendMessageDTO;
import org.springframework.http.HttpEntity;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramNoticeRepository implements NoticeRepository {

    private static final Logger LOG = Logger.getLogger(TelegramNoticeRepository.class.getName());

    private final FreeMarkerConfig freeMarkerConfig;
    private final String apiKey;
    private final String channel;

    public TelegramNoticeRepository(FreeMarkerConfig freeMarkerConfig, String apiKey, String channel) {
        this.freeMarkerConfig = freeMarkerConfig;
        this.apiKey = apiKey;
        this.channel = channel;
    }

    @Override
    public void publish(Notice notice) {
        try {
            String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freeMarkerConfig.getConfiguration().getTemplate("telegram.ftl"),
                    notice
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

    public static void main(String args[]) throws Exception {
        String message = "#hastagdeprova\n" +
                "\n" +
                "TEMA:\n" +
                "\n" +
                "ashh ahs h ha shd ahs dha sdhas dhas dhas dhas dha dhas dhas d\n" +
                "\n" +
                "INSTRUCCIONS:\n" +
                "\n" +
                ":ballot_box_with_check: Preparar 3 tuits amb el hashtag escollit i publicar-los a les 09:00h.\n" +
                "\n" +
                ":clock9: A LES 09:00h :bangbang:\n" +
                "\n" +
                ":ballot_box_with_check: Clicar sobre el hashtag, llegir els tuits i fer molts RT a tuits independentistes.\n" +
                "\n" +
                ":ballot_box_with_check: Penja comentaris, afegint el Hashtag, a tweets dels comptes que segueixes!\n" +
                "\n" +
                ":ballot_box_with_check: Fer nous tuits, RT amb comentari afegint el hashtag.\n" +
                "\n" +
                ":no_entry_sign: Mai repetir el mateix comentari.\n" +
                "\n" +
                ":no_entry_sign: No fer RT ni comentar mai afegint el hashtag sol.\n" +
                "\n" +
                ":no_entry_sign:  Només fer like no suma.\n" +
                "\n" +
                ":warning: Comenteu tweets dels usuaris que seguiu afegint el hashtag! Així acabarem sent més!\n" +
                "\n" +
                ":information_source: Aquest missatge s'ha generat automàticament quan ha acabat l'enquesta:\n" +
                "https://hashtagsxrep.herokuapp.com/poll/29529847-1efd-4c56-8e00-03b137dcdd10\n" +
                "\n" +
                ":information_source: Podreu seguir l'evolució del hashtag\n" +
                "https://hashtagsxrep.herokuapp.com/report/hastagdeprova\n" +
                "\n" +
                ":information_source: RECORDA: a les 09:00h comencem!!!\n" +
                "\n" +
                "#hastagdeprova";

        HttpEntity<SendMessageDTO> request = new HttpEntity<>(new SendMessageDTO(
                "@testrepcat01",
                message
        ));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation("https://api.telegram.org/bot711604476:AAFbBthgLKgx2GzsuVbuzZGOKvOtRvcsW1I/sendMessage", request, Void.class);
    }
}
