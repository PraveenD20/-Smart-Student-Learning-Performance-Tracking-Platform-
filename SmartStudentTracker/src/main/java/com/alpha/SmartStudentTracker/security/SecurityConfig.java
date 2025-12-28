package com.alpha.SmartStudentTracker.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	 @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//	        http
//	            .csrf(csrf -> csrf.disable())
//	            .authorizeHttpRequests(auth -> auth
//	                .requestMatchers("/admin/**").hasRole("ADMIN")
//	                .requestMatchers("/trainer/**").hasRole("TRAINER")
//	                .requestMatchers("/student/**").hasRole("STUDENT")
//	                .anyRequest().authenticated()
//	            )
////	            .httpBasic();
//	            .httpBasic(httpBasic -> {});
//
//
//	        return http.build();
//	    }
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth

	            // ✅ Allow login endpoints without authentication
	            .requestMatchers(
	                "/student/login",
	                "/admin/login",
	                "/trainer/login"
	            ).permitAll()

	            // 🔒 Role-based access
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .requestMatchers("/trainer/**").hasRole("TRAINER")
	            .requestMatchers("/student/**").hasRole("STUDENT")

	            // 🔒 Everything else needs authentication
	            .anyRequest().authenticated()
	        )
	        .httpBasic(httpBasic -> {});

	    return http.build();
	}


	    @Bean
	    public UserDetailsService userDetailsService() {

	        UserDetails admin = User.withUsername("admin")
	                .password("{noop}admin123")
	                .roles("ADMIN")
	                .build();

	        UserDetails trainer = User.withUsername("trainer")
	                .password("{noop}trainer123")
	                .roles("TRAINER")
	                .build();

	        UserDetails student = User.withUsername("student")
	                .password("{noop}student123")
	                .roles("STUDENT")
	                .build();

	        return new InMemoryUserDetailsManager(admin, trainer, student);
	    }
	

}
