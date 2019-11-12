package cat.xarxarepublicana.hashtagsxrep.domain.poll;

import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import java.util.List;

public interface PollRepository {

  void save(Poll poll);

  List<Poll> findLast();

  Poll findById(String id);

  Boolean hasProposal(String pollId, String authorId);

  void saveProposal(Proposal proposal);

  Proposal findProposal(String pollId, String authorId);

  List<Proposal> findPollProposals(Poll poll);

  void addVote(Proposal proposal, User voter);

  Proposal findUserVote(Poll poll, String voterId);

  List<Poll> findFinishedPollsWithNoMonitor();

  Proposal findWinnerProposal(Poll poll);

  void delete(Poll poll);

  void deleteVote(String pollId, String userId);
}
