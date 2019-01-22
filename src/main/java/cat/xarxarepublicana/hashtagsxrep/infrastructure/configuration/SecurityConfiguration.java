package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationContext;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.WebFilter;

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
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(Views.URL_INDEX, Views.URL_LOGIN, Views.URL_LOGIN + "/**", "/connect/**").permitAll()
                .anyExchange().authenticated()
                .and().formLogin().loginPage(Views.URL_LOGIN)
                .and().logout()
                .and().build();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UserDetailsService userDetailsService = userDetailsService();
        AuthenticationProvider authenticationProvider = authenticationProvider(userDetailsService);
        AuthenticationContext authenticationContext = authenticationContext(userDetailsService);
        AuthenticationManager authenticationManager = authenticationManager();
        Filter restoreUserFilter = restoreUserFilter(authenticationManager);

        http
                .authorizeRequests()
                .antMatchers(Views.URL_INDEX, Views.URL_LOGIN, Views.URL_LOGIN + "/**", "/connect/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .authenticationProvider(authenticationProvider)
                .formLogin().loginPage(Views.URL_LOGIN).permitAll()
                .and()
                .logout().permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();

        http.addFilterBefore(restoreUserFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationContext authenticationContext(UserDetailsService userDetailsService) {
        return new AuthenticationContext(userDetailsService, cookieName, secret);
    }

    @Bean
    public WebFilter restoreUserFilter(AuthenticationManager authenticationManager) {
        CookiedUserRestorationFilter authenticationTokenFilter = new CookiedUserRestorationFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManager);
        return authenticationTokenFilter;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new AuthenticationUserService(userRepository);
    }
}
