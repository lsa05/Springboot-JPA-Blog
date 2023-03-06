let index = {
		init:function(){
			$("#btn-save").on("click", ()=>{
				this.save();
			});
		},
		
		save : function(){
			//alert('user의 save함수 호출됨');
			let data = {
				username : $("#username").val(),
				password : $("#password").val(),
				email : $("#email").val(),
			}
			
			//console.log(data);
			
			$.ajax().done().fail();//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!						
		}
}

index.init();