package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> Java(다른 언어 포함)에 있는 Object를 테이블로 매핑해주는 기술
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
//@DynamicInsert //insert할 때 null인 필드를 제외하고 insert해줌
public class User {

	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.(MySQL의 경우, auto-increament를 사용하겠다는 뜻) 
	private int id; //시퀀스, auto-increment 사용할 것임
	
	@Column(nullable = false, length = 30, unique=true) //아이디는 null이 되면 안되므로 해당 어노테이션을 사용해 null 가능여부와 길이값을 지정해준다.
	private String username; //아이디     //unique로 중복 방지
	
	@Column(nullable = false, length = 100) //나중에 암호화를 할 것이기 때문에 길이는 넉넉하게 100
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("user")
	//DB에는 RoleType이 없으니까 String이라는 것을 알려줘야함.
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다.(Enum은 도메인(범위)을 쓸 수 있음) //ADMIN, USER
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;
}
