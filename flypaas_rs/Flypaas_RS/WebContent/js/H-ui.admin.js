/*H-ui.admin.js v2.0 date:15:42 2014-11-24 by:guojunhui*/
/*获取顶部选项卡总长度*/
function tabNavallwidth(){
	var taballwidth=0,
		$tabNav = $(".acrossTab"),
		$tabNavWp = $(".Hui-tabNav-wp"),
		$tabNavitem = $(".acrossTab li"),
		$tabNavmore =$(".Hui-tabNav-more");
	if (!$tabNav[0]){return;}
	$tabNavitem.each(function(index, element) {
        taballwidth+=Number(parseFloat($(this).width()+60));
    });
	$tabNav.width(taballwidth+25);
	var w = $tabNavWp.width();
	if(taballwidth+25>w){
		$tabNavmore.show();
	}
	else{
		$tabNavmore.hide();
		$tabNav.css({left:0});
	}
}


/*------------------------销毁session    -                   */
function userExit(){
	/*$.post("/userController/userExit.action",function(data1){
		var data = eval(data1)
		if(data ==1){
			window.location.href ='/login.jsp';
		}else{
			layer.msg('退出失败!',2,1);
		}
	})*/
	location.href="/userController/userExit.action";
}

/*全选*/
$("table thead th input:checkbox").on("click" , function(){
	var that = this;
	$(this).closest("table").find("tr > td:first-child input:checkbox").each(function(){
		this.checked = that.checked;
		$(this).closest("tr").toggleClass("selected");
	});
});
/*批量删除*/
function resourceBatchDel(){
	var option = $('input[name="batchDel"]:checked');
	if(option.length==0){
		alert('你还没有选择任何内容！'); 
	}else{
		layer.confirm('确认要删除吗？',function(index){  
		    // 获取所有选中的checked框  
		    //var option = $('input[name="batchDel"]:checked');  
		    var checkedId = "";  
		    var boo=true;  
		    //拼接除全选框外,所有选中的id,  
		    for (var i = 0, len = option.length; i < len; i++) {  
		        if (boo) {  
		            if (option[i].batchDel=='allSelect') {  
		                boo=true;  
		            }else {  
		                boo=false;  
		                checkedId += option[i].value;  
		            }  
		        }else{  
		            checkedId += ","+option[i].value;  
		        }  
		    }
		    $.ajax({  
	            type: "post",  
	            url:'/resourceController/batchDelResource.action',  
	            data:{  
	            	"checkedId":checkedId
	            },  
	            dataType:"json",  
	            success : function(data) { 
		        	var json = eval("("+data+")");
		        	if(json == 0){
		        		layer.msg('添加失败!',1,2,function(){
		        			window.close();
			        		window.location.reload(true);
		        		});
		        	}
		        	if(json == 1){
		        		layer.msg('添加成功!',1,1,function(){
		        			window.close();
			        		window.location.reload(true);
		        		});
		        	}
		        },
		        error:function(code){   
		        	layer.msg('服务器出错!',1,3,function(){
	        			window.close();
		        		window.location.reload(true);
	        		});
		        }   
	          });  
	  });  
	}
	
	
}

function resourceBatchDel1(){
	var chk_value =[]; 
	$('input[name="batchDel"]:checked').each(function(){ 
		chk_value.push($(this).parents("td").next().val()); 
	});
	if(chk_value.length==0){
		alert('你还没有选择任何内容！'); 
	}else{
		layer.confirm('确认要删除吗？',function(index){
    		alert(chk_value);
    		layer.msg('已删除!',2);
     		return true;
    	})
	}
	
} 

/*弹出层*/
function layer_show(w,h,title,url){
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	if (title == null || title == '') {
		title=false;
	};
	if (url == null || url == '') {
		url="404.html";
	};
	$.layer({
    	type: 2,
    	shadeClose: true,
    	title: title,
		maxmin:false,
		shadeClose: true,
    	closeBtn: [0, true],
    	shade: [0.8, '#000'],
    	border: [0],
    	offset: ['20px',''],
    	area: [w+'px', h +'px'],
    	iframe: {src: url}
	});
}
/*---------------------------------------------------------用户管理--------------------------------------*/
/*用户-添加*/
function user_add(w,h,title,url){
	layer_show(w,h,title,url);
}
/*用户-查看*/
function user_show(id,w,h,title,url){
	layer_show(w,h,title,url);
}
/*用户-密码-修改*/
function user_password_edit(id,username,w,h,title,url){
	url = url+'?id='+id+'&username='+username;
	layer_show(w,h,title,url);
}

