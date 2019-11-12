package cat.xarxarepublicana.hashtagsxrep.domain.core.error;

public class LoginError extends DomainError {

  public LoginError(String message) {
    super(message);
  }

  public LoginError(String message, Throwable cause) {
    super(message, cause);
  }
}
