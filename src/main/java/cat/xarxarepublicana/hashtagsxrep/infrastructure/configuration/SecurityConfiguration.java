package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationContext;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUser;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUserService;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.RestoreUserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${app.usertoken.cookiename}")
    private String cookieName;

    @Value("${app.usertoken.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/ui/**", "/error").and().debug(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UserDetailsService userDetailsService = userDetailsService();
        AuthenticationProvider authenticationProvider = authenticationProvider(userDetailsService);
        AuthenticationContext authenticationContext = authenticationContext(userDetailsService);
        AuthenticationSuccessHandler authenticationSuccessHandler = authenticationSuccessHandler(authenticationContext);
        AuthenticationManager authenticationManager = authenticationManager();
        Filter restoreUserFilter = restoreUserFilter(authenticationManager);

        http
                .authorizeRequests()
                .antMatchers(Views.INDEX, Views.LOGIN, Views.LOGIN + "/**", "/connect/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .authenticationProvider(authenticationProvider)
                .formLogin().loginPage(Views.LOGIN).successHandler(authenticationSuccessHandler).permitAll()
                .and()
                .logout().permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();

        http.addFilterBefore(restoreUserFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(AuthenticationContext authenticationContext) {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                AuthenticationUser authenticationUser = (AuthenticationUser) authentication.getPrincipal();
                authenticationContext.put(authenticationUser, response);
            }
        };
    }

    @Bean
    public AuthenticationContext authenticationContext(UserDetailsService userDetailsService) {
        return new AuthenticationContext(userDetailsService, cookieName, secret);
    }

    @Bean
    public Filter restoreUserFilter(AuthenticationManager authenticationManager) {
        RestoreUserFilter authenticationTokenFilter = new RestoreUserFilter();
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
