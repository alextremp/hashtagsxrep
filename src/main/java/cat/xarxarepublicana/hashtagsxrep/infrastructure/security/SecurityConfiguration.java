package cat.xarxarepublicana.hashtagsxrep.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
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
        return http.authorizeExchange()
                //.pathMatchers("/ui/**", "/favicon.ico", "/", "/login", "/login/**", "/connect/**", "/error").permitAll()
                .pathMatchers("/**").permitAll()
                .pathMatchers("/api/**").authenticated()
                .anyExchange().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable()
                .authenticationManager(reactiveAuthenticationManager)
                .securityContextRepository(serverSecurityContextRepository)
                .build();
    }
}