/*用户-编辑*/
function user_edit(id,w,h,title,url){
	url = url+'?id='+id+'&username='+id;
	layer_show(w,h,title,url);
}
/*用户-编辑-保存*/
function user_edit_save(obj,id){
	var i = parent.layer.getFrameIndex();
	parent.layer.close(i);
}

/*用户-停用*/
function user_stop(obj,id,status,role){
	layer.confirm('确认要停用吗？',function(index){
		$.post("/userController/editUserStatus.action",{"sid":id,"status":status},function(data1){
			var data = eval(data1)
			if(data ==1){
				layer.msg('已停用!',2,1);
				location.replace(location.href);
			}else{
				layer.msg('操作失败!',2,2);
			}
		})
	});
}
/*用户-启用*/
function user_start(obj,id,status){
	layer.confirm('确认要启用吗？',function(index){
		$.post("/userController/editUserStatus.action",{"sid":id,"status":status},function(data1){
			var data = eval(data1)
			if(data ==1){
				layer.msg('已启用!',2,1);
				location.replace(location.href);
			}else{
				layer.msg('操作失败!',2,2);
			}
		})
	});
}
/*用户-删除*/
function user_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.post("/userController/delUser.action",{"sid":id},function(data1){
			var data = eval(data1)
			if(data ==1){
				layer.msg('已删除!',2,1);
				location.replace(location.href);
			}else{
				layer.msg('操作失败!',2,2);
			}
		})
	});
}

/*---------------------------------------------------资源管理----------------------------------------------*/
/*资源-添加*/
function resource_add(w,h,title,url){
	layer_show(w,h,title,url);
}
/*资源-查看*/
function resource_show(id,w,h,title,url,status,ip,countryId,provinceId){
	url = url+'?id='+id+'&countryId='+countryId+'&provinceId='+provinceId;
	layer_show(w,h,title,url);
}

/*资源-编辑*/
function resource_edit1(id,w,h,title,url,status,ip,countryId,provinceId){
	url = url+'?id='+id+'&countryId='+countryId+'&provinceId='+provinceId;
	if(status == "4"){
		//判断并发量是否为0
		$.post("/resourceStatusController/queryStatusByIpNewData.action",{"ip":ip},function(data1){
			var data = eval(data1)
			if(data ==1){
				layer_show(w,h,title,url);
			}else{
				layer.msg('该资源正在使用不能修改!',2,7);
			}
		})
	}else if(status == 6){
		layer.msg('该资源正在使用不能修改!',2,7);
	}else{
		layer_show(w,h,title,url);
	}
}
/*资源-编辑-保存*/
function resource_edit_save(obj,id){
	var i = parent.layer.getFrameIndex();
	parent.layer.close(i);
}

/*资源-停用*/
function resource_stop(obj,id,status){
	layer.confirm('确认要停用吗？',function(index){
		layer.msg('已删除!',1);
	});
}
/*资源-启用*/
function resource_start(obj,id,status){
	layer.confirm('确认要启用吗？',function(index){
		layer.msg('已删除!',1);
	});
}
/*资源-删除*/
function resource_del(obj,id,status,ip){
	if(status != 3){
		layer.confirm('确认要删除吗？',function(index){
			$.post("/resourceController/delResouce.action",{"rtppSid":id,"ip":ip},function(data1){
				var data = eval(data1)
				if(data == 1){
					layer.msg('已删除!',2,1);
					location.replace(location.href);
				}if(data == 2){
					alert('资源处于就绪状态,不能删除!',2,1);
					location.replace(location.href);
				}else{
					layer.msg('操作失败!',2,3);
					location.replace(location.href);
				}
			})
		});
	}else{
		layer.msg('该资源不能删除!',2,7);
	}
}


/*资源状态修改*/
/**
 * 修改 该节点的状态为 status
 */
/*
function editStatus(status,id,ip){
	if(status == 5){
		$.post("/resourceStatusController/queryStatusByIpNewData.action",{"ip":ip},function(data1){
			var data = eval(data1)
			if(data ==1){
				$.post("/resourceController/editStatus.action",{"rtppSid":id,"status":status},function(data1){
					var data = eval(data1)
					if(data ==1){
						layer.msg('修改成功!',2,1);
						location.replace(location.href);
					}else{
						layer.msg('操作失败!',2,1);
					}
				});
			}else{
				layer.msg('该资源不能修改!',2,1);
			}
		})
	}else{
		$.post("/resourceController/editStatus.action",{"rtppSid":id,"status":status},function(data1){
			var data = eval(data1)
			if(data ==1){
				layer.msg('修改成功!',2,1);
				location.replace(location.href);
			}else{
				layer.msg('操作失败!',2,1);
			}
		});
	}
}
*/



