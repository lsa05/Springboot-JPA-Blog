package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥 주소가 /이면 index.jsp허용
//static이하에 있는 /js/**, /css/**, /image/**

@Controller
public class UserController {									//회원가입, 로그인, 회원정보 수정에 대한 view를 보여주는 역할만 수행하는 컨트롤러

	//회원가입
	@GetMapping("/auth/joinForm")
	public String joinForm() {	
		return "user/joinForm";
	}
	
	//로그인
	@GetMapping("/auth/loginForm")
	public String loginForm() { 
		return "user/loginForm";
	}	
	
	//회원정보수정
	@GetMapping("/user/updateForm")
	public String updateForm() { 
		return "user/updateForm";
	}
	
	
	//카카오 로그인
//	@GetMapping("auth/kakao/callback")
//	public @ResponseBody String kakaoCallback(String code) {//Data를 리턴해주는 컨트롤러 함수
//
//		//POST 방식으로 key=value 데이터를 요청(카카오쪽으로)
//		RestTemplate rt = new RestTemplate();
//		
//		// HttpHeader 오브젝트 생성
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//	
//		// HttpBody 오브젝트 생성
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type","authorization_code");
//		params.add("client_id","5bdb4ad39d1812559da016b9b9f56bf0");
//		params.add("redirect_uri","http://localhost:8000/auth/kakao/callback");
//		params.add("code","code");
//		
//		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
//		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
//				new HttpEntity<>(params, headers);
//		
//		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
//		ResponseEntity<String> response = rt.exchange(
//						"https://kauth.kakao.com/oauth/token",
//						HttpMethod.POST,
//						kakaoTokenRequest,
//						String.class
//		);
//		
//		return "카카오 토큰 요청 완료 : 토큰 요청에 대한 응답 "+response.getBody();
//	}
	
}
