// JavaScript Document
$(function(){
	
	//文档目录收缩
    $(".doc_menu ul li span").click(function(){
    	$(this).siblings("dl").toggle();
    	$(this).parent("li").toggleClass("active");		
    })
	
	$(".doc_menu ul li").click(function(){
	    $(this).siblings("li.active").removeClass("active");
		$(this).siblings("li").find("dl:not('.child')").hide();
	})


    $(".doc_menu ul li dd.parent").find("a:eq(0)").click(function(){
    	$(this).toggleClass("active");
    	$(this).siblings(".child").toggle();
    })
    
	$(".doc_menu ul li dd.parent").click(function(){
	    $(this).siblings("dd.parent").find("a.active").removeClass("active");
		$(this).siblings("dd.parent").find(".child").hide();
	})
    
    /*文档表格处理*/
    $(".doc_txt table tr th:last-child").css("border-right","none");
    $(".doc_txt table tr:last-child td").css("border-bottom","none");
    $(".doc_txt table tr td:first-child").css("border-left","none");
    $(".doc_txt table tr td:last-child").css({"border-right":"none","text-align":"left"});
    $(".doc_txt table tr td:last-child span").css({"margin-left":"20px","margin-right":"20px"});

    /*文档错误代码表格处理*/
    $(".code_table tr").find("th:first-child:not(.th_100)").css("width","200px");
    //$(".code_table th:eq(1)").css("width","60%");
    $(".code_table th").css("text-align","left")
    $(".code_table td").css("text-align","left")
    $(".code_table th").find("span").css({"margin-left":"20px","margin-right":"20px"});
    $(".code_table td").find("span").css({"margin-left":"20px","margin-right":"20px"});
	
	
	//目录定位、收缩
	/*$("div").each(function(){
	    if($(this).hasClass("header_wrapper")){
		    //var wrapper_left = $(".header_wrapper").offset().left;
            $("#doc_aside").css({"position":"fixed","top":"20%","right":0,"z-index":9});
            $("#aside_fold").css("right",0);
			//$("#doc_aside").width(wrapper_left*0.8);
		}
	});*/
	$("#doc_aside").css({"position":"fixed","top":"20%","right":0,"z-index":9});
	
	
	$(window).resize(function(){
		/*$("div").each(function(){
	    if($(this).hasClass("display_wrapper")){
		    //var wrapper_left = $(".display_wrapper").offset().left;
            $("#doc_aside").css({"position":"fixed","top":"20%","right":0,"z-index":9});
            $("#aside_fold").css("right",0);
			//$("#doc_aside").width(wrapper_left*0.8);
		}
	});*/
	$("#doc_aside").css({"position":"fixed","top":"20%","right":0,"z-index":9});
    });
		  
    $("#aside_fold_link").click(function(){
		$("#doc_aside").hide();
		$("#aside_fold").slideDown();
		});
		
	$("#aside_fold").click(function(){
		$("#doc_aside").show();
		$("#aside_fold").hide();
		});
		
	//go to top至顶
	$(window).scroll(function(){
		if($(this).scrollTop()>100){ //当window的scrolltop距离大于100时，go to top按钮淡出，反之淡入
			$("#go_top").fadeIn();
			}else{
				$("#go_top").fadeOut();
				}
		});
	
	$("#go_top").click(function(){
		$("html,body").animate({scrollTop:0},800);//点击go to top按钮时，以800的速度回到顶部，这里的800可以根据你的需求修改
        return false;
		});
		
	//目录高度超过450px，就添加滚动条
	var aside_height = $(".doc_aside").height();
	if(aside_height>450){
	    $(".doc_aside").css({"height":"450px","overflow-y":"scroll"});
	}
});
