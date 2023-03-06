package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@RequiredArgsConstructor 	//final붙은 애들에 대해 생성자 만들어줌

@Data     									//getter, setter둘 다 만들고 싶을 때
//@AllArgsConstructor 				//생성자 만들고 싶을 때
@NoArgsConstructor  			    //빈 생성자 반들고 싶을 때 
public class Member {
	private  int id;  			// private으로 해야 직접 접근해서 값을 변경하는 것을 막을 수 있음(객체지향), 
										// 상태(값)를 변경할때는 메소드를 이용해야 함. 그래서 getter, setter를 만들어 수정할 수 있게 함 
	private  String username;
	private  String password;
	private  String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}  
	
	
	
	
	
}
