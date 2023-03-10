package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// jsp로 치면 DAO라고 생각하면 됨
// 자동으로 bean으로 등록이 된다. 그래서 
// @Repository어노테이션 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> { //jpa레파지토리는 user테이블을 관리하는 레파지토리이며, user테이블의 pk는 integer다.
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username); 	//username이라는 파라미터를 전달받아서 해당하는 사용자를 찾아서 Optional<User> 형태로 반환한다. 
																							//Optional은 값이 있을 수도 있고 없을 수도 있음을 나타내는 자바 8에서 추가된 클래스로, 이를 통해 NullPointException 등의 예외를 방지할 수 있다.
}

/*
 JpaRepository는 Spring Data JPA에서 제공하는 인터페이스이다. 
 JpaRepository에서 이미 save(), findById(), findAll() 등의 메서드가 구현되어 있다. 
 따라서 UserRepository에서 따로 save() 메서드를 구현하지 않아도, 
 JpaRepository를 상속받았기 때문에 save() 메서드를 사용할 수 있다. 
 */






//JPA Naming 쿼리
// SELECT * FROM user WHERE username=? AND password=?; 이 쿼리가 동작함  //각?에는 파라미터가 들어옴 
//	User findByUsernameAndPassword(String username, String password);

//	@Query(value="SELECT * FROM user WHERE username=1 AND password=2", nativeQuery = true)
//	User login(String username, String password);