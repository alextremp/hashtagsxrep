package cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security;

import cat.xarxarepublicana.hashtagsxrep.domain.user.ACCESS;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationRole implements GrantedAuthority {

    private static final AuthenticationRole ROLE_CREATOR = new AuthenticationRole(ACCESS.CREATOR.name());
    private static final AuthenticationRole ROLE_ADMIN = new AuthenticationRole(ACCESS.ADMIN.name());
    private static final AuthenticationRole ROLE_TAGGER = new AuthenticationRole(ACCESS.TAGGER.name());
    private static final AuthenticationRole ROLE_VIEWER = new AuthenticationRole(ACCESS.VIEWER.name());

    private static Map<ACCESS, List<AuthenticationRole>> ACCESS_MAP = new HashMap<ACCESS, List<AuthenticationRole>>() {{
        put(ACCESS.CREATOR, Arrays.asList(ROLE_CREATOR, ROLE_ADMIN, ROLE_TAGGER, ROLE_VIEWER));
        put(ACCESS.ADMIN, Arrays.asList(ROLE_ADMIN, ROLE_TAGGER, ROLE_VIEWER));
        put(ACCESS.TAGGER, Arrays.asList(ROLE_TAGGER, ROLE_VIEWER));
        put(ACCESS.VIEWER, Arrays.asList(ROLE_VIEWER));
    }};

    private String authority;

    private AuthenticationRole(String authority) {
        this.authority = authority;
    }

    public static List<AuthenticationRole> fromAccess(ACCESS access) {
        return ACCESS_MAP.get(access);
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
