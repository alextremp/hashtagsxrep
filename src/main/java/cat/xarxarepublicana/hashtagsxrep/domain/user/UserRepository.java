package cat.xarxarepublicana.hashtagsxrep.domain.user;

public interface UserRepository {

    User findById(String id);

    void saveLoggedUser(User user);

    void saveExtractedUser(User user);
}
