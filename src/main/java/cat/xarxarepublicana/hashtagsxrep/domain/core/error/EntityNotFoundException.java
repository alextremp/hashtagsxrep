package cat.xarxarepublicana.hashtagsxrep.domain.core.error;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String message) {
    super(message);
  }
}
