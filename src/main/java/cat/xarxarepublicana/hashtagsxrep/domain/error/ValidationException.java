package cat.xarxarepublicana.hashtagsxrep.domain.error;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
