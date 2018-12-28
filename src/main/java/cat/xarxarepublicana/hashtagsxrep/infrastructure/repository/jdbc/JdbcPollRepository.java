package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
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

    @Override
    public Boolean hasProposal(String pollId, String authorId) {
        return pollMapper.existsProposal(pollId, authorId);
    }

    @Override
    public void addProposal(Proposal proposal) {
        pollMapper.insertProposal(proposal);
    }

    @Override
    public Proposal findProposal(String pollId, String authorId) {
        return pollMapper.selectOneProposalById(pollId, authorId);
    }

    @Override
    public List<Proposal> findPollProposals(String pollId) {
        return pollMapper.selectProposalsList(pollId);
    }
}
