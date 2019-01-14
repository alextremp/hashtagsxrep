package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.InviteMapper;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.PollMapper;

import java.util.List;

public class JdbcPollRepository implements PollRepository {

    private final PollMapper pollMapper;
    private final InviteMapper inviteMapper;

    public JdbcPollRepository(PollMapper pollMapper, InviteMapper inviteMapper) {
        this.pollMapper = pollMapper;
        this.inviteMapper = inviteMapper;
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
    public void saveProposal(Proposal proposal) {
        if (pollMapper.existsProposal(proposal.getPollId(), proposal.getAuthorId())) {
            pollMapper.updateProposal(proposal);
        } else {
            pollMapper.insertProposal(proposal);
        }
    }

    @Override
    public Proposal findProposal(String pollId, String authorId) {
        return pollMapper.selectOneProposalById(pollId, authorId);
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

    @Override
    public void deleteVote(String pollId, String userId) {
        pollMapper.deleteVote(pollId, userId);
    }

    @Override
    public List<Poll> findFinishedPollsWithNoMonitor() {
        return pollMapper.selectFinishedPollsWithNoMonitor();
    }

    @Override
    public Proposal findWinnerProposal(Poll poll) {
        return pollMapper.selectWinnerProposal(poll.getId());
    }

    @Override
    public void delete(Poll poll) {
        inviteMapper.deleteByPollId(poll.getId());
        pollMapper.deleteVotes(poll.getId());
        pollMapper.deleteProposals(poll.getId());
        pollMapper.delete(poll.getId());
    }

}
