// JavaScript Document
$(function(){
	if($("#menu li:eq(0)").length > 0){
		var menu_left = $("#menu li:eq(0)").position().left;
		$(".sub_menu").css("padding-left",menu_left);
		$(window).resize(function(){
		var menu_left = $("#menu li:eq(0)").position().left;
		$(".sub_menu").css("padding-left",menu_left);	
		})
	}
	
	$("#menu li").click(function(){
		$(this).addClass("active").siblings("li").removeClass("active");
		})
		
	//左侧菜单
	$(".side_menu li").click(function(){
		$(this).addClass("active").siblings("li").removeClass("active");
		})
		
	//表格变色
	$("table tr:nth-child(2n+1)").addClass("unhover");
	
	//主体内容搜索查询区划下拉框处理
	$(".main_search .select").click(function(){
		$(this).css("color","#595959");		
		})

	//企业资质认证表格宽度处理
	$(".qualification_view .table_2 tr").each(function(){
		$(this).find("td").eq(0).css("width","100px");
		$(this).find("th").eq(0).css("width","100px");
	})

	//demo信息表格处理
	$(".demo_info .ul_left table tr").css("background","#fff");
})