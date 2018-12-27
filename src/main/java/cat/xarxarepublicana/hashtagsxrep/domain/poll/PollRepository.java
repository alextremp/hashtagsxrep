package cat.xarxarepublicana.hashtagsxrep.domain.poll;

import java.util.List;

public interface PollRepository {

    void save(Poll poll);

    List<Poll> findActive();

    Poll findById(String id);
}
