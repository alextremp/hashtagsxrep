package cat.xarxarepublicana.hashtagsxrep.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Controller
public class MainController {

    private static final Logger LOG = Logger.getLogger(MainController.class.getName());

    @GetMapping("/")
    public Mono<String> index() {
        return Mono.fromRunnable(() -> LOG.info(">>> accessing index"))
                .then(Mono.just("index"));
    }

}
