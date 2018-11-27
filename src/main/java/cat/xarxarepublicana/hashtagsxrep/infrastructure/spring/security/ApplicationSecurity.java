package cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security;

import cat.xarxarepublicana.hashtagsxrep.infrastructure.spring.security.UserAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

  @Value("${app.usertoken.cookiename}")
  private String cookieName;

  @Value("${app.usertoken.secret}")
  private String secret;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/", "/ui/**", "/login", "/login/**", "/connect/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .authenticationProvider(authenticationProvider(userDetailsService()))
        .formLogin()
        .loginPage("/login").successHandler(successHandler(securityContext(userDetailsService())))
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and().csrf().disable();

    http.addFilterBefore(restoreUserFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public AuthenticationSuccessHandler successHandler(SecurityContext securityContext) {
    return new SavedRequestAwareAuthenticationSuccessHandler() {
      @Override
      public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        securityContext.put(userAuth, response);
      }
    };
  }

  @Bean
  public SecurityContext securityContext(UserDetailsService userDetailsService) {
    return new SecurityContext(userDetailsService, cookieName, secret);
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
    return new SecurityUserDetailsService();
  }
}
