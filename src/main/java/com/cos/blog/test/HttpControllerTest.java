package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//@Controller // 사용자가 요청하면 ->Html파일을 응답해줌

@RestController  //사용자가 요청하면 -> Data를 응답해줌
public class HttpControllerTest {

	private static final String TAG = "HttpController Test : " ;
	
	//http://localhost:8000/blog/http/lombok     //application.yml에 포트 8000, context-path : /blog
	@GetMapping("/http/lombok")	
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter:"+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter:"+m.getUsername());
		return "lombokTest 완료";
	}
	
	//인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다. (나머지는 405에러 발생 -> postman사용)
	// http://localhost:8000/blog/http/get     (select)
	// http://localhost:8080/blog/http/get?id=1&username=ssar get요청은 이렇게 쿼리스트링으로 데이터를 보낼 수 있음. (@RequestParam을 매개변수로 넣어야 함)
	// 근데 매개변수가 많다면, Member (Object)를 매개변수로 하면 됨
	@GetMapping("/http/get")
	public String getTest(Member m) {

		return "get 요청" + m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail() ;   //Data로 문자열(String)이 리턴됨
	}
	
	//post요청은 데이터 전송할 때 쿼리스트링x, body에 담아서 보냄
	// http://localhost:8000/blog/http/post  (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { 
		return  "post 요청" + m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail() ;  
	}
	
	
	// http://localhost:8000/blog/http/put   (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청" + m.getId() +"," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail() ; 
	}
	
	
	// http://localhost:8000/blog/http/delete    (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
