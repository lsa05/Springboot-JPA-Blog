let index = {
	init: function() {
		$("#btn-save").on("click", () => {//function(){}을 사용하지 않고 ()=>{}을 사용하는 이유 : this를 바인딩 하기 위해서 사용
			this.save();
		});

		$("#btn-login").on("click", () => {//function(){}을 사용하지 않고 ()=>{}을 사용하는 이유 : this를 바인딩 하기 위해서 사용
			this.login();
		});
	},

	//회원가입
	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};

		//console.log(data);

		//ajax호출 시 default가 비동기 호출
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌,,
		//회원가입 수행 요청
		$.ajax({
			type: "POST",
			url: "/api/user",
			data: JSON.stringify(data),  //data:data 하면 자바스크립트 오브젝트
			//data: JSON.stringify(data)하면 JSON문자열
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json" //요청에 대한 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면)	=> javascript오브젝트로 변경해줌								
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.");
			//console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	},


	//로그인
	login: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
		};

		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data),  //data:data 하면 자바스크립트 오브젝트
			//data: JSON.stringify(data)하면 JSON문자열
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json" //요청에 대한 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면)	=> javascript오브젝트로 변경해줌								
		}).done(function(resp) {
			alert("로그인이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});

	}
}

index.init();