package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVo;

@Service
public class MemberService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public int insertMember(MemberVo m) {
		m.setPwd(passwordEncoder.encode(m.getPwd()));
		int re = DBManager.insertMember(m);
		return re;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//매개변수로 전달받은 username에 해당하는 
		//회원의 정보를 db로 부터 꺼내와요
		MemberVo m = DBManager.selectMember(username);
		
		//만약 username에 해당하는 회원이 없으면 
		//예외를 발생시켜요
		if(m == null) {
			throw new UsernameNotFoundException(username);
		}
		
		// 우리가 db로 부터 뽑아온 회원의 정보를 
		// 스프링시큐리가 인증절차를 할 수 있는 객체로 
		// 만들어 일을 맡깁니다.
		
		return User.builder()
				.username(m.getId())
				.password(m.getPwd())
				.roles(m.getRole())
				.build();
	}

}





