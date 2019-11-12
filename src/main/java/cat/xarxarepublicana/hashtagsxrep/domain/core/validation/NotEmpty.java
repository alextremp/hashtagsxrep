package cat.xarxarepublicana.hashtagsxrep.domain.core.validation;

import cat.xarxarepublicana.hashtagsxrep.domain.core.error.ValidationException;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;

public class NotEmpty {

  public static void validate(String value, String fieldName) {
    if (StringUtils.isEmpty(value)) {
      throw new ValidationException(fieldName + " cannot be empty");
    }
  }

  public static void validate(Collection<?> value, String fieldName) {
    if (value == null || value.isEmpty()) {
      throw new ValidationException(fieldName + " cannot be empty");
    }
  }
}
