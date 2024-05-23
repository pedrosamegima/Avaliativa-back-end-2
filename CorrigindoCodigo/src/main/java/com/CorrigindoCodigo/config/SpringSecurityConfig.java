package com.CorrigindoCodigo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SpringSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http
		.csrf().disable()
		.authorizeHttpRequests((requests)-> requests

				.requestMatchers(
						HttpMethod.POST,"/aluno/", "/turma/")
				.permitAll()
				.requestMatchers(
						HttpMethod.GET,"/aluno/", "/turma/")
				.permitAll()
				.requestMatchers(
						HttpMethod.DELETE,"/aluno/", "/turma/{id}")
				.permitAll()
				.requestMatchers(
						HttpMethod.PUT,"/turma/**")
				.permitAll()				
				.anyRequest()
				.authenticated()	

				)
		.httpBasic();
		return http.build();

	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService(){
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("Pedro")
				.password("senai")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);

	}

}

