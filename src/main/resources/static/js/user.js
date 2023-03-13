let index = {                                        						 //'index'라는 이름의 변수를 선언, 객체 리터럴을 할당한다.
	init: function() {                                  				 //객체 index의 init()메서드를 정의
		$("#btn-save").on("click", () => {            	     //function(){}을 사용하지 않고 ()=>{}을 사용하는 이유 : this를 바인딩 하기 위해서 사용
			this.save();                                     				 //id가 "btn-save"인 HTML 요소를 찾아 클릭이벤트를 등록, 클릭이벤트가 발생하면 객체 'index'의 'save()'메서드를 호출한다.
		});

		$("#btn-update").on("click", () => {				//function(){}을 사용하지 않고 ()=>{}을 사용하는 이유 : this를 바인딩 하기 위해서 사용
			this.update();
		});
	},

	//회원가입
	save: function() { 												//객체 index의 save()메서드를 정의
		let data = {														//AJAX요청으로 보낼 데이터를 정의
			username: $("#username").val(),              //id가 "username"인 HTML 요소의 값(val)을 반환(=입력된 username값)하여 username에 저장
			password: $("#password").val(),
			email: $("#email").val(),
		};

		//console.log(data);

		/*
		ajax호출 시 default가 비동기 호출
		ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌,,
		*/
		
		//회원가입 수행 요청
		$.ajax({ //AJAX를 이용한 비동기 통신
			type: "POST",
			url: "/auth/joinProc",                         									//요청을 보낼 URL
			data: JSON.stringify(data),               					     		//요청에 함께 보낼 데이터, http body데이터
			contentType: "application/json; charset=utf-8",				//요청한 body데이터가 어떤 타입인지(MIME타입) 지정
			dataType: "json"                              									//요청을 서버로해서 응답이 왔을 때 응답의 MIME타입 지정, 이 코드에서는 JSON 응답을 기대하고 있다.
		}).done(function(resp) {                        								//AJAX 통신 요청이 성공하면(=서버에서 응답이 오면) 실행될 메서드  
			if (resp.status === 500) {                       							//서버 오류 발생시
				alert("회원가입에 실패하였습니다.");
			} else {//회원가입 성공시
				alert("회원가입이 완료되었습니다.");
				location.href = "/";  							                       		//메인 페이지로 이동
			}

		}).fail(function(error) {                         								//AJAX 통신 실패시 실행될 메서드
			alert(JSON.stringify(error));                  							//에러메세지 출력
		});

	},

	//회원정보수정
	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};

		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("회원정보수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
}

index.init();																				//'index'객체의 'init()'메서드 호출 