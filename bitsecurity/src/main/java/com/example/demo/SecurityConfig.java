package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
		
		//우리가 사용자정의 loginPage를 지정하면
		//로그아웃제공하지 않아요
		//로그아웃을 위한 서비스명도 지정해요.
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true);
		
		http.httpBasic(); 
		//http기본 프로토콜을 사용하겠습니다.
	}
	
}








