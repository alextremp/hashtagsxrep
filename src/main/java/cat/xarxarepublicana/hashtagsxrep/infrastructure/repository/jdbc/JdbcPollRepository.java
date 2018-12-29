package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
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
    public List<Poll> findLast() {
        return pollMapper.selectLastStarted(10);
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
    public Proposal findProposal(Poll poll, String authorId) {
        return pollMapper.selectOneProposalById(poll.getId(), authorId);
    }

    @Override
    public List<Proposal> findPollProposals(Poll poll) {
        return pollMapper.selectProposalsList(poll.getId(), poll.isVotingClosed());
    }

    @Override
    public void addVote(Proposal proposal, User voter) {
        pollMapper.insertVote(proposal, voter);
    }

    @Override
    public Proposal findUserVote(Poll poll, String voterId) {
        return pollMapper.selectVotedProposal(poll.getId(), voterId);
    }
}
