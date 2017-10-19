//javascript

$(function(){
	isEmptyObject = function(obj){
		return obj == null || obj == undefined || obj == '';
	};
	
	$("div.select").click(function(e){
		if("readonly" == $(this).attr('readonly')){
			return false;
		}
		e.stopPropagation();
		$(this).children("ul").toggle();
		$(this).toggleClass("focus");
		$(this).addClass('self').parents("body").find(".select").not(".self").children("ul").hide();
		$(this).removeClass("self");

	});
	//下拉选择
	$("div.select ul li").click(function(){
		var self=  $(this);
		var select = self.parents('div.select');
		var input = select.attr("input");
		if (!isEmptyObject(input)) { 
			select.find("input[name='"+input+"']").attr("value",self.attr("val"));
		}
		var li_val = self.text();
		self.addClass("selected").siblings("li").removeClass("selected");
		self.parent("ul").siblings("span").html(li_val+"<i>&nbsp;</i>");
	});
	//模拟下拉列表框
	$("div.select").each(function(){
		var self = $(this);
		var select_width =self.width();
		self.children("span").width(select_width-10);
		self.children("ul").width(select_width);
		self.children("ul").children("li").width(select_width-10);
		var dv = self.attr("defaultValue");
		var input = self.attr("input");
		if (!isEmptyObject(input)) { 
			self.append("<input type='hidden' id='"+input+"' name='"+input+"'/>");
		}
		if (!isEmptyObject(dv)) { 
			self.find("li[val='"+dv+"']").click();
			self.click();
		}
	});
	$(document).click(function(){
		$("div.select ul").hide();
		});
	//模拟上传	
	if(!$("input[type='file']").hasClass("file_1_short")){
		$("input[type='file']").wrap("<div class='file'></div>");
		$("input[type='file']").before("<input type='text' class='file_txt' name='file_txt' /><input type='button' value='浏览...' class='file_btn' />");
	}
	else{
		$("input[type='file']").wrap("<div class='file file_short'></div>");
		$("input[type='file']").before("<input type='text' class='file_txt' name='file_txt' /><input type='button' value='浏览...' class='file_btn' />");
	}
});