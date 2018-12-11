package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class PoCController {

    private static final Logger LOG = Logger.getLogger(PoCController.class.getName());

    public PoCController() {
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
