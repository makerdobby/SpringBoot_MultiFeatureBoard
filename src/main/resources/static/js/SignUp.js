
$(document).ready(function() {

	var date = new Date().toISOString();
	var today = date.substring(0, 10);
	console.log(today);
	$('#floatingInputDate').val(today);

});

function rolback() {

	$('#floatingPasswordConfirm').keyup(function() {

		$('#floatingPasswordConfirm').focus().css('color', 'black');
		$('label[for="floatingPasswordConfirm"]').css('color', 'black').text("Confirm Password");
	});
}

function signUp() {
	console.log("시작");
	var pw1 = $('#floatingPassword').val();
	var pw2 = $('#floatingPasswordConfirm').val();

	if(checkJoined()>0){
		alert("이미 존재하는 회원입니다");
		$('#floatingInputUsername').focus();
		return;
	}

	if (pw1 == pw2) {
		document.signUpFrm.method = "post";
		document.signUpFrm.action = '/dosignUp';
		document.signUpFrm.submit();
	} else {

		$('#floatingPasswordConfirm').focus().css('color', 'red');
		$('label[for="floatingPasswordConfirm"]').text("비밀번호가 일치하지 않습니다").css('color', 'red');

		rolback();
	}
}

function checkJoined(){

	var result = 0;

	$.ajax({
		 url : "/signUpAjax"
		,type : "GET"
		,async :false
		,data : $("form[name='signUpFrm']").serialize()
		,success : function(data){
			console.log(data);
			result = data;
		}
		,error:function(){
			alert("실패");
		}
	});
	console.log("result :"+result);
	return result;
}

function confirm(){

	var pw1 = $('#floatingPassword').val();
	var pw2 = $('#floatingPasswordConfirm').val();
	console.log(pw1);
	console.log(pw2);

	if (pw1 === pw2){

		$('#floatingPasswordConfirm').focus().css('color', 'green');
		$('label[for="floatingPasswordConfirm"]').text("일치").css('color', 'green');
	}

	$('#floatingPasswordConfirm').click(function() {

		$('#floatingPasswordConfirm').focus().css('color', 'black');
		$('label[for="floatingPasswordConfirm"]').css('color', 'black').text("Confirm Password");
	});
}