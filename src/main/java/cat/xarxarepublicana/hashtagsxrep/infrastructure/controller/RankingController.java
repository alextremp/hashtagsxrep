package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.ranking.GetTaggersRankingUseCase;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String ranking(Model model) {
        GetTaggersRankingUseCase.GetTaggersRankingResponse taggersRanking = getTaggersRankingUseCase.getTaggersRanking();
        model.addAttribute("ranking", taggersRanking.getRanking());
        return Views.VIEW_RANKING;
    }
}
