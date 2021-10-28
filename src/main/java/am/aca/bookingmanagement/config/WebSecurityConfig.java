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
                .anyRequest().permitAll()
//                .antMatchers("/review").hasRole("USER")
//                .antMatchers("/history").hasRole("USER")
//                .antMatchers("/info").hasRole("USER")
//                .antMatchers("/login").anonymous()
//                .antMatchers("/login/partner").anonymous()
//                .antMatchers("/register").anonymous()
//                .antMatchers("/register/partner").anonymous()
//                .anyRequest().permitAll()
//                .antMatchers("/info/partner").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/filter").permitAll()
//                .antMatchers("/history/partner").permitAll()
                .and()
                .addFilterBefore(new JwtAuthorizationFilter(), BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}