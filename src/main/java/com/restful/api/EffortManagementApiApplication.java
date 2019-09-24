package com.restful.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableJpaAuditing
@EnableWebSecurity
@Configuration
public class EffortManagementApiApplication  extends WebSecurityConfigurerAdapter {


	public static void main(String[] args) {
		SpringApplication.run(EffortManagementApiApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http

	.csrf().disable()
				.httpBasic()
				.and()
				.authorizeRequests().antMatchers("/swagger-resources/**").permitAll().
		antMatchers("/swagger-ui.html").permitAll()
				.anyRequest().permitAll();
	}


}
