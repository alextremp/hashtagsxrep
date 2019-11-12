package cat.xarxarepublicana.hashtagsxrep.domain.core.validation;

import cat.xarxarepublicana.hashtagsxrep.domain.core.error.ValidationException;

public class NotNull {

  public static void validate(Object value, String fieldName) {
    if (value == null) {
      throw new ValidationException(fieldName + " cannot be null");
    }
  }
}
