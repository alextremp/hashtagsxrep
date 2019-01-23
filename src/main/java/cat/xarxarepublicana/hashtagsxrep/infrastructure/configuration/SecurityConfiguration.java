package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    //TODO V2 : https://medium.com/@ard333/authentication-and-authorization-using-jwt-on-spring-webflux-29b81f813e78

    /*private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    private final ServerSecurityContextRepository serverSecurityContextRepository;

    @Autowired
    public SecurityConfiguration(
            ReactiveAuthenticationManager reactiveAuthenticationManager,
            ServerSecurityContextRepository serverSecurityContextRepository) {
        this.reactiveAuthenticationManager = reactiveAuthenticationManager;
        this.serverSecurityContextRepository = serverSecurityContextRepository;
    }*/


    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                .httpBasic().disable()
                .formLogin().loginPage(Views.URL_LOGIN)
                .and()
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(Views.URL_INDEX, Views.URL_LOGIN, Views.URL_LOGIN + "/**", "/connect/**").permitAll()
                .anyExchange().authenticated()
                //.authenticationManager(reactiveAuthenticationManager)
                //.securityContextRepository(serverSecurityContextRepository)
                //.securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .and().build();
    }
}
