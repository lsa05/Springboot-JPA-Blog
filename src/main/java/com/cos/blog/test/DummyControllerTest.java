package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController //html파일이 아니라 data를 리턴해주는 controller 
public class DummyControllerTest {

	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	//delete
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch(Exception e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return ("삭제되었습니다. id: ")+ id;
	}
	
	//update
	//save함수는 id를 전달하지 않으면 insert를 해주고
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
	@Transactional //save()를 호출하지 않아도 update가 됨,,,(더티 체킹) //함수 종료시에 자동 commit됨
	@PutMapping("/dummy/user/{id}") 
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //수정할 것 : email, password //json데이터를 요청 => Java Object로 변환(MessageConverter의 Jackson라이브러리가 변환해서 받아줌.)
		System.out.println("id: "+id);
		System.out.println("passoword: "+requestUser.getPassword());
		System.out.println("email: "+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		
		//더티 체킹을 이용한 update
		return user;
	}
	
	//여러개 select
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//paging 한 페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	
	//select
	//{id}주소로 파라미터를 전달 받을 수 있다.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) { //User객체를 return받을 것임
		// 만약 user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면, user가 null이 될 것 아냐?
		// 그럼 return이 null이 되잖아. 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!!
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
		});
		// 요청 : 웹브라우져
		// user 객체는 자바 오브젝트 (웹브라우져가 이해 x)
		// 변환 (웹브라우져가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리 호출해서
		// user 오브젝트를 json으로 변환해서 브라우져에게 던져줍니다. 
		return user;
	}
	
	//insert
	//http://localhost:8000/blog/dummy/join(요청)
	//http의 body에 username, password, email데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) {//나머지는 자동생성이라 3가지 변수만 파라미터로 받으면 됨 (key = value)형식으로
		System.out.println("username : "+user.getUsername()); 
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		
		System.out.println("id: "+ user.getId());
		System.out.println("role: "+user.getRole() );
		System.out.println("createDate: "+ user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
