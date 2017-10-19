// JavaScript Document

$(function(){

	//导航菜单
    var ytx={};
	$('[t_nav]').hover(function(){
		var _nav = $(this).attr('t_nav');
		clearTimeout( ytx[ _nav + '_timer' ] );
		ytx[ _nav + '_timer' ] = setTimeout(function(){
			$('#'+_nav).stop(true,true).slideDown(200);
		}, 150);
	},function(){
		var _nav = $(this).attr('t_nav');
		clearTimeout( ytx[ _nav + '_timer' ] );
		ytx[ _nav + '_timer' ] = setTimeout(function(){
			$('#'+_nav).stop(true,true).slideUp(200);
		}, 150);
	});	

	//导航绿色标题高度处理
	$(".submenu dl").each(function(){
		var dl_h = $(this).height();
		$(this).find("dt").height(dl_h);
	})

	//首页短信、im、语音、视频动画效果
	$(".box1 ul li").hover(function(){
		$(this).find(".unhover").find("i").animate({bottom:-145,opacity: '0'},1000);
		$(this).find(".unhover").find(".txt").animate({left:-125,opacity: '0'},1000);
		$(this).find(".hover").show().find("i").animate({top:0},1000);
		$(this).find(".hover").show().find(".txt").animate({right:0},1000);
	},function(){
		$(this).find(".unhover").find("i").animate({bottom:0,opacity: '1'},1000);
		$(this).find(".unhover").find(".txt").animate({left:0,opacity: '1'},1000);
		$(this).find(".hover").find("i").animate({top:-125},1000);
		$(this).find(".hover").find(".txt").animate({right:-110},1000);
	})

	//首页“简单”、“可靠”、“专注”、“全球”动画效果
	var current = $(".index_2 span.txt.current").index();
	$(".index_2 span.txt").not(".current").hover(function(){
		var span_index = $(this).index();
		$(this).addClass("current");
		$(".txt_desc").find("span").eq(span_index).fadeIn(800).siblings("span").fadeOut(800);
	},function(){
		var span_index = $(this).index();
		$(this).removeClass("current");
		$(".txt_desc").find("span").eq(span_index).fadeOut(800).siblings("span").eq(current).fadeIn(800);
	})


	//首页客户图标鼠标放上去状态变化
	$(".index_4 ul li").hover(function(){
		var img_src = $(this).find("img").attr("src");
		var img_name = img_src.substring(7).replace(".png","");
		//alert(img_name);
		$(this).find("img").attr("src","/front/"+img_name+"_hover.png");
	},function(){
		var img_src = $(this).find("img").attr("src");
		var img_name = img_src.substring(7).replace("_hover.png","");
		$(this).find("img").attr("src","/front/"+img_name+".png");
	})

	//语音通知手风琴效果
	$(".voice_2 ul li").each(function(){
		var fold = $(this).find(".fold");
		var unfold = $(this).find(".unfold");
		if(fold.is(":hidden")){
			$(this).width(680);
		}else{
			$(this).width(100);
		}
	})

	$(".voice_2 ul li").click(function(){
		var li_index = $(this).index();
		$(this).animate({width:680},200);
		$(this).find(".unfold").show();
		$(this).find(".fold").hide();
		$(this).siblings().animate({width:100},200);
		$(this).siblings().find(".unfold").hide();
		$(this).siblings().find(".fold").show();
	})

	//下拉框处理
	$("div.select_box ul li:even").css("background","#f5f5f5");

	$("div.select_box").click(function(e){
		if("readonly" == $(this).attr('readonly')){
			return false;
		}
		e.stopPropagation();
		$(this).children("ul").toggle();
		$(this).toggleClass("focus");
	});

	//下拉选择
	$("div.select_box ul li").click(function(){
		var self=  $(this);
		var select = self.parents('div.select_box');
		var input = select.attr("input");
		if(!$.isEmptyObject(input)){
			select.find("input[name='"+input+"']").attr("value",self.attr("val"));
		}
		var li_val = self.text();
		self.addClass("selected").siblings("li").removeClass("selected");
		self.parent("ul").siblings("span").html(li_val);
	});
	
	//模拟下拉列表框
	$("div.select_box").each(function(){
		var self = $(this);
		var select_width =self.width();
		self.children("span").width(select_width-27);
		self.children("ul").width(select_width);
		self.children("ul").children("li").width(select_width-12);
		var dv = self.attr("defaultValue");
		var input = self.attr("input");
		if(!$.isEmptyObject(input)){
			self.append("<input type='hidden' id='"+input+"' name='"+input+"'/>");
		}
		if(!$.isEmptyObject(dv)){
			self.find("li[val='"+dv+"']").click();
			self.click();
		}
	});
	$(document).click(function(){
		$("div.select_box ul").hide();
		$("div.select_box").removeClass("focus");
		});

	/*-------下载页面效果---------*/
	$(".download_tab_tit ul li").click(function(){
		var li_index = $(this).index();
		$(this).addClass("current").siblings("li").removeClass("current");
		$(this).find("i").show().end().siblings("li").find("i").hide();
		$(".download_tab_ctn").find("img").eq(li_index).show().siblings("img").hide();
		if(li_index==0){
			$(this).parents(".download_tab_tit").css("border-color","#fff");
		}else if(li_index==1){
			$(this).parents(".download_tab_tit").css("border-color","#c4f3d7");
		}else{
			$(this).parents(".download_tab_tit").css("border-color","#bcdef2");
		}

		//对应各版本信息展示
		$(".download_box").find(".download_detail").eq(li_index).show().siblings(".download_detail").hide();
	})

	$(".download_tit ul li").click(function(){
		var li_index = $(this).index();
		$(this).addClass("current").siblings("li").removeClass("current");
		$(this).parents(".download_tit").siblings(".dowload_info").find(".info").eq(li_index).show().siblings(".info").hide();
	})

	//更新日志点击
	$(".update_log").click(function(){
		$(this).addClass("current").parents(".info_list").siblings(".info_list").find(".update_log").removeClass("current");
	})

	//价格国际资费搜索
	$(".search_data ul li:odd").css("background","#f5f5f5");
	/*$(".search_data").click(function(){
		$(this).find("ul").toggle();
	})

	$(".search_data ul li").click(function(){
		var code_val = $(this).find(".code").html();
		var country_val = $(this).find(".country").html();
		$(this).parents(".search_data").find(".code_cur").html(code_val);
		$(this).parents(".search_data").find(".country_cur").html(country_val);
	});*/


	/*--------------------------弹层相关js start------------------------*/
	/**
	 * 弹层居中显示（窗口固定时）
	 */
	var w = $(window).width(); //窗口宽度
	var h = $(window).height(); //窗口高度

	var fw = $(".float_box").width();
	var fh = $(".float_box").height();
	var fw = (w - fw)/2;
	var fh = (h - fh)/2;
	$(".float_box").css({"left":fw,"top":fh});


	/**
	 * 弹层居中显示（窗口变化时）
	 */
	$(window).resize(function(){
		var w = $(window).width(); //窗口宽度	
		var h = $(window).height(); //窗口高度	

		var fw = $(".float_box").width();
		var fh = $(".float_box").height();
		var fw = (w - fw)/2;
		var fh = (h - fh)/2;
		$(".float_box").css({"left":fw,"top":fh});
	})

	//弹层显示
    $(".float_link").click(function(){
    	$(".background_box").show();
    	$(".float_box").show();
    	$("body").addClass("float_body");
    });

    //弹层关闭
    $(".float_box .cancel_btn").click(function(){
    	$(".background_box").fadeOut();
    	$(".float_box").fadeOut();
    	$("body").removeClass("float_body");
    });

    /*--------------------------弹层相关js end------------------------*/


	//ie placeholder处理
	if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
        $('input[placeholder]').each(function(){ 
       
        var input = $(this);   
        var input_value = input.attr('placeholder');    
        $(input).wrap('<span class="simulation"></span>')
        $(input).after("<label>"+input.attr('placeholder')+"</label>");

        $(input).focus(function(){
             	$(this).next('label').text('');
        });
       
        $(input).blur(function(){
            if (input.val() == '' || input.val() == input_value) {
                $(this).next('label').text(input_value);
            }
        });

        $("span.simulation label").click(function(){
             	$(this).text('');
             	$(this).prev("input").focus();
        });
       
    });       
        
    }

    /*返回顶部*/
    $("#gotop").click(function(){
        $(window).scrollTop(0,0);
    })


 	

})
