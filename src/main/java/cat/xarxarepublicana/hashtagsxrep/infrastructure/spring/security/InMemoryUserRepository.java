package cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.logging.Logger;

@Component
public class InMemoryUserRepository {

    private static final Logger LOG = Logger.getLogger(InMemoryUserRepository.class.getName());

    private HashMap<String, UserAuth> userMap = new HashMap<>();

    public UserAuth findById(String id) {
        return userMap.get(id);
    }

    public void save(UserAuth user) {
        LOG.info(">>> save auth: " + user.getUsername());
        this.userMap.put(user.getId(), user);
    }
}
