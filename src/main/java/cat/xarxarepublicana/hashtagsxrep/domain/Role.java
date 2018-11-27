package cat.xarxarepublicana.hashtagsxrep.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Role implements GrantedAuthority {

    public static final Role ROLE_CREATOR = new Role("CREATOR");
    public static final Role ROLE_ADMIN = new Role("ADMIN");
    public static final Role ROLE_TAGGER = new Role("TAGGER");
    public static final Role ROLE_USER = new Role("USER");

    public static Map<String, List<Role>> ACCESS = new HashMap<String, List<Role>>() {{
        put(ROLE_CREATOR.getAuthority(), Arrays.asList(ROLE_CREATOR, ROLE_ADMIN, ROLE_TAGGER, ROLE_USER));
        put(ROLE_ADMIN.getAuthority(), Arrays.asList(ROLE_ADMIN, ROLE_TAGGER, ROLE_USER));
        put(ROLE_TAGGER.getAuthority(), Arrays.asList(ROLE_TAGGER, ROLE_USER));
        put(ROLE_USER.getAuthority(), Arrays.asList(ROLE_USER));
    }};

    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
