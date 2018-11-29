package cat.xarxarepublicana.hashtagsxrep.domain.user;

import cat.xarxarepublicana.hashtagsxrep.domain.twitter.TwitterUser;

public interface UserRepository {

    User findById(String id);

    User save(TwitterUser user);

}
