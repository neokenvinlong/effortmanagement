package com.restful.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity // Allows us to have method level access control
@EnableGlobalMethodSecurity(prePostEnabled = true) // Allows us to have method level access control
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @SuppressWarnings(value = "deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        auth
        .userDetailsService(jwtUserDetailsService)
        .passwordEncoder(passwordEncoder());

        auth.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("Select name, role from account where name = ?");
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select name, Password from account where name = ?")
//                .authoritiesByUsernameQuery("select name, Role  from account where name = ?")
//        .passwordEncoder(passwordEncoder())
//        .getUserDetailsService(jwtUserDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/authenticate").permitAll()
                .and().authorizeRequests().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**").permitAll()
//                .antMatchers("/me").permitAll()
//                .antMatchers(HttpMethod.PUT, "/tasks/**").hasRole("PM")
//                .antMatchers(HttpMethod.DELETE, "/tasks/**").hasRole("PM")
//                .antMatchers(HttpMethod.POST, "/tasks/**").hasRole("PM")
//                .antMatchers(HttpMethod.GET,"/tasks/**").permitAll()
//                .antMatchers(HttpMethod.PATCH,"/tasks/**").hasRole("PM")
//                .antMatchers(HttpMethod.GET, "/employees/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/employees/**").hasRole("PM")
//                .antMatchers(HttpMethod.PUT, "/employees/**").permitAll()
//                .antMatchers(HttpMethod.DELETE,"/employees/**").hasRole("PM")
//                .antMatchers(HttpMethod.PATCH,"/efforts/**").hasRole("PM")
//                .antMatchers(HttpMethod.PUT,"/efforts/**").hasRole("EMPLOYEE")
//                .antMatchers(HttpMethod.GET,"/efforts/**").permitAll()
//                .antMatchers(HttpMethod.POST,"/efforts/**").hasRole("EMPLOYEE")
//                .antMatchers( "/projects/**").permitAll()
                // all other requests need to be authenticated
                        .anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
