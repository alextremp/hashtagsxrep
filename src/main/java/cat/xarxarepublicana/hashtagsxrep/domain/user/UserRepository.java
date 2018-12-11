package cat.xarxarepublicana.hashtagsxrep.domain.user;

public interface UserRepository {

    User findById(String id);

    void save(User user);

}
