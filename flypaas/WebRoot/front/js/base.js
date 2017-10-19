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


    //表格变色处理
	$("table tr:nth-child(2n)").addClass("even");
	$("table tr:nth-child(2n+1)").addClass("odd");

	//复选框处理
 	$("input[type='checkbox']").wrap("<span class='checkbox'></span>");
	
	$("input[type='checkbox']").click(function(){
		if($(this).attr("checked")){
			$(this).attr("checked",true);
			$(this).parent("span").addClass("checked");
			}
		else{
			$(this).attr("checked",false);
			$(this).parent("span").removeClass("checked");
			}
		});
	
	$("input[type='checkbox']").each(function(){
		if($(this).attr("checked")){
			$(this).parent("span").addClass("checked");
			}
	});


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
    });
 	
    String.prototype.endWith=function(str){    
    	  var reg=new RegExp(str+"$");    
    	  return reg.test(this);       
    };
    
    var bodyId = $("body").attr("id");
    $("#menu"+bodyId).addClass("active");
});
