function newPwd(){
	var newPwd1 = $('#newPwd1').val();
	var newPwd2 = $('#newPwd2').val();
	if(newPwd1!=newPwd2){
		$('#errorMessage2').css("display","block");
		$('#newPwd2').focus();
	}else{
		$('#errorMessage2').css("display","none");
	}
}

function check(){
	
}

