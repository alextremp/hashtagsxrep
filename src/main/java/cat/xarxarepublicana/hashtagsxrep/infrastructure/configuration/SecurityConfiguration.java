package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    //TODO V2 : https://medium.com/@ard333/authentication-and-authorization-using-jwt-on-spring-webflux-29b81f813e78

    private final ReactiveAuthenticationManager reactiveAuthenticationManager;
    private final ServerSecurityContextRepository serverSecurityContextRepository;

    @Autowired
    public SecurityConfiguration(
            ReactiveAuthenticationManager reactiveAuthenticationManager,
            ServerSecurityContextRepository serverSecurityContextRepository) {
        this.reactiveAuthenticationManager = reactiveAuthenticationManager;
        this.serverSecurityContextRepository = serverSecurityContextRepository;
    }


    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                .httpBasic().disable()
                .authenticationManager(reactiveAuthenticationManager)
                //.securityContextRepository(serverSecurityContextRepository)
                //.securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange()
                //.pathMatchers(HttpMethod.OPTIONS).permitAll()
                //.pathMatchers(Views.URL_INDEX, Views.URL_LOGIN, Views.URL_LOGIN + "/**", "/connect/**").permitAll()
                .anyExchange().authenticated()
                .and().formLogin().loginPage(Views.URL_LOGIN)
                .and().logout()
                .and().build();
    }
}
