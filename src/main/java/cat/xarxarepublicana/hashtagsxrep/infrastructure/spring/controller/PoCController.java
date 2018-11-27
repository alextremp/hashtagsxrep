package cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.controller;

import cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security.UserAuth;
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
    public String index(Model model, @AuthenticationPrincipal UserAuth userAuth) {
        if (userAuth != null) {
            model.addAttribute("user", userAuth.getUser());
        }
        return "index";
    }

    @GetMapping("/hello")
    public String hello(Model model, @AuthenticationPrincipal UserAuth userAuth) {
        model.addAttribute("name", userAuth.getUser().getName());
        model.addAttribute("profileImage", userAuth.getUser().getProfileImageUrl());
        model.addAttribute("nickname", userAuth.getUser().getNickname());
        return "hello";
    }

}
