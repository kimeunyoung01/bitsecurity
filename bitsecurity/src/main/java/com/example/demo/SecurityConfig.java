package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		
		http.authorizeRequests()
		.mvcMatchers("/","/all/**").permitAll()
		.mvcMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated();
		
		//http.formLogin(); 
		//스프링 시큐리티가 제공하는 로그인폼을 사용하겠습니다.
		
		//로그인 페이지를 사용자가 만들어 지정해 보자
		http.formLogin().loginPage("/login").permitAll();
		
		
		
		http.httpBasic(); 
		//http기본 프로토콜을 사용하겠습니다.
	}
	
}








