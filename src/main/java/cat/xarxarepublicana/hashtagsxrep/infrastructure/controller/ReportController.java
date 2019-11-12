package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.report.GetTwitterQueryReportUseCase;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReportController {

  private final GetTwitterQueryReportUseCase getTwitterQueryReportUseCase;

  @Autowired
  public ReportController(GetTwitterQueryReportUseCase getTwitterQueryReportUseCase) {
    this.getTwitterQueryReportUseCase = getTwitterQueryReportUseCase;
  }

  @GetMapping("/report/{twitterQuery}")
  public String twitterQueryReport(
      @PathVariable("twitterQuery")
          String twitterQueryWithoutHashtag,
      @AuthenticationPrincipal
          AuthenticationUser authenticationUser,
      Model model
  ) {
    String twitterQuery = "#" + twitterQueryWithoutHashtag;
    GetTwitterQueryReportUseCase.GetTwitterQueryReport getTwitterQueryReport =
        getTwitterQueryReportUseCase.getTwitterQueryReport(twitterQuery);
    model.addAttribute("report", getTwitterQueryReport.getReport());
    model.addAttribute("monitor", getTwitterQueryReport.getMonitor());
    model.addAttribute("user", authenticationUser.getUser());
    return Views.VIEW_REPORT;
  }
}
