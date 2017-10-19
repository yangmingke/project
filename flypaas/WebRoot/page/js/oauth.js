	 $(function(){
		   $("#orgIdFile").change(function(){
	        	var a = $(this).val();
	        	$(this).siblings("input[type='text']").val(a);
	        	$("#isOrgIdFile").val(2);
	        });
	        
	        $("#ittIdFile").change(function(){
	        	var a = $(this).val();
	        	$(this).siblings("input[type='text']").val(a);
	        	$("#isIttIdFile").val(2);
	        });
	        $("#bsnumFile").change(function(){
	        	var a = $(this).val();
	        	$(this).siblings("input[type='text']").val(a);
	        	$("#isBsnum").val(2);
	        });
	        $("#idCardFile").change(function(){
	        	var a = $(this).val();
	        	$(this).siblings("input[type='text']").val(a);
	        	$("#isIdCard").val(2);
	        });
	        
	        
			 $("#oauth_confirm_btn").click(function(){
				 var type = $("input[type='radio']:checked").attr("id");
				 if(type=="person"){
					 if(oauth_frm_person.realName()&&oauth_frm_person.idType()&&oauth_frm_person.idNum()&&oauth_frm_person.idCard()&&oauth_frm_person.idType()){
						 $("#personalForm").submit();
					 }
					 return;
				 }
				 else if(type=="company"){
					 if(oauth_frm_company.companyName()&&oauth_frm_company.address()&&oauth_frm_company.orgNum()&&oauth_frm_company.isOrgIdFile()
							 &&oauth_frm_company.picType1()&&oauth_frm_company.ittIdNum()&&oauth_frm_company.isIttIdFile()&&oauth_frm_company.picType2()
							 &&oauth_frm_company.bsNum()&&oauth_frm_company.isBsnum()&&oauth_frm_company.picType3()&&oauth_frm_company.legalPerson()&&oauth_frm_company.companyNbr()){
						 $("#companyForm").submit();
					 }
				 }
			 });
	 });
	 var oauth_frm_person = {
		 realName:function(){
			 var realName = $("#real_name").val();
			 if(realName==""){
				 $("#nameSpanError").fadeIn();
				 $("#real_name").focus();
				 return false;
			 }
			 if(validationSymbol(realName)){
				 $("#nameSpanError").fadeIn();
				 $("#real_name").focus();
				 return false;
			 }
			 $("#nameSpanError").hide();
			 return true;
		 },
		 idType:function(){
			 var pidType = $("#pidType").val();
			 if(pidType==""){
				 $("#idTypeSpanError").fadeIn();
				 return false;
			 }
		     $("#idTypeSpanError").hide();
		     return true;
			 
		 },
		 idNum:function(){
			 $("#idNumSpanError").text("错误：请输入正确的证件号码");
			 var idNum = $("#id_num").val();
			 var pidType = $("#pidType").val();
			 var existsID = $("#existsID").val();
			 if(idNum==""){
				 $("#idNumSpanError").fadeIn();
				 $("#id_num").focus();
				 return false;
			 }
			 if(pidType==1){
	     			if(!vIDCard(idNum)){
	     				$("#idNumSpanError").fadeIn();
	     	    		$("#id_num").focus();
	     	    		return false;
	     			}
		     }else if(pidType==2){
	     			if(!vPCard(idNum)){
	     				$("#idNumSpanError").fadeIn();
	     	    		$("#id_num").focus();
	     	    		return false;
	     			}
		     }
			 if(existsID==1){
				$("#idNumSpanError").text("错误：证件号码已存在");
				$("#idNumSpanError").fadeIn();
  	    		$("#id_num").focus();
  	    		return false;
			 }
			 $("#idNumSpanError").hide();
			 return true;
		 },
		 idCard:function(){
			 var isIdCard = $("#idCardFile").val();
			 if(isIdCard==""){
				 $("#idCardFile").parent().siblings("span").fadeIn();
				 return false;
			 }
			 $("#idCardFile").parent().siblings("span").hide();
			 return true;
			 
		 },
		 picType:function(){
			 var picType = $("#picType").val();
			 if(picType==2){
				 $("#idCardFile").parent().siblings("span").fadeIn();
				 return false;
			 }
			$("#idCardFile").parent().siblings("span").hide();
			return true; 
		 }
		 
		 
	 };
	 var oauth_frm_company = {
		  companyName:function(){
			  var name= $("#company_name").val();
			  if(name==""){
		   		  $("#companyNameSpanError").fadeIn();
		   		  $("#company_name").focus();
		   		  return false;
		   	  }else if(validationSymbol(name)){
		   		  $("#companyNameSpanError").fadeIn();
		   		  $("#company_name").focus();
		   		  return false;
		   	  }
		   	  $("#companyNameSpanError").hide();
		   	  return true;
		  },
		  address:function(){
			  var address = $("#company_address").val();
			  if(address==""){
		   		  $("#addressSpanError").fadeIn();
		   		  $("#company_address").focus();
		   		  return false;
		   	  }
		   	  $("#addressSpanError").hide();  
		   	  return true;
		  },
		  orgNum:function(){
			  var orgNum = $("#org_num").val();
			  var existsORGNUM = $("#existsORGNUM").val();
			  if(orgNum==""){
		   		  $("#orgNumSpanError").fadeIn();
		   		  $("#org_num").focus();
		   		  return false;
		   	  }else if(!verifyOrgId(orgNum)){
		   		  $("#orgNumSpanError").fadeIn();
		   		  $("#org_num").focus();
		   		  return false;
		   	  }
			  if(existsORGNUM==1){
				  $("#orgNumSpanError").fadeIn();
		   		  $("#org_num").focus();
		   		  return false;
			  }
		   	  $("#orgNumSpanError").hide();
		   	  return true;
		  },
		  isOrgIdFile:function(){
			  var isOrgIdFile = $("#orgIdFile").val();
			  if(isOrgIdFile==""){
		   		  $("#orgIdFile").parent(".file").siblings("span").fadeIn();
		   		  return false;
		   	  }
		   	  $("#orgIdFile").parent(".file").siblings("span").hide();
		   	  return true;
		  },
		  ittIdNum:function(){
			  var ittIdNum = $("#ittId_num").val();
			  var existsITTNUM = $("#existsITTNUM").val();
			  if(ittIdNum==""){
		   		  $("#ittidSpanError").fadeIn();
				  $("#ittId_num").focus();
			      return false;
		   	  }else if(!verifyItt(ittIdNum)){
		   		  $("#ittidSpanError").fadeIn();
				  $("#ittId_num").focus();
				  return false;
		   	  }
			  if(existsITTNUM==1){
				  $("#ittidSpanError").fadeIn();
				  $("#ittId_num").focus();
				  return false;
			  }
		   	  $("#ittidSpanError").hide();
		   	  return true;
			  
		  },
		  isIttIdFile:function(){
			  var isIttIdFile = $("#ittIdFile").val();
			  if(isIttIdFile==""){
		   		  $("#ittIdFile").parent(".file").siblings("span").fadeIn();
		   		  return false;
		   	  }
		   	  $("#ittIdFile").parent(".file").siblings("span").hide();
		   	  return true;
		  },
		  bsNum:function(){
			  var bs_num = $("#bs_num").val();
			  var existsBSNUM=$("#existsBSNUM").val();
			  if(bs_num==""){
		   		  $("#bsnumSpanError").fadeIn();
				  $("#bs_num").focus();
				  return false;
		   	  }else if(!bsNum(bs_num)){
		   		  $("#bsnumSpanError").fadeIn();
				  $("#bs_num").focus();
				  return false;
		   	  }
			  if(existsBSNUM==1){
				  $("#bsnumSpanError").fadeIn();
				  $("#bs_num").focus();
				  return false;
			  }
		   	  $("#bsnumSpanError").hide();
		   	  return true;
		  },
		  isBsnum:function(){
			  var isBsnum=$("#bsnumFile").val();
			  if(isBsnum==""){
		   		  $("#bsnumFile").parent(".file").siblings("span").fadeIn();
		   		  return false;
		   	  }
		   	  $("#bsnumFile").parent(".file").siblings("span").hide();
		   	  return true;
		  },
		  picType1:function(){
			  var picType1 = $("#picType1").val();
		   	  if(picType1==2){
		   		  $("#orgIdFile").parent(".file").siblings("span").fadeIn();
		   		  return false;
		   	  }
		   	  $("#orgIdFile").parent(".file").siblings("span").hide();
		   	  return true;
		  },
		  picType2:function(){
			  var picType2 = $("#picType2").val();
			  if(picType2==2){
		   		  $("#ittIdFile").parent(".file").siblings("span").fadeIn();
		   		  return false;
		   	  }
		   	  $("#ittIdFile").parent(".file").siblings("span").hide();
		   	  return true;
		  },
		  picType3:function(){
			  var picType3 = $("#picType3").val();
			  if(picType3==2){
		   		  $("#bsnumFile").parent(".file").siblings("span").fadeIn();
		   		  return false;
		   	  }
		   	  $("#bsnumFile").parent(".file").siblings("span").hide();
		   	  return true;
		  },
		  companyNbr:function(){
			  var companyNbr=$("#companyNbr").val();
			  if(companyNbr==""){
		   		  $("#companyNbrError").fadeIn();
		   		  $("#companyNbr").focus();
		   		  return false;
		   	  }/*else if(!verify400So(companyNbr)){
		   		  $("#companyNbrError").fadeIn();
		   		  $("#companyNbr").focus();
		   		  return false;
		   	  }*/
		   	  $("#companyNbrError").hide();
		   	  return true;
		  },
		  legalPerson:function(){
			  var legalPerson=$("#legalPerson").val();
			  if(legalPerson==""){
		   		  $("#legalPersonError").fadeIn();
		   		  $("#legalPerson").focus();
		   		  return false;
		   	  }else if(validationSymbol(legalPerson)){
		   		  $("#legalPersonError").fadeIn();
		   		  $("#legalPerson").focus();
		   		  return false;
		   	  }
		   	  $("#legalPersonError").hide();
		   	  return true;
		   	  
		  }
	 };
    
     
        
