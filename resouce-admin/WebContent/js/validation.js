
//校验手机号
function phoneCheck(sMobile){
	var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
	return reg.test(sMobile); 
}

//固定电话
function telephoneCheck(telephone){
	var reg = /^0\d{2,3}-\d{7,8}(-\d{1,6})?$/;
	return reg.test(telephone); 
}

function phoneNumCheck(phoneNum){
	return phoneCheck(phoneNum) || telephoneCheck(phoneNum);
}

//邮箱验证
function emailCheck(email){
	var reg = /^([\w\.\-]+)\@(\w+)(\.([\w^\_]+)){1,2}$/;
	return reg.test(email); 
}