function resource_editStatus(id,w,h,title,url,status,ip){
	layer_show(id,w,h,title,url,status,ip);
}

/*--------------------  资源状态管理  --------------------*/
/**
 * 点击资源状态列表的搜索资源  按时间段/名称/ip进行搜索
 */
function searchResource(){
	var dateMin = $('#datemin').val();
	var dateMax = $('#datemax').val();
	var ip = $('#ip').val();
	if(dateMin=="" || dateMax ==""){
		alert("请输入完整条件");
	}else{
		/*$.post("/resourceStatusController/queryResourceRealStatus.action",{"dateMin":dateMin,"dateMax":dateMax,"ip":ip},function(data){
			
		})*/
		location.href="/resourceStatusController/queryResourceRealStatus.action?dateMin="+dateMin +"&dateMax="+dateMax +"&ip="+ip;
	}
	
	
}

function payOrder_show(w,h,title,url,balance){
	url = url+'?balance='+balance;
	layer_show(w,h,title,url,balance);
}




/*----------------------------------------账单管理-------------------------------------------*/
function payOrder_del(obj,id){
	layer.confirm('确认要取消吗？',function(index){
		$.post("/billFlowController/updateBillFlow.action",{"id":id},function(data){
			var json =eval("("+data+")");
			if(json == 1){
				layer.msg('取消成功!',2,1);
				location.replace(location.href);
			}
			if(json == 0){
				layer.msg('取消失败!',2,3);
			}
			if(json == 2){
				layer.msg('取消失败，该提款记录状态已改变!',2,1);
				location.replace(location.href);
			}
		})
	});
}




/*------------------------------------------图片库-----------------------------------------------*/
//图片库-分类-添加
function picture_class_add(obj){
	var v = $("#picture-class-val").val();
	if(v==""||v==null){
		return false;
	}else{
		//ajax请求 添加分类
	}
}

//图片库-分类-编辑
function picture_class_edit(id,w,h,title,url){
	layer_show(w,h,title,url);
}
//图片库-添加
function picture_add(w,h,title,url){
	layer_show(w,h,title,url);
}
//图片库-编辑
function picture_edit(id,w,h,title,url){
	layer_show(w,h,title,url);
}
//图片库-下架
function picture_xiajia(obj,id){
	$(obj).parents("tr").find(".picture-manage").prepend('<a style="text-decoration:none" onClick="picture_fabu(this,\'10001\')" href="javascript:;" title="发布"><i class="icon-hand-up"></i></a>');
	$(obj).parents("tr").find(".picture-status").html('<span class="label">已下架</span>');
	$(obj).remove();
}
//图片库-发布
function picture_fabu(obj,id){
	$(obj).parents("tr").find(".picture-manage").prepend('<a style="text-decoration:none" onClick="picture_xiajia(this,\'10001\')" href="javascript:;" title="下架"><i class="icon-hand-down"></i></a>');
	$(obj).parents("tr").find(".picture-status").html('<span class="label label-success">已发布</span>');
	$(obj).remove();
}
//管理员-删除
function picture_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',1);
	});
}
/*------------管理员管理--------------*/
//管理员-角色-添加
function admin_role_add(w,h,title,url){
	layer_show(w,h,title,url);
}
//管理员-角色-编辑
function admin_role_edit(id,w,h,title,url){
	layer_show(w,h,title,url);
}
//管理员-角色-删除
function admin_role_del(obj,id){
	layer.confirm('角色删除须谨慎，确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',1);
	});
}

//管理员-权限-添加
function admin_permission_add(){
	
}
//管理员-权限-编辑
function admin_permission_edit(id,w,h,title,url){
	layer_show(w,h,title,url);
}

//管理员-权限-删除
function admin_permission_del(obj,id){
	layer.confirm('角色删除须谨慎，确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',1);
	});
}

