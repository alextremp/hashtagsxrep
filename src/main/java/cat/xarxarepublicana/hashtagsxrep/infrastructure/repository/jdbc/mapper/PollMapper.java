package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PollMapper {

    void insert(@Param("poll") Poll poll);

    Poll selectOneById(@Param("id") String id);

    List<Poll> selectLastStarted(@Param("quantity") Integer quantity);

    Boolean existsProposal(@Param("pollId") String pollId, @Param("authorId") String authorId);

    Proposal selectOneProposalById(@Param("pollId") String pollId, @Param("authorId") String authorId);

    Proposal selectVotedProposal(@Param("pollId") String pollId, @Param("voterId") String voterId);

    void insertProposal(@Param("proposal") Proposal proposal);

    List<Proposal> selectProposalsList(@Param("pollId") String pollId, @Param("orderVotes") Boolean orderVotes);

    void insertVote(@Param("proposal") Proposal proposal, @Param("voter") User voter);

    List<Poll> selectFinishedPollsWithNoMonitor();

    Proposal selectWinnerProposal(@Param("pollId") String pollId);
}
