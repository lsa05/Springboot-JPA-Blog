package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice //모든 Exception이 발생하면 이 클래스로 들어옴
@RestController
public class GlobarExceptionHandler {

	@ExceptionHandler(value=IllegalArgumentException.class) // IllegalArgumentException이 발생하였을 때 
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()); 
	}
}
