package cat.xarxarepublicana.hashtagsxrep.infrastructure.repository.jdbc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InviteMapper {

    void insertAdmins(@Param("pollId") String pollId);

    void insertTaggers(@Param("pollId") String pollId);

    void insertTopRanking(@Param("pollId") String pollId);

    void insertRandomRanking(@Param("pollId") String pollId);

    void insertInfluencers(@Param("pollId") String pollId);

    void deleteByPollId(@Param("pollId") String pollId);
}
