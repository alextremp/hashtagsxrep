package cat.xarxarepublicana.hashtagsxrep.domain.core.error;

public class ValidationException extends RuntimeException {
  public ValidationException(String message) {
    super(message);
  }
}
