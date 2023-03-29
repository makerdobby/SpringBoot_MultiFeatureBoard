package com.thymeleaf.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws
		Exception {
		http
			.authorizeRequests()
//			.antMatchers("/login","/signUp","/","/css/**","/js/**").permitAll()
//			.anyRequest().authenticated()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
//				.loginPage("/login")
//				.defaultSuccessUrl("/")
//				.successHandler(null)
//				.permitAll()
//				.and()
			.disable()
			.csrf()
			.disable()
			.headers()
			.disable()
			.httpBasic()
			.disable()
			.rememberMe()
			.disable()
			.logout()
				.disable()
			.sessionManagement()
			;

		return http.build();
	}



}
