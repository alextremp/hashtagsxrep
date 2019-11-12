package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.PollRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Ranking;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.RankingRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.InviteMapper;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper.PollMapper;
import java.util.Collections;
import java.util.List;

public class JdbcPollRepository implements PollRepository {

  private final PollMapper pollMapper;
  private final InviteMapper inviteMapper;
  private final RankingRepository rankingRepository;

  public JdbcPollRepository(PollMapper pollMapper, InviteMapper inviteMapper, RankingRepository rankingRepository) {
    this.pollMapper = pollMapper;
    this.inviteMapper = inviteMapper;
    this.rankingRepository = rankingRepository;
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
    boolean votingClosed = poll.isVotingClosed();
    List<Proposal> proposalList = pollMapper.selectProposalsList(poll.getId(), votingClosed);
    if (votingClosed) {
      resolveDraws(proposalList);
    }
    return proposalList;
  }

  private void resolveDraws(List<Proposal> proposalList) {
    if (proposalList != null && proposalList.size() > 1 && proposalList.get(0).getVotes() == proposalList.get(1)
        .getVotes()) {
      int draws = 1;
      Ranking ranking = rankingRepository.loadRanking();
      int winner = 0;
      Integer winnerRanking = ranking.getRankByNickname(proposalList.get(winner).getAuthorNickname());
      if (winnerRanking == null) {
        winnerRanking = ranking.getTaggerScoreList().size();
      }
      Integer currentRanking;
      while (draws < proposalList.size()) {
        if (proposalList.get(draws).getVotes() == proposalList.get(0).getVotes()) {
          currentRanking = ranking.getRankByNickname(proposalList.get(draws).getAuthorNickname());
          if (currentRanking != null && currentRanking < winnerRanking) {
            winner = draws;
            winnerRanking = currentRanking;
          }
          draws++;
        } else {
          break;
        }
      }
      if (winner != 0) {
        Collections.swap(proposalList, 0, winner);
      }
    }
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
    List<Proposal> proposalList = findPollProposals(poll);
    if (proposalList.size() > 0) {
      return proposalList.get(0);
    } else {
      return null;
    }
  }

  @Override
  public void delete(Poll poll) {
    inviteMapper.deleteByPollId(poll.getId());
    pollMapper.deleteVotes(poll.getId());
    pollMapper.deleteProposals(poll.getId());
    pollMapper.delete(poll.getId());
  }
}
