package cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.controller;

import cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security.AuthenticationUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class PoCController {

    private static final Logger LOG = Logger.getLogger(PoCController.class.getName());

    public PoCController() {
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal AuthenticationUser authenticationUser) {
        if (authenticationUser != null) {
            model.addAttribute("user", authenticationUser.getUser());
        }
        return "index";
    }

    @GetMapping("/hello")
    public String hello(Model model, @AuthenticationPrincipal AuthenticationUser authenticationUser) {
        model.addAttribute("name", authenticationUser.getUser().getName());
        model.addAttribute("profileImage", authenticationUser.getUser().getProfileImageUrl());
        model.addAttribute("nickname", authenticationUser.getUser().getNickname());
        return "hello";
    }

}
