package cat.xarxarepublicana.hashtagsxrep.domain.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeConverter {

  public static LocalDateTime toLocalDateTime(Date date) {
    return date != null ? date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime() : null;
  }
}