//管理员-编辑-保存
function admin_edit_save(obj,id){
	var i = parent.layer.getFrameIndex();
	parent.layer.close(i);
}
//管理员-删除
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',1);
	});
}
//管理员-停用
function admin_stop(obj,id){
	$(obj).parents("tr").find(".admin-manage").prepend('<a style="text-decoration:none" onClick="admin_start(this,\'10001\')" href="javascript:;" title="启用"><i class="icon-hand-up"></i></a>');
	$(obj).parents("tr").find(".admin-status").html('<span class="label">已停用</span>');
	$(obj).remove();
}
//管理员-启用
function admin_start(obj,id){
	$(obj).parents("tr").find(".admin-manage").prepend('<a style="text-decoration:none" onClick="admin_stop(this,\'10001\')" href="javascript:;" title="停用"><i class="icon-hand-down"></i></a>');
	$(obj).parents("tr").find(".admin-status").html('<span class="label label-success">已启用</span>');
	$(obj).remove();
}
/*------------系统管理--------------*/
//系统管理-日志-删除
function system_log_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',1);
	});
}
/*=======================================================*/
$(function(){
	//菜单处于当前状态
    var webSite ="";
    var loc=location.href;
    var url = loc.replace(webSite,"");
    $(".menu_dropdown ul li").each(function(){
		var current = $(this).find("a");
		$(this).removeClass("current");
		if(url == $(current[0]).attr("href")){
			$(this).addClass("current");
		}
	});
	
	$("#nav-toggle").click(function(){
		$(".Hui-aside").slideToggle();	
	});	
	$(".menu_dropdown dd li a").click(function(){
		if($(window).width()<768){
			$(".Hui-aside").slideToggle();
		}
	});
	
	$(".dislpayArrow a").click(function(){
		if($(".Hui-aside").is(":hidden")){
			$(".Hui-aside").show();$(this).removeClass("open");
			$(".Hui-article,.dislpayArrow").css({"left":"200px"});
		}else{
			$(this).addClass("open");
			$(".Hui-aside").hide();
			$(".Hui-article,.dislpayArrow").css({"left":"0"});
		}
	});

	//选项卡导航
	var topWindow=$(window.parent.document);
	$(".Hui-aside .menu_dropdown a").click(function(){
		var bStop=false;
		var bStopIndex=0;
		var _href=$(this).attr('_href');
		var _titleName=$(this).html();
		var show_navLi=topWindow.find("#min_title_list li");
		show_navLi.each(function() {
			if($(this).find('span').attr("data-href")==_href)
			{
				bStop=true;
				bStopIndex=show_navLi.index($(this));
				return false;	
			}
		});
		if(!bStop){
			creatIframe(_href,_titleName);
			min_titleList();	
		}
		else{
			show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
			var iframe_box=topWindow.find("#iframe_box");
			iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();	
		}
	});
	min_titleList()
	function min_titleList(){
		var show_nav=topWindow.find("#min_title_list");
		var aLi=show_nav.find("li");
	};
	function creatIframe(href,titleName){
		var show_nav=topWindow.find('#min_title_list');
		show_nav.find('li').removeClass("active")
		var iframe_box=topWindow.find('#iframe_box');
		show_nav.append('<li class="active"><span data-href="'+href+'">'+titleName+'</span><i></i><em></em></li>');
		tabNavallwidth();
		var iframeBox=iframe_box.find('.show_iframe')
		iframeBox.hide();
		iframe_box.append('<div class="show_iframe"><div class="loading"></div><iframe frameborder="0" src="'+href+'"></iframe></div>');
		var showBox=iframe_box.find('.show_iframe:visible')
		showBox.find('iframe').hide().load(function(){
			showBox.find('.loading').hide();	
			$(this).show()
		});
	}

	var num=0;
	var oUl=$("#min_title_list");
	var hide_nav=$("#Hui-tabNav");
	$(document).on("click","#min_title_list li",function(){
		var bStopIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		$("#min_title_list li").removeClass("active").eq(bStopIndex).addClass("active");
		iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();		
	});
	$(document).on("click","#min_title_list li i",function(){
		var aCloseIndex=$(this).parents("li").index();
		$(this).parent().remove();
		$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();	
		num==0?num=0:num--;
		tabNavallwidth();
	});
	$(document).on("dblclick","#min_title_list li",function(){
		var aCloseIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		if(aCloseIndex>0){
			$(this).remove();
			$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();	
			num==0?num=0:num--;
			$("#min_title_list li").removeClass("active").eq(aCloseIndex-1).addClass("active");
			iframe_box.find(".show_iframe").hide().eq(aCloseIndex-1).show();
			tabNavallwidth();
		}else{
			return false;
		}
	});
	tabNavallwidth();
	
	$('#js-tabNav-next').click(function(){
		num==oUl.find('li').length-1?num=oUl.find('li').length-1:num++;
		toNavPos()
	});
	$('#js-tabNav-prev').click(function(){
		num==0?num=0:num--;
		toNavPos();
	});
	
	function toNavPos(){
		oUl.stop().animate({'left':-num*100},100)
	}
	jQuery("#Hui-tabNav").slide({mainCell:".Hui-tabNav-wp #min_title_list li",prevCell:"#js-tabNav-prev",nextCell:"#js-tabNav-next",autoPage:false,effect:"leftLoop",vis:10,pnLoop:false,trigger:"click"});
 
}); 