package com.challenge.ml.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.challenge.ml.dao.BookDAO;
import com.challenge.ml.entity.Book;
import com.challenge.ml.security.ChallengeAuthorizationFilter;



@SpringBootApplication
public class DemoApplication {
	BookDAO bookDAO;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		 
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new ChallengeAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/users").permitAll()
				.anyRequest().authenticated();
		}
	}
	
}
