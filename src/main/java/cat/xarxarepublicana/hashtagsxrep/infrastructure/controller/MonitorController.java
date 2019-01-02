package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.monitor.CreateMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.monitor.DeleteMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.monitor.ListMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@Controller
public class MonitorController {

    private final CreateMonitorUseCase createMonitorUseCase;
    private final ListMonitorUseCase listMonitorUseCase;
    private final DeleteMonitorUseCase deleteMonitorUseCase;

    @Autowired
    public MonitorController(CreateMonitorUseCase createMonitorUseCase, ListMonitorUseCase listMonitorUseCase, DeleteMonitorUseCase deleteMonitorUseCase) {
        this.createMonitorUseCase = createMonitorUseCase;
        this.listMonitorUseCase = listMonitorUseCase;
        this.deleteMonitorUseCase = deleteMonitorUseCase;
    }

    @PostMapping("/monitor")
    @Secured("ROLE_ADMIN")
    public RedirectView createMonitor(
            @RequestParam("twitterQuery")
                    String twitterQuery,
            @RequestParam("startTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime startTime,
            @AuthenticationPrincipal
                    AuthenticationUser authenticationUser) {
        CreateMonitorUseCase.CreateMonitorResponse createMonitorResponse = createMonitorUseCase.createMonitor(authenticationUser.getUser(), twitterQuery, startTime);
        return new RedirectView(Views.URL_MONITOR);
    }

    @GetMapping("/monitor")
    public String listMonitors(Model model) {
        ListMonitorUseCase.ListMonitorResponse listMonitorResponse = listMonitorUseCase.listMonitor();
        model.addAttribute("monitorList", listMonitorResponse.getMonitorList());
        return Views.VIEW_MONITOR;
    }


    @PostMapping("/monitor/{monitorId}/delete")
    @Secured("ROLE_ADMIN")
    public RedirectView monitorDelete(
            @PathVariable("monitorId") String monitorId,
            @RequestParam("hashtag") String hashtag
    ) {
        deleteMonitorUseCase.deleteMonitor(monitorId, hashtag);
        return new RedirectView(Views.URL_MONITOR);
    }

}
