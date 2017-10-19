// JavaScript Document
$(function(){
	//ie透明度
	$("#background_box").css("filter","alpha(opacity=40)");
	$(".background_box").css("filter","alpha(opacity=40)");

    $("input[type='text'],input[type='password']").focus(function(){
    	$(this).attr("placeholder","");
    })
    
    $("input[type='text'],input[type='password']").each(function(){
    	var placeholder_val=$(this).attr("placeholder");
    	$(this).blur(function(){
    	$(this).attr("placeholder",placeholder_val);
    	$(this).css("color","#aaa");
    })
    })
    
    //判断ie11，border-radius处理
    if ($.browser.mozilla && $.browser.version == '11.0') { 
     $("#menu ol").removeAttr("border-radius");
     $("#menu ol li:first-child > a").removeAttr("border-radius");
     $("#menu ol li:last-child > a").removeAttr("border-radius");
     $("#menu ol li:first-child > a").removeAttr("border-radius");
     $("#menu ol li:first-child > a").removeAttr("border-radius");
     $("#menu ol li:first-child > a").removeAttr("border-radius");
    }
    


	//登录注册点击
	$("#log").click(function(){
		$(".log_box").show();
		$("#background_box").show();
	})
	
	//登录用户名密码聚焦时高亮
	$("#log_username").focus(function(){
		$(this).css("z-index","1");
		$("#log_password").css("z-index","0");
	})
	$("#log_password").focus(function(){
		$(this).css("z-index","1");
		$("#log_username").css("z-index","0");
	})
	
	//弹层居中
	var window_width=$(window).width();
	$(window).resize(function(){
		var window_width=$(window).width();
		
		$(".float_box").css("left",window_width/2-260);
		})
	$(".float_box").css("left",window_width/2-260);
	
	//弹层关闭
	$(".float_tit .close,.cancel_btn").click(function(){
		$(".float_box").fadeOut("slow");
		$("#background_box").fadeOut("slow");
		$(".background_box").fadeOut("slow");
		})
	
	//表格变色处理
	$("table tr:nth-child(2n)").addClass("even");
	$("table tr:nth-child(2n+1)").addClass("odd");
	$("table tr td:first-child").css("border-left","1px #e0eefe solid");
	$("table tr td:last-child").css("border-right","1px #e0eefe solid");

    
    //登录进入的首页底部tab图表
	$(".chart_tit ul li").click(function(){
		var li_index=$(this).index();
		$(this).addClass("active").siblings("li").removeClass("active");
		//$(".chart_ctn").find("div").eq(li_index).show().siblings("div").hide();
	})
	
	//资费配置表格最后一列处理
	$(".fee_ctn table tr td:last-child").css({"width":"260px","padding-left":"90px","padding-right":"90px"});

	//模拟上传
	$("input[type='file']").wrap("<div class='file'></div>");
	if(!$("input[type='file']").hasClass("choose")){
		$("input[type='file']").before("<input type='text' class='file_txt' name='file_txt' /><input type='button' value='浏览' class='file_btn' />");
	}
	else{
		$("input[type='file']").before("<input type='button' value='选择文件' class='choose_btn' />");
	}
	
	
	//表单单选
	$("p.sort a").click(function(){
		$(this).addClass("active").siblings("a").removeClass("active");
	})

	//消息账单tab
	$(".bills_tab_tit ul li").click(function(){
		var li_index=$(this).index();
		$(this).addClass("active").siblings("li").removeClass("active");
		$(".bills_tab_ctn").find("div").eq(li_index).show().siblings("div").hide();
	})

	//单选框处理
	$("input[type='radio']").wrap("<span class='radio'></span>");
	$("input[type='radio']").click(function(){
		if($(this).attr("checked")){
			$(this).parent("span").addClass("checked").parent("span").siblings("span").find(".radio").removeClass("checked");
			$(this).parent("span").next("img").addClass("checked").parent("span").siblings("span").find("img").removeClass("checked");;
			}
		});

	$("input[type='radio']").each(function(){
		if($(this).attr("checked")){
			$(this).parent("span").addClass("checked").parent("span").siblings("span").find(".radio").removeClass("checked");
			$(this).parent("span").next("img").addClass("checked").parent("span").siblings("span").find("img").removeClass("checked");;
			}
		});

    //问号详情
    $(".set_list").eq(0).css("margin-left","0px");
	$("i.ask").hover(function(){
    	$(this).next(".ask_ctn").show();
		var ask_left = $(this).position().left;
	   	$(this).next(".ask_ctn").css("left",ask_left+35);
    },function(){
    	$(this).next(".ask_ctn").hide();
    });

    //应用详情内容列表项处理
    $("p.p_list").each(function(){
    	$(this).find("span").eq(0).css("margin-left","0px");
    })

    //主体内部标题右侧点击收缩
    $(".tab_ctn .info h2 i").click(function(){
    	$(this).toggleClass("down");
    	$(this).parent("h2").next("table").toggle();
    })

    
    
    //服务资费，月结账单，client账号账单列项样式处理
    $(".service_list dl:even").css("border-left","1px solid #E0EEFE");
    $(".service_list dl:odd").css("border-right","1px solid #E0EEFE");
    $(".service_list").find("dl:eq(0),dl:eq(1)").css("background","#EEF6FF");
    $(".service_list").find("dl:eq(4),dl:eq(5)").css("background","#EEF6FF");
    $(".service_list").find("dl:eq(8),dl:eq(9)").css("background","#EEF6FF");
    $(".service_list").find("dl:eq(12),dl:eq(13)").css("background","#EEF6FF");
    $(".service_list").find("dl:eq(16),dl:eq(17)").css("background","#EEF6FF");
    $(".service_list").find("dl:eq(20),dl:eq(21)").css("background","#EEF6FF");
    $(".service_list").find("dl:eq(23),dl:eq(24)").css("background","#EEF6FF");
    $(".service_list").find("dl:eq(27),dl:eq(28)").css("background","#EEF6FF");
    $(".service_list").find("dl:eq(30),dl:eq(31)").css("background","#EEF6FF");
    $(".service_list").find("dl:eq(33),dl:eq(34)").css("background","#EEF6FF");
    $(".service_list").find("dl:eq(37),dl:eq(38)").css("background","#EEF6FF");

    
    
	

	})
