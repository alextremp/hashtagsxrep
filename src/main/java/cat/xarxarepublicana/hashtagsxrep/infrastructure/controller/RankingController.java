package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.ranking.GetTaggersRankingUseCase;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RankingController {

  private final GetTaggersRankingUseCase getTaggersRankingUseCase;

  @Autowired
  public RankingController(GetTaggersRankingUseCase getTaggersRankingUseCase) {
    this.getTaggersRankingUseCase = getTaggersRankingUseCase;
  }

  @GetMapping(Views.URL_RANKING)
  public String ranking(
      Model model,
      @AuthenticationPrincipal
          AuthenticationUser authenticationUser
  ) {
    GetTaggersRankingUseCase.GetTaggersRankingResponse getTaggersRankingResponse =
        getTaggersRankingUseCase.getTaggersRanking(authenticationUser.getUser());
    model.addAttribute("getTaggersRankingResponse", getTaggersRankingResponse);
    model.addAttribute("user", authenticationUser.getUser());
    return Views.VIEW_RANKING;
  }
}
