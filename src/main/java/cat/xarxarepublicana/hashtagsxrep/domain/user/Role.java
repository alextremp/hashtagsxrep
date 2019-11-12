package cat.xarxarepublicana.hashtagsxrep.domain.user;

import cat.xarxarepublicana.hashtagsxrep.domain.core.type.FluentMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface Role {
  String VIEWER = "VIEWER";
  String TAGGER = "TAGGER";
  String ADMIN = "ADMIN";
  String CREATOR = "CREATOR";

  Map<String, List<String>> ACCESS_MAP = new FluentMap<String, List<String>>()
      .fluentPut(Role.CREATOR, Arrays.asList(CREATOR, ADMIN, TAGGER, VIEWER))
      .fluentPut(Role.ADMIN, Arrays.asList(ADMIN, TAGGER, VIEWER))
      .fluentPut(Role.TAGGER, Arrays.asList(TAGGER, VIEWER))
      .fluentPut(Role.VIEWER, Arrays.asList(VIEWER));
}
