package cat.xarxarepublicana.hashtagsxrep.infrastructure.configuration;

import cat.xarxarepublicana.hashtagsxrep.application.Views;
import cat.xarxarepublicana.hashtagsxrep.domain.user.UserRepository;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthCookieService;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationContext;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.AuthenticationUserService;
import cat.xarxarepublicana.hashtagsxrep.infrastructure.security.RestoreUserFilter;
import javax.servlet.Filter;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${app.usertoken.cookiename}")
  private String cookieName;

  @Value("${app.usertoken.secret}")
  private String secret;

  @Autowired
  private UserRepository userRepository;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/ui/**", "/error");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    UserDetailsService userDetailsService = userDetailsService();
    AuthenticationProvider authenticationProvider = authenticationProvider(userDetailsService);
    AuthenticationManager authenticationManager = authenticationManager();
    Filter restoreUserFilter = restoreUserFilter(authenticationManager);

    http
        .authorizeRequests()
        .antMatchers(Views.URL_INDEX, Views.URL_LOGIN, Views.URL_LOGIN + "/**", "/connect/**", "/about").permitAll()
        .anyRequest().authenticated()
        .and()
        .authenticationProvider(authenticationProvider)
        .formLogin().loginPage(Views.URL_LOGIN).permitAll()
        .and()
        .logout().deleteCookies(cookieName).logoutSuccessUrl("/").permitAll()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().csrf().disable();

    http.addFilterBefore(restoreUserFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public AuthenticationContext authenticationContext(
      UserDetailsService userDetailsService,
      AuthCookieService authCookieService) {
    return new AuthenticationContext(userDetailsService, authCookieService);
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

  @Bean
  public AuthCookieService authCookieService() {
    return new AuthCookieService(cookieName, secret);
  }
}
