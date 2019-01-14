package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.telegram.dto;

public class SendMessageDTO {
    private final String chat_id;
    private final String text;

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

    @Override
    public String toString() {
        return "SendMessageDTO{" +
                "chat_id='" + chat_id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
