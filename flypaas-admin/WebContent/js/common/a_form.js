// JavaScript Document

$(function() {

	// 文本框处理
	$(":text,:password").focus(function() {
		$(this).css("color", "#333");
	});

    //单选框处理
	$(":radio").wrap("<span class='radio'></span>");
	$(":radio:checked").parent("span").addClass("checked");
	$(":radio").click(function(){
		if($(this).is(":checked")){
			$(this).parent("span").addClass("checked");
			var name = $(this).attr("name");
			$(":radio[name='"+name+"']").not(this).parent("span").removeClass("checked");
		}
	});
	
	// 复选框处理
	$(":checkbox").wrap("<span class='checkbox'></span>");
	$(":checkbox:checked").parent("span").addClass("checked");
	$(":checkbox").click(function() {
		if ($(this).is(":checked")) {
			$(this).parent("span").addClass("checked");
		} else {
			$(this).parent("span").removeClass("checked");
		}
	});

	/*------------------------模拟下拉列表框-------------------*/
	// 点击其他地方下拉收缩
	$(document).click(function() {
		$("div.select ul").slideUp();
	});

    //placeholder处理
    $(":text,:password").focus(function(){
    	$(this).attr("placeholder","");
    });
    
    $(":text,:password").each(function(){
    	var placeholder_val=$(this).attr("placeholder");
    	$(this).blur(function(){
	    	$(this).attr("placeholder",placeholder_val);
	    	$(this).css("color","#595959");
	    });
    });
    
  //模拟上传
	$("input[type='file']").wrap("<div class='file'></div>");
	if(!$("input[type='file']").hasClass("choose")){
		$("input[type='file']").before("<input type='text' class='file_txt' name='file_txt' /><input type='button' value='浏览' class='file_btn' />");
	}
	else{
		$("input[type='file']").before("<input type='button' value='选择文件' class='choose_btn' />");
	}

});