function idNumExists(idNum,id,oldIdNum){
	if(idNum==oldIdNum){
		$("#exists"+id).val("");
		return;
	}
	$.ajax({
		url:"/user/ckIDNumEnable",
		type:"post",
		data:"idNum="+idNum,
		dataType: "text",
			success: function (data) {
				$("#exists"+id).val(data);
            },
            error: function (msg) {
            }
		});
}
function verifyPic(obj,id){
	var v = obj.value;
	if(!v.match(/.jpg|.gif|.png|.jpeg/i)){
		$("#picType"+id).val(2);
	}else{
		$("#picType"+id).val(1);
	}
	
}
function setIdType(type){
	$("#pidType").val(type);
}
function onUploadImgChange(sender, index) {
	if (!sender.value.match(/.jpg|.png|.bmp|.jpeg/i)) {
		sender.value="";
		popupBox("认证提示",'图片格式无效！','',2);
		$("#picType").val(2);
		return;
	}
	$("#picType").val(1);
	//赋值给显示的input
	var fileContent = sender.value ;
	$("#file_txt").val(fileContent);
	var objPreview = document.getElementById('preview' + index);

	var objPreviewFake = document
			.getElementById('preview_fake' + index);
	var objPreviewSizeFake = document
			.getElementById('preview_size_fake' + index);

	if (sender.files && sender.files[0]) {
		objPreview.style.display = 'block';
		objPreview.style.width = 'auto';
		objPreview.style.height = 'auto';
		try {
			objPreview.src = sender.files[0].getAsDataURL();
		} catch (e) {
			var reader = new FileReader();
			reader.onload = function(e) {
				objPreview.src = e.target.result;
			}
			reader.readAsDataURL(sender.files[0]);
		}
		// Firefox 因安全性问题已无法直接通过 input[file].value 获取完整的文件路径     
	} else if (objPreviewFake.filters) {
		sender.select();
		sender.blur();
		var imgSrc = document.selection.createRange().text;
		objPreviewFake.filters
				.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
		objPreviewSizeFake.filters
				.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
		autoSizePreview(objPreviewFake, objPreviewSizeFake.offsetWidth,
				objPreviewSizeFake.offsetHeight);
		objPreview.style.display = 'none';
	}
}

function onPreviewLoad(sender) {
	autoSizePreview(sender, sender.offsetWidth, sender.offsetHeight);
}

function autoSizePreview(objPre, originalWidth, originalHeight) {
	var zoomParam = clacImgZoomParam(300, 300, originalWidth,
			originalHeight);
	objPre.style.width = zoomParam.width + 'px';
	objPre.style.height = zoomParam.height + 'px';
	objPre.style.marginTop = zoomParam.top + 'px';
	objPre.style.marginLeft = zoomParam.left + 'px';
}

function clacImgZoomParam(maxWidth, maxHeight, width, height) {
	var param = {
		width : width,
		height : height,
		top : 0,
		left : 0
	};
	if (width > maxWidth || height > maxHeight) {
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;

		if (rateWidth > rateHeight) {
			param.width = maxWidth;
			param.height = height / rateWidth;
		} else {
			param.width = width / rateHeight;
			param.height = maxHeight;
		}
	}
	//param.height = 318;
	//param.width = 643;
	param.left = 0;
	param.top = 0;
	return param;
}
