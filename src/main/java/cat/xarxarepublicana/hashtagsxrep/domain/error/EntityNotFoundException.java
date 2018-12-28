package cat.xarxarepublicana.hashtagsxrep.domain.error;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
