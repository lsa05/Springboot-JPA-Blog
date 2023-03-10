package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController 																								//RestAPI 처리를 담당하는 컨트롤러임을 명시
public class UserApiController {

	@Autowired																								// UserService 객체를 주입받기 위해 사용
	private UserService userService;
	
	@Autowired
	private AuthenticationManager AuthenticationManager;						// Spring Security에서 제공하는 인증 처리 객체를 주입받기 위해 사용
	
	//회원가입
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {				// 클라이언트가 회원가입 요청을 보냈을 때 호출되는 메소드
		System.out.println("UserApiController : save 호출됨");
		
		userService.회원가입(user);																	//UserService 클래스의 회원가입 메소드를 호출하여, 전달받은 회원가입 요청 데이터를 처리
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);		 	//반환된 응답 데이터를 ResponseDto<Integer> 객체에 담아서 반환, HttpStatus.OK.value()는 상태 코드가 200 (OK)을 나타내는 값이며, 두 번째 매개변수인 1은 응답 데이터를 나타내는 값
																														//new ResponseDto<Integer>(HttpStatus.OK.value(), 1) : status 값으로 HttpStatus.OK.value()를, data 값으로 1을 가지는 ResponseDto<Integer> 객체를 생성하여 반환하여 클라이언트에게 전달된다.
	}																													
	
	//회원정보수정
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
