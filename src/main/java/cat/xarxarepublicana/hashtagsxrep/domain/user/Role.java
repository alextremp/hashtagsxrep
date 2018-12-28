package cat.xarxarepublicana.hashtagsxrep.domain.user;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Role {
    String VIEWER = "VIEWER";
    String TAGGER = "TAGGER";
    String ADMIN = "ADMIN";
    String CREATOR = "CREATOR";

    Map<String, List<String>> ACCESS_MAP = new HashMap<String, List<String>>() {{
        put(Role.CREATOR, Arrays.asList(CREATOR, ADMIN, TAGGER, VIEWER));
        put(Role.ADMIN, Arrays.asList(ADMIN, TAGGER, VIEWER));
        put(Role.TAGGER, Arrays.asList(TAGGER, VIEWER));
        put(Role.VIEWER, Arrays.asList(VIEWER));
    }};
}
