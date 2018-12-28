package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import cat.xarxarepublicana.hashtagsxrep.domain.poll.Poll;
import cat.xarxarepublicana.hashtagsxrep.domain.poll.Proposal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PollMapper {

    void insert(@Param("poll") Poll poll);

    Poll selectOneById(@Param("id") String id);

    List<Poll> selectActive();

    Boolean existsProposal(@Param("pollId") String pollId, @Param("authorId") String authorId);

    Proposal selectOneProposalById(@Param("pollId") String pollId, @Param("authorId") String authorId);

    void insertProposal(@Param("proposal") Proposal proposal);

    List<Proposal> selectProposalsList(@Param("pollId") String pollId);
}
