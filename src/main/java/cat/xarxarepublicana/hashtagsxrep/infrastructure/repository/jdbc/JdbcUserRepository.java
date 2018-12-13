package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.UserMapper;

public class JdbcUserRepository implements UserRepository {

    private final UserMapper userMapper;

    public JdbcUserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findById(String id) {
        return userMapper.selectOneById(id);
    }

    @Override
    public void saveLoggedUser(User user) {
        if (userMapper.exists(user.getId())) {
            userMapper.updateCredentialsData(user);
        } else {
            userMapper.insert(user);
        }
    }

    @Override
    public void saveExtractedUser(User user) {
        if (userMapper.exists(user.getId())) {
            userMapper.updateTwitterData(user);
        } else {
            userMapper.insert(user);
        }
    }
}
