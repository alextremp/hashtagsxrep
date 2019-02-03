package cat.xarxarepublicana.hashtagsxrep.infrastructure.cache;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.JdbcUserRepository;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.List;

public class CachedUserRepository implements UserRepository {

    private final LoadingCache<String, User> cache;
    private final JdbcUserRepository jdbcUserRepository;

    public CachedUserRepository(LoadingCache<String, User> cache, JdbcUserRepository jdbcUserRepository) {
        this.cache = cache;
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @Override
    public User findById(String id) {
        return cache.get(id);
    }

    @Override
    public User findByNickname(String nickname) {
        return jdbcUserRepository.findByNickname(nickname);
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

    @Override
    public List<User> findByGroupId(String groupId) {
        return jdbcUserRepository.findByGroupId(groupId);
    }
}
