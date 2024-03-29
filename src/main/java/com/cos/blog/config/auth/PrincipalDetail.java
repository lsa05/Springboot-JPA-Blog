package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Data;
import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장해준다.
@Getter
public class PrincipalDetail implements UserDetails{
	private User user;//콤포지션

	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴한다.(true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있지 않았는지 리턴한다. (true:잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//비밀번호가 만료되지 않았는지 리턴한다. (true:만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정활성화(사용가능)인지 리턴한다. (true:활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//계정이 갖고 있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있을땐 for문을 돌아야 하는데 우리는 한개만!!)
	//GrantedAuthority를 상속한 Collection타입
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();	
		collectors.add(()->{return "ROLE_"+user.getRole();});  //"ROLE_" 이 부분은 규칙임.. 꼭 넣어줘야 함, 결과적으로 ROLE_USER 이런식으로 리턴됨
		return collectors;
	}
	
}
