package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.local;

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
    public void saveLoggedUser(User user) {
        repository.put(user.getId(), user);
    }

    @Override
    public void saveExtractedUser(User user) {

    }
}
