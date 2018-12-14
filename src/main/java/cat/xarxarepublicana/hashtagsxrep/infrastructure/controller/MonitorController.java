package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.application.monitor.CreateMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.application.monitor.ListMonitorUseCase;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class MonitorController {

    private final CreateMonitorUseCase createMonitorUseCase;
    private final ListMonitorUseCase listMonitorUseCase;

    @Autowired
    public MonitorController(CreateMonitorUseCase createMonitorUseCase, ListMonitorUseCase listMonitorUseCase) {
        this.createMonitorUseCase = createMonitorUseCase;
        this.listMonitorUseCase = listMonitorUseCase;
    }

    @PostMapping("/monitor")
    @Secured("ROLE_CREATOR")
    public String createMonitor(
            @RequestParam("twitterQuery")
                    String twitterQuery,
            @RequestParam("startTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime startTime,
            @AuthenticationPrincipal
                    AuthenticationUser authenticationUser,
            Model model) {
        CreateMonitorUseCase.CreateMonitorResponse createMonitorResponse = createMonitorUseCase.createMonitor(authenticationUser.getUser(), twitterQuery, startTime);
        ListMonitorUseCase.ListMonitorResponse listMonitorResponse = listMonitorUseCase.listMonitor();
        model.addAttribute("monitorList", listMonitorResponse.getMonitorList());
        model.addAttribute("ok", "Monitor creat: " + createMonitorResponse.getMonitor().getTwitterQuery());
        return Views.VIEW_MONITOR;
    }

    @GetMapping("/monitor")
    @Secured("ROLE_ADMIN")
    public String listMonitors(Model model) {
        ListMonitorUseCase.ListMonitorResponse listMonitorResponse = listMonitorUseCase.listMonitor();
        model.addAttribute("monitorList", listMonitorResponse.getMonitorList());
        return Views.VIEW_MONITOR;
    }
}
