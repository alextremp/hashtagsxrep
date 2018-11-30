package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.local;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterUser;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserFactory;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> repository;
    private final UserFactory userFactory;

    public InMemoryUserRepository(UserFactory userFactory) {
        this(new ConcurrentHashMap<>(), userFactory);
    }

    public InMemoryUserRepository(Map<String, User> repository, UserFactory userFactory) {
        this.repository = repository;
        this.userFactory = userFactory;
    }

    @Override
    public User findById(String id) {
        return this.repository.get(id);
    }

    @Override
    public User save(TwitterUser twitterUser, String userToken, String userSecret) {
        User user;
        User old = findById(twitterUser.getIdStr());
        if (old == null) {
            user = userFactory.createFromTwitter(twitterUser, userToken, userSecret);
            this.repository.put(twitterUser.getIdStr(), user);
        } else {
            old.updateFromTwitter(twitterUser);
            user = old;
            this.repository.put(twitterUser.getIdStr(), user);
        }
        return user;
    }
}
