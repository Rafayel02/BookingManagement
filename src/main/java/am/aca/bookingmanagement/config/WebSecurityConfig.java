package am.aca.bookingmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/review").hasRole("USER")
                .antMatchers("/filter").permitAll()

                .antMatchers("/register/partner").anonymous()
                .antMatchers("/register").anonymous()

                .antMatchers("/info/partner").permitAll()
                .antMatchers("/info").hasRole("USER")

                .antMatchers("/history/partner").permitAll()
                .antMatchers("/history").hasRole("USER")

                .antMatchers("/login/partner").anonymous()
                .antMatchers("/login").anonymous()

                .antMatchers("/").permitAll()

                .anyRequest().permitAll()
                .and()
                .addFilterBefore(new JwtAuthorizationFilter(), BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}