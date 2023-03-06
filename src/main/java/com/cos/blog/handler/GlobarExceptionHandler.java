package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //모든 Exception이 발생하면 이 클래스로 들어옴
@RestController
public class GlobarExceptionHandler {

	@ExceptionHandler(value=IllegalArgumentException.class) // IllegalArgumentException이 발생하였을 때 
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
