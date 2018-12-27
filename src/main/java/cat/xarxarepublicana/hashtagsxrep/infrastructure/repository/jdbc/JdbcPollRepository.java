package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.PollMapper;

import java.util.List;

public class JdbcPollRepository implements PollRepository {

    private final PollMapper pollMapper;

    public JdbcPollRepository(PollMapper pollMapper) {
        this.pollMapper = pollMapper;
    }

    @Override
    public void save(Poll poll) {
        pollMapper.insert(poll);
    }

    @Override
    public List<Poll> findActive() {
        return pollMapper.selectActive();
    }

    @Override
    public Poll findById(String id) {
        return pollMapper.selectOneById(id);
    }
}
