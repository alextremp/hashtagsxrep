package cat.xarxarepublicana.hashtagsxrep.infrastructure.cache;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.JdbcUserRepository;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class CachedUserRepository implements UserRepository {

    private final LoadingCache<String, User> cache;
    private final JdbcUserRepository jdbcUserRepository;

    public CachedUserRepository(JdbcUserRepository jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
        this.cache = Caffeine.newBuilder()
            .expireAfterAccess(5, TimeUnit.MINUTES)
            .maximumSize(100)
            .build(id -> jdbcUserRepository.findById(id));
    }

    @Override
    public User findById(String id) {
        return cache.get(id);
    }

    @Override
    public void saveLoggedUser(User user) {
        jdbcUserRepository.saveLoggedUser(user);
        cache.invalidate(user.getId());
    }

    @Override
    public void saveExtractedUser(User user) {
        jdbcUserRepository.saveExtractedUser(user);
        cache.invalidate(user.getId());
    }
}
