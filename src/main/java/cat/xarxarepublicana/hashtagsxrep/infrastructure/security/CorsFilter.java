package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple CORS filter
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter implements WebFilter {

    private static final Map<String, String> HEADERS = new HashMap<String, String>() {{
        put("Access-Control-Allow-Origin", "*");
        put("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        put("Access-Control-Allow-Headers", "x-requested-with");
        put("Access-Control-Max-Age", "3600");
    }};

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        return Mono.fromRunnable(() -> {
            serverWebExchange.getResponse().getHeaders().setAll(HEADERS);
            if (!"OPTIONS".equals(serverWebExchange.getRequest().getMethodValue())) {
                webFilterChain.filter(serverWebExchange);
            }
        });
    }
}
