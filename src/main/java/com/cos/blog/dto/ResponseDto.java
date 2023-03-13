package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {					//REST API의 응답을 담는 DTO
	int status;												//HTTP 응답 상태 코드를 저장한다. 예를 들어, HTTP 응답이 성공했을 경우 200(OK), 클라이언트 오류가 발생했을 경우 4xx, 서버 오류가 발생했을 경우 5xx 등이 저장
	T data;														//REST API에서 클라이언트로 전송할 데이터를 저장하는 제네릭 타입의 멤버 변수, 어떤 타입의 데이터라도 처리한다.
}																	//이를 JSON 형식으로 변환하여 클라이언트에게 전송함으로써, 서버에서 처리된 결과를 클라이언트가 쉽게 확인할 수 있도록 한다.

/*
 ResponseDto는 응답 상태(status)와 응답 데이터(data)를 가지고 있다. 응답 상태는 HTTP 상태 코드를 나타내며, 응답 데이터는 API를 호출한 클라이언트에게 반환될 데이터를 나타낸다.
예를 들어, 클라이언트에서 /user 요청을 보내면, 서버는 이에 대한 응답으로 status와 data를 담은 ResponseDto 객체를 (JSON 형식으로) 반환할 수 있다.이 객체를 클라이언트에서 받아서 상태와 데이터를 확인할 수 있다.
 */
