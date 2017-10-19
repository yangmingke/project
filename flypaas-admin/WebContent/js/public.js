//公共js脚本

/**
 * 弹层居中
 * @param id
 */
function floatCenter(id){
	var div = $("#"+id);
	var div_left = (window.screen.width - div.width()) / 2;
	var div_top = (window.screen.height - div.height()) / 2 - 60;
	div.css({left: div_left, top: div_top});
}

/**
 * 显示弹层
 * @param id 弹层id
 * @param url 弹层页面，可以为null
 */
function showBox(id, url){
	if($(".background_box").length == 0){
		$("body").append("<div class='background_box' style='display:none;'></div>");
	}
	if($("#"+id).length == 0){
		$("body").append("<div id='"+id+"' class='float_box' style='display:none;'></div>");
	}
	
	var div = $("#"+id);
	if(url == null){
		floatCenter(id);
	}else if(div.is(":empty")){
		div.load(url, function(){
			floatCenter(id);
		});
	}
	$(".background_box").show();
	div.show();
}

/**
 * 隐藏弹层
 * @param id 弹层id
 */
function hideBox(id){
	$(".background_box, #"+id).hide();
}

/**
 * 将表单提交到一个div
 * @param form
 * @param div_id
 */
function submitDiv(form, div_id){
	$("#" + div_id).load(form.action, $(form).serialize());
	return false;
}

/**
 * 新窗口查看文件
 * @param img img标签
 */
function viewFile(img){
	open($(img).attr("src"), "viewFile");
}

/**
 * 刷新页面顶部未读的消息个数
 */
function refreshMsg(){
	$.ajax({
		type: "post",
		url: ctx + "/msg/getUnreadCount",
		success: function(data){
			if(data!=null && data>0){
				$("#header_msg").text("消息("+data+")");
			}
		}
	});
}
