package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.telegram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageDTO {
  @JsonProperty("chat_id")
  private String chatId;
  @JsonProperty("text")
  private String text;
  @JsonProperty("parse_mode")
  private String parseMode = "HTML";
  @JsonProperty("disable_web_page_preview")
  private String disableWebPageOreview = "true";

  public SendMessageDTO(String chatId, String text) {
    this.chatId = chatId;
    this.text = text;
  }
}
