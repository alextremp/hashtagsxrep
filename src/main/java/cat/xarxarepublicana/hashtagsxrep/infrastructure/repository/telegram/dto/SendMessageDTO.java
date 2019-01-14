package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.telegram.dto;

public class SendMessageDTO {
    private final String chat_id;
    private final String text;
    private final String parse_mode = "HTML";
    private final String disable_web_page_preview = "true";

    public SendMessageDTO(String chat_id, String text) {
        this.chat_id = chat_id;
        this.text = text;
    }

    public String getChat_id() {
        return chat_id;
    }

    public String getText() {
        return text;
    }

    public String getParse_mode() {
        return parse_mode;
    }

    public String getDisable_web_page_preview() {
        return disable_web_page_preview;
    }

    @Override
    public String toString() {
        return "SendMessageDTO{" +
                "chat_id='" + chat_id + '\'' +
                ", text='" + text + '\'' +
                ", parse_mode='" + parse_mode + '\'' +
                ", disable_web_page_preview='" + disable_web_page_preview + '\'' +
                '}';
    }
}
