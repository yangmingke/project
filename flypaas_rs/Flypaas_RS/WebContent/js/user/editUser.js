$('#editUser').click(function(){
	var sid = $('#userId').val();
	var username = $('#usernameId').val();
	var mobile = $('#mobileId').val();
	var email = $('#emailId').val();
	var roleId = $('#select2').val();
	var realname = $('#realnameId').val();
	var address = $('#addressId').val();
	var chatType = $('#select1').val();
	var chatNbr = $('#chatnbrId').val();
	$.post("/userController/editUser.action",
			{"sid":sid,
			 "username":username,
			 "mobile":mobile,
			 "email":email,
			 "roleId":roleId,
			 "realname":realname,
			 "address":address,
			 "chatType":chatType,
			 "chatNbr":chatNbr,
			},
			function(data){
				var data = eval('('+data+')')
				if(data == 1){
					alert("修改成功");
				}
				if(data == 0){
					alert("修改失败");
				}
			}
	)
});

