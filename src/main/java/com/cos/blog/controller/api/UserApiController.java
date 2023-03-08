package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager AuthenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //자바 오프젝트를 json으로 변환해서 리턴
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.회원수정(user);
		//여기서는 트랜잭션이 종료되기 때문에 DB값은 변경이 됐음
		//하지만 세션값은 변경되지 않은 상태이므로 직접 세션값을 변경해 줄 것
		//세션 등록
		Authentication authentication = AuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken( user.getUsername() , user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	
	//전통적인 로그인 방식
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
//		System.out.println("UserApiController : login호출됨");
//		User principal = userService.로그인(user); //principal(접근주체)
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
	
	
	
	
}
