package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.local;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterUser;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> repository;

    public InMemoryUserRepository() {
        this(new ConcurrentHashMap<>());
    }

    public InMemoryUserRepository(Map<String, User> repository) {
        this.repository = repository;
    }

    @Override
    public User findById(String id) {
        return this.repository.get(id);
    }

    @Override
    public User save(TwitterUser twitterUser) {
        User user;
        User old = findById(twitterUser.getIdStr());
        if (old == null) {
            user = twitterUser.toUser();
            this.repository.put(twitterUser.getIdStr(), user);
        } else {
            old.updateFromTwitter(twitterUser);
            user = old;
            this.repository.put(twitterUser.getIdStr(), user);
        }
        return user;
    }
}
