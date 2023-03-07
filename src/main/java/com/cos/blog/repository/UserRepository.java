package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// jsp로 치면 DAO라고 생각하면 됨
// 자동으로 bean으로 등록이 된다. 그래서 
// @Repository어노테이션 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> { //jpa레파지토리는 user테이블을 관리하는 레파지토리이며, user테이블의 pk는 integer다.

	//JPA Naming 쿼리
	// SELECT * FROM user WHERE username=? AND password=?; 이 쿼리가 동작함  //각?에는 파라미터가 들어옴 
	User findByUsernameAndPassword(String username, String password);
	
//	@Query(value="SELECT * FROM user WHERE username=1 AND password=2", nativeQuery = true)
//	User login(String username, String password);
}
