package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto-increment
	private int id;// 시퀀스, auto-increment
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne //테이블간의 연관관계를 만들어줌, 여러개의 답변이 하나의 게시글에 존재할 수 있음
	@JoinColumn(name = "boardId")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	
}
