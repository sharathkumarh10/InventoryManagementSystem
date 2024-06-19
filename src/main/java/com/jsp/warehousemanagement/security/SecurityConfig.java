package com.jsp.warehousemanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jsp.warehousemanagement.filter.ApiKeyFilter;
import com.jsp.warehousemanagement.repository.ClientRepository;
@EnableMethodSecurity
@Configuration
 @EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private ClientRepository clientRepo;

	@Bean
	 PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder(12);
	 }
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(csrf->csrf.disable())
				.securityMatchers( matcher -> matcher.requestMatchers("/api/v2/**","/login/**"))
				.authorizeHttpRequests(authorize->authorize.requestMatchers("/api/v2/register")
						.permitAll()
						.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.build();
		
	}
	
	@Bean
	@Order(1)
	SecurityFilterChain apiKeyFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		return httpSecurity.csrf(csrf->csrf.disable())
				.securityMatchers(matcher->matcher.requestMatchers("/api/v2/client/**"))
				.authorizeHttpRequests(authorize->authorize.anyRequest().permitAll())
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(new ApiKeyFilter(clientRepo) , UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}
}
