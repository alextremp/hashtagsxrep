package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalAttributesController {

    @ModelAttribute
    public void globalAttributes(
            Model model,
            @AuthenticationPrincipal
                AuthenticationUser authenticationUser) {
        if (authenticationUser != null) {
            model.addAttribute("user", authenticationUser.getUser());
        }
    }
}
