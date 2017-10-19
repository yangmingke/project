//javascript

$(function(){
	
	//左侧菜单
	$(".menu .tab ul li").click(function(){
		var li_index = $(this).index();
		$(".menu .tab_ctn").find("dl").eq(li_index).show().siblings("dl").hide();
	});

    /*--------------------------窗口固定时---------------------------*/
	var w = $(window).width(); //窗口宽度
	var h = $(window).height(); //窗口高度

	/**
	 * 当主体部分宽度大于或者小于1440时，主体部分右侧布局宽度
	 */
	var w0 = $(".item_box").width(); //主体部分布局宽度
	var w1 = $(".item_left").width(); //主体部分左侧布局宽度

	if(w0>=1440){
		$(".item_right").css("width",1440-26-w1);		
	}else{
		var w2 = w0-26-w1;
		$(".item_right").css("width",w2);
	}


	/**
	 * 应用发布步骤图标间距
	 */
	var w3 = (w1-492)/8;
	$(".item_left .step .step_space").css({"margin-left":w3,"margin-right":w3});


	/**
	 * 左侧菜单高度
	 */
//	var h0 = $("body").height()-80-60;
//	var h1 = $(".main").height()+40;
//	var h2 = $(".side").height();
//	var h3 = $(".profile").height();
//	var h4 = $(".quick_link").height();
//	$(".tab,.tab ul").css("height",h0-h3);
//	
//	$(window).scroll(function(){
//		var h1 = $(".main").height()+40;
//		$(".tab,.tab ul").css("height",h1-h3);
//	});
//	
//	$("body").resize(function(){
//		var h1 = $(".main").height()+40;
//		$(".tab,.tab ul").css("height",h1-h3);
//	});


	/**
	 * 弹层居中显示
	 */
	var fw = $(".float_box").width();
	var fw0 = $(".float_box0").width();
	var fw1 = $(".float_box1").width();
	var fh = $(".float_box").height();
	var fh0 = $(".float_box0").height();
	var fh1 = $(".float_box1").height();
	var fw = (w - fw)/2;
	var fw0 = (w - fw0)/2;
	var fw1 = (w - fw1)/2;
	var fh = (h - fh)/2;
	var fh0 = (h - fh0)/2;
	var fh1 = (h - fh1)/2;
	$(".float_box").css({"left":fw,"top":fh});
	$(".float_box0").css({"left":fw0,"top":fh0});
	$(".float_box1").css({"left":fw1,"top":fh1});
	

	/*--------------------------窗口固定时---------------------------*/
	

    /*--------------------------窗口变化时---------------------------*/
	$(window).resize(function(){
		var w = $(window).width(); //窗口宽度	
		var h = $(window).height(); //窗口高度	

		/**
		 * 当主体部分宽度大于或者小于1440时，主体部分右侧布局宽度
		 */
		var w0 = $(".item_box").width(); //主体部分布局宽度
		var w1 = $(".item_left").width(); //主体部分左侧布局宽度
		if(w0>=1440){
			$(".item_right").css("width",1440-26-w1);
		}else{
			var w2 = w0-26-w1;
			$(".item_right").css("width",w2);
		}


		/**
		 * 应用发布步骤图标间距
		 */
		var w3 = (w1-492)/8;
		$(".item_left .step .step_space").css({"margin-left":w3,"margin-right":w3});


		/**
		 * 左侧菜单高度
		 */
//		var h0 = $(".content").height();
//		var h1 = $(".profile").height();
//		$(".tab,.tab ul").css("height",h0-h1);
		

		/**
		 * 弹层居中显示
		 */
		var fw = $(".float_box").width();
		var fw0 = $(".float_box0").width();
		var fw1 = $(".float_box1").width();
		var fh = $(".float_box").height();
		var fh0 = $(".float_box0").height();
		var fh1 = $(".float_box1").height();
		var fw = (w - fw)/2;
		var fw0 = (w - fw0)/2;
		var fw1 = (w - fw1)/2;
		var fh = (h - fh)/2;
		var fh0 = (h - fh0)/2;
		var fh1 = (h - fh1)/2;
		$(".float_box").css({"left":fw,"top":fh});
		$(".float_box0").css({"left":fw0,"top":fh0});
		$(".float_box1").css({"left":fw1,"top":fh1});

	});
    /*--------------------------窗口变化时---------------------------*/
    
	/*$(window).load(function(){
		/*var h0 = $("body").height();
		var h1 = $(".foot").height();
		$(".tab,.tab ul").css("height",h0-h1);*/
		/*var h_c = $(".content").height();
		var h_p = $(".profile").height();
		var h_q = $(".quick_link").height();
		$(".tab,.tab ul").css("height",h_c-h_p-h_q);
	});*/

    //弹层显示
    $(".float_link").click(function(){
    	$(".background_box").show();
    	$(".float_box").show();
    	$("body").addClass("float_body");
    });

    $(".float_link1").click(function(){
    	$(".background_box").show();
    	$(".float_box1").show();
    	$("body").addClass("float_body");
    });

    //弹层关闭
    $(".float_box .cancel_btn").click(function(){
    	$(".background_box").fadeOut();
    	$(".float_box").fadeOut();
    	$("body").removeClass("float_body");
    });
    
    $(".float_box0 .cancel_btn").click(function(){
    	$(".background_box").fadeOut();
    	$(".float_box0").fadeOut();
    	$("body").removeClass("float_body");
    });

    $(".float_box1 .cancel_btn").click(function(){
    	$(".background_box").fadeOut();
    	$(".float_box1").fadeOut();
    	$("body").removeClass("float_body");
    });

    //问号
    $(".ask_icon").hover(function(){
    	var ask_h1 = $(this).siblings(".ask_ctn").height();
    	var ask_h2 = 0-ask_h1-34;
    	$(this).toggleClass("hover");
    	$(this).siblings(".ask_ctn").toggle();
    	$(this).siblings(".ask_ctn").css("top",ask_h2);
    	
    });
});
	function href2url(url){
		location.href=url;
	}