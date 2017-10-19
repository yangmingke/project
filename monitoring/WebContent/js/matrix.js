
$(document).ready(function(){
	
	//tab标签切换
	$(".sidebar ul li ul li a").click(function(){
		var a_link = $(this).attr("rel");
		addTab($(this));
	});
	
	
	//链路质量图
	$("#sidebar").delegate("#menu_id_13", "click", function(){
		var mainFrames = document.getElementsByName('mainFrame');
		for(var i = 0 , j = mainFrames.length; i < j; i++){
			var mainFrame = mainFrames[i];
			var form = mainFrame.contentWindow.document.getElementById("nodeForm");
			if(!form){
				continue;
			}
			$(form).submit();
		}
    });
	//流量图
	$("#sidebar").delegate("#menu_id_15", "click", function(){
		var mainFrames = document.getElementsByName('mainFrame');
		for(var i = 0 , j = mainFrames.length; i < j; i++){
			var mainFrame = mainFrames[i];
			var form = mainFrame.contentWindow.document.getElementById("nodeForm");
			if(!form){
				continue;
			}
			$(form).submit();
		}
    });
	

	//给标签添加监听事件
	$(".tab_tit ul").delegate("li", "click", function(){
    	var id = $(this).find("a").attr("id");
    	if(id === "SR链路质量图"||id=="SR流量图"||id=="拓扑图"){
    		var mainFrames = document.getElementsByName('mainFrame');
    		for(var i = 0 , j = mainFrames.length; i < j; i++){
    			var mainFrame = mainFrames[i];
    			var form = mainFrame.contentWindow.document.getElementById("nodeForm");
    			if(!form){
    				continue;
    			}
    			$(form).submit();
    		}
    	}
    });

	function addTab(link) {

		// 如果tab标签已存在
        if ($("#" + $(link).attr("title")).length != 0){
        	$("#" + $(link).attr("title")).parent("li").addClass("current").siblings("li").removeClass("current");
        	$("#" + $(link).attr("title") + "_ctn").show().siblings("div").hide();
        	return;
        }
            

	    // 隐藏其他标签
	    $(".tab_tit li").removeClass("current");
	    $(".tab_ctn .ctn").hide();
	    
	    var id = $(link).attr("title") + "_mainFrame";
	    // 添加新标签及其内容
	    $(".tab_tit ul").append("<li class='current'><a class='tab_a' id='" + $(link).attr("title") + "' href='#'>" + $(link).html() + "</a><a href='#' class='remove'>x</a></li>");
	    $(".tab_ctn").append("<div class='ctn' id=" + $(link).attr("title") + "_ctn><iframe frameborder='0' allowfullscreen mozallowfullscreen webkitallowfullscreen  id=" + id + "  name='mainFrame' scrolling='no' width='100%' height='100%' src=" + $(link).attr("rel") +"></iframe></div>");
	    $("#" + $(link).attr("title") + "_ctn").show();
	    $("#"+ id).bind("load", function(){
	    	var obj = $(document.getElementById(id).contentWindow.document.body);
	    	var div = obj.find(".container-fluid");
	    	var h = div.height();
	    	var content = $("#content .tab_ctn");
	    	var ifm= document.getElementById(id);   
	    	var subWeb = document.frames ? document.frames[id].document : ifm.contentDocument;
	    	var widtht=document.body.clientWidth-$("#sidebar").width();
	    	if(ifm != null && subWeb != null) {
	    	   ifm.height = subWeb.body.scrollHeight;
	    	   ifm.width = subWeb.body.scrollWidth;
	    	   $(this).height(ifm.height);
	    	   $(this).width(widtht);
	    	}else{
	    		$(this).width(widtht);
	    	}
//	    	var tabContent = $("#content .tab_ctn");
//	    	var tabOffTop = (tabContent.offset() || {}).top;
//	    	var wH = $(window).height();
//	    	var offH = wH - tabOffTop;
//	    	var ch = content.height();
//	    	if(h === offH){
//	    		h = ch;
//	    	}
	    });
	}

	$('.tab_tit ul a.tab_a').live('click', function() {

	    // 获取对应标签内容id
	    var contentname = $(this).attr("id") + "_ctn";

	    // 隐藏其他标签
	    $(".tab_ctn .ctn").hide();
	    $(".tab_tit ul li").removeClass("current");

	    // 展示当前标签
	    $(this).parent().addClass("current");
	    $("#" + contentname).show();
	});

	$('.tab_tit ul a.remove').live('click', function() {

	    // 获取对应标签id
	    var tabid = $(this).parent().find(".tab_a").attr("id");

	    // 移除标签及其内容
	    var contentname = tabid + "_ctn";
	    $("#" + contentname).remove();
	    $(this).parent().remove();

	    // 如果没有标签，或者左侧还有标签，就显示第一个
		if ($(".tab_tit ul li.current").length == 0 && $(".tab_tit ul li").length > 0) {

		    // 找到第一个
		    var firsttab = $(".tab_tit ul li:first-child");
		    firsttab.addClass("current");

		    // 获取它的标签名及其内容
		    var firsttabid = $(firsttab).find("a.tab_a").attr("id");
		    $("#" + firsttabid + "_ctn").show();
		} 
		
	});


	// === Sidebar navigation === //
	//$(".submenu:eq(0)").addClass("open");
	$('.submenu > a').click(function(e)
	{
		e.preventDefault();
		var submenu = $(this).siblings('ul');
		var li = $(this).parents('li');
		var submenus = $('#sidebar li.submenu ul');
		var submenus_parents = $('#sidebar li.submenu');
		if(li.hasClass('open'))
		{
			if(($(window).width() > 768) || ($(window).width() < 479)) {
				submenu.hide();
			} else {
				submenu.show(250);
			}
			li.removeClass('open');
		} else 
		{
			if(($(window).width() > 768) || ($(window).width() < 479)) {
				submenus.hide();			
				submenu.show();
			} /*else {
				submenus.fadeOut(250);			
				submenu.fadeIn(250);
			}*/
			submenus_parents.removeClass('open');		
			li.addClass('open');
			var ul_html = submenu.find('li:first-child').find('ul').html();
			
			if(ul_html!=null){//alert(ul_html);
				submenu.find('li:first-child:has(li)').addClass('open');
				submenu.find('li:first-child:has(li)').find('ul').show();				
			}
		}
	});
	
	var ul = $('#sidebar > ul');
	
	$('#sidebar > a').click(function(e)
	{
		e.preventDefault();
		var sidebar = $('#sidebar');
		if(sidebar.hasClass('open'))
		{
			sidebar.removeClass('open');
			ul.slideUp(250);
		} else 
		{
			sidebar.addClass('open');
			ul.slideDown(250);
		}
	});   

    
    
	$('.subsubmenu a').click(function(){
		$(this).siblings("ul").slideToggle();
		$(this).parent("li.subsubmenu").siblings("li").find("ul").slideUp();
		$(this).parent("li.subsubmenu").toggleClass("open");		
		$(this).parent("li.subsubmenu").siblings("li").removeClass("open")
	})
	
	// === Resize window related === //
	$(window).resize(function()
	{
		if($(window).width() > 479)
		{
			ul.css({'display':'block'});	
			$('#content-header .btn-group').css({width:'auto'});		
		}
		if($(window).width() < 479)
		{
			ul.css({'display':'none'});
			fix_position();
		}
		if($(window).width() > 768)
		{
			$('#user-nav > ul').css({width:'auto',margin:'0'});
            $('#content-header .btn-group').css({width:'auto'});
		}
	});
	
	if($(window).width() < 468)
	{
		ul.css({'display':'none'});
		fix_position();
	}
	
	if($(window).width() > 479)
	{
	   $('#content-header .btn-group').css({width:'auto'});
		ul.css({'display':'block'});
	}
	
	// === Tooltips === //
	$('.tip').tooltip();	
	$('.tip-left').tooltip({ placement: 'left' });	
	$('.tip-right').tooltip({ placement: 'right' });	
	$('.tip-top').tooltip({ placement: 'top' });	
	$('.tip-bottom').tooltip({ placement: 'bottom' });	
	
	// === Search input typeahead === //
	$('#search input[type=text]').typeahead({
		source: ['Dashboard','Form elements','Common Elements','Validation','Wizard','Buttons','Icons','Interface elements','Support','Calendar','Gallery','Reports','Charts','Graphs','Widgets'],
		items: 4
	});
	
	// === Fixes the position of buttons group in content header and top user navigation === //
	function fix_position()
	{
		var uwidth = $('#user-nav > ul').width();
		$('#user-nav > ul').css({width:uwidth,'margin-left':'-' + uwidth / 2 + 'px'});
        
        var cwidth = $('#content-header .btn-group').width();
        $('#content-header .btn-group').css({width:cwidth,'margin-left':'-' + uwidth / 2 + 'px'});
	}
	
	// === Style switcher === //
	$('#style-switcher i').click(function()
	{
		if($(this).hasClass('open'))
		{
			$(this).parent().animate({marginRight:'-=190'});
			$(this).removeClass('open');
		} else 
		{
			$(this).parent().animate({marginRight:'+=190'});
			$(this).addClass('open');
		}
		$(this).toggleClass('icon-arrow-left');
		$(this).toggleClass('icon-arrow-right');
	});
	
	$('#style-switcher a').click(function()
	{
		var style = $(this).attr('href').replace('#','');
		$('.skin-color').attr('href','css/maruti.'+style+'.css');
		$(this).siblings('a').css({'border-color':'transparent'});
		$(this).css({'border-color':'#aaaaaa'});
	});
	
	$('.lightbox_trigger').click(function(e) {
		
		e.preventDefault();
		
		var image_href = $(this).attr("href");
		
		if ($('#lightbox').length > 0) {
			
			$('#imgbox').html('<img src="' + image_href + '" /><p><i class="icon-remove icon-white"></i></p>');
		   	
			$('#lightbox').slideDown(500);
		}
		
		else { 
			var lightbox = 
			'<div id="lightbox" style="display:none;">' +
				'<div id="imgbox"><img src="' + image_href +'" />' + 
					'<p><i class="icon-remove icon-white"></i></p>' +
				'</div>' +	
			'</div>';
				
			$('body').append(lightbox);
			$('#lightbox').slideDown(500);
		}
		
	});
	

	$('#lightbox').live('click', function() { 
		$('#lightbox').hide(200);
	});
	
    //ie placeholder处理
	if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
        $('input[placeholder]').each(function(){ 
       
        var input = $(this);       
        $(input).append("<div class='txt'>");
        $(input).val(input.attr('placeholder'));
               
        $(input).focus(function(){
             if (input.val() == input.attr('placeholder')) {
                 input.val('');
             }
        });
       
        $(input).blur(function(){
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.val(input.attr('placeholder'));
            }
        });
    });

        
        
    }

    //搜索区域宽度太长处理
	    /*var w1 = $(".widget-title").width();
		var w2 = $(".widget-title span.icon").width();
		var w3 = $(".widget-title h5").width();
	  	var sc_height = $(".widget-title .search ul").height();
	  	//$(".widget-title").height(sc_height);
	  	$(".widget-title span.icon").height(sc_height-15);
	  	$(".widget-title .search").width(w1-w2-w3-49);

	  	if ((screen.height <= 768) && (screen.width <= 1024)) {
	  		var w4 = 0;
			$(".widget-title .excelFrm input").each(function(){
				var ww = $(this).width();
				w4 += ww;
			})
			var w5 = w4 + 29;
		  	var sc_height = $(".widget-title .search ul").height();
		  	//$(".widget-title").height(sc_height);
		  	$(".widget-title span.icon").height(sc_height-15);
		  	$(".widget-title .search").width(w1-w2-w3-49);
		  	$(".widget-title .search ul").width(w1-w2-w3-w5-51);

	  	} */
	

	//头部
	var w = $(window).width();
	var h = $(window).height();
	$(".header_right").width(w-220);
	$("#content").height(h-130);
	//$(".submenu").parent("ul").height(h-150);

	$(window).resize(function(){
		var w = $(window).width();
		var h = $(window).height();
		$(".header_right").width(w-220);
		$("#content").height(h-130);
	})
	
});

