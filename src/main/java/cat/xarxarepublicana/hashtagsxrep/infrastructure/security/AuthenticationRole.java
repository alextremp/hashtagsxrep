package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import cat.xarxarepublicana.hashtagsxrep.domain.user.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthenticationRole implements GrantedAuthority {

    private static final AuthenticationRole ROLE_CREATOR = new AuthenticationRole(Role.CREATOR);
    private static final AuthenticationRole ROLE_ADMIN = new AuthenticationRole(Role.ADMIN);
    private static final AuthenticationRole ROLE_TAGGER = new AuthenticationRole(Role.TAGGER);
    private static final AuthenticationRole ROLE_VIEWER = new AuthenticationRole(Role.VIEWER);

    private static Map<String, List<AuthenticationRole>> ROLE_ACCESS_MAP = new HashMap<String, List<AuthenticationRole>>() {{
        put(Role.CREATOR, Arrays.asList(ROLE_CREATOR, ROLE_ADMIN, ROLE_TAGGER, ROLE_VIEWER));
        put(Role.ADMIN, Arrays.asList(ROLE_ADMIN, ROLE_TAGGER, ROLE_VIEWER));
        put(Role.TAGGER, Arrays.asList(ROLE_TAGGER, ROLE_VIEWER));
        put(Role.VIEWER, Arrays.asList(ROLE_VIEWER));
    }};

    private String authority;

    private AuthenticationRole(String authority) {
        this.authority = authority;
    }

    public static List<AuthenticationRole> fromRole(String role) {
        return ROLE_ACCESS_MAP.get(role);
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
