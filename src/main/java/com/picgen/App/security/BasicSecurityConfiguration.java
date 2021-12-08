package com.picgen.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private @Autowired UserDetailsServiceImplements service;

	@Bean
	public PasswordEncoder senhaEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/**").permitAll()
				.antMatchers(HttpMethod.GET, "/pay/{token}/{id_ordem}/PAY").permitAll()
				.antMatchers(HttpMethod.GET, "/api/v1/order/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/user/save").permitAll()
				.antMatchers(HttpMethod.PUT, "/api/v1/user/credentials").permitAll()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().cors()
			.and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(service);
		
		auth.inMemoryAuthentication().withUser("boaz").password(senhaEncoder().encode("boaz"))
				.authorities("ROLE_ADMIN");
	}
	
}
