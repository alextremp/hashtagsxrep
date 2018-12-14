package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.report.GetTwitterQueryReportUseCase;
import org.springframework.beans.factory.annotation.Autowired;
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
            @PathVariable("twitterQuery") String twitterQueryWithoutHashtag,
            Model model
    ) {
        String twitterQuery = "#" + twitterQueryWithoutHashtag;
        GetTwitterQueryReportUseCase.GetTwitterQueryReport getTwitterQueryReport = getTwitterQueryReportUseCase.getTwitterQueryReport(twitterQuery);
        model.addAttribute("report", getTwitterQueryReport.getReport());
        model.addAttribute("monitor", getTwitterQueryReport.getMonitor());
        return Views.VIEW_REPORT;
    }


}
