package cat.xarxarepublicana.hashtagsxrep.application.group;

import cat.xarxarepublicana.hashtagsxrep.application.group.response.GetMembersRankingResponse;
import cat.xarxarepublicana.hashtagsxrep.domain.core.error.EntityNotFoundException;
import cat.xarxarepublicana.hashtagsxrep.domain.group.Group;
import cat.xarxarepublicana.hashtagsxrep.domain.group.GroupRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Ranking;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.RankingRepository;
import cat.xarxarepublicana.hashtagsxrep.domain.ranking.Score;
import cat.xarxarepublicana.hashtagsxrep.domain.user.User;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetMembersRankingUseCase {

  private final GroupRepository groupRepository;
  private final RankingRepository rankingRepository;
  private final UserRepository userRepository;

  @Autowired
  public GetMembersRankingUseCase(
      GroupRepository groupRepository,
      RankingRepository rankingRepository,
      UserRepository userRepository) {
    this.groupRepository = groupRepository;
    this.rankingRepository = rankingRepository;
    this.userRepository = userRepository;
  }

  public GetMembersRankingResponse listRankedUsers(String groupId) {
    Group group = groupRepository.findById(groupId);
    if (group == null) {
      throw new EntityNotFoundException("Grup no trobat: " + groupId);
    }
    Ranking ranking = rankingRepository.loadRanking();
    List<User> memberList = userRepository.findByGroupId(groupId);

    List<Score> memberScoreList = ranking.getTaggerScoreList()
        .stream()
        .filter(score -> memberList
            .stream()
            .anyMatch(user -> user.getNickname().equals(score.getNickname())))
        .collect(Collectors.toList());
    List<Score> notRankedList = memberList.stream()
        .filter(user -> !memberScoreList.stream().anyMatch(score -> score.getNickname().equals(user.getNickname())))
        .map(user -> new Score(user.getNickname(), 0))
        .collect(Collectors.toList());
    memberScoreList.addAll(notRankedList);

    Ranking membersRanking = new Ranking(memberScoreList);
    return new GetMembersRankingResponse(group, membersRanking);
  }
}
