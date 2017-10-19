
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.admin.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/font/font-awesome.min.css"/>
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>首页</title>
</head>
<body>
<div class="pd-20" style="padding-top:20px;">
  <p class="f-20 text-success">欢迎使用快传技术有限公司资源方开放平台 V1.0</p>
  <table class="table table-border table-bordered table-bg">
    <thead>
      <tr>
        <th colspan="7" scope="col">消息管理<a href="javascript:;" onclick="del()" class="btn btn-danger radius" style="float:right;margin-right: 3%;"><i class="icon-trash"></i> 批量删除</a></th>
      </tr>
      <tr class="text-c">
      	<th style="width: 5%"><input type='checkbox' name='checkall' value=''></th>
      	<th style="width: 5%">序号</th>
        <th style="width: 10%">消息类型</th>
        <th style="width: 40%">标题</th>
        <th style="width: 20%">发送时间</th>
        <th style="width: 10%">状态</th>
        <th style="width: 10%">操作</th>
      </tr>
    </thead>
    <tbody id="dataTable">
    <%-- <% int i = 0; %>
    <c:forEach var ="msgList" items="${msgList}">
      <tr class="text-c">
      	<td><%=i++ %></td>
        <td>${msgList.msgType}</td>
        <td>${msgList.msgTitle}</td>
        <td><fmt:formatDate value="${msgList.createDate}" pattern="yyyy-MM-dd" /></td>
        <td>${msgList.hasread}</td>
      </tr>
    </c:forEach> --%>
    
    
    </tbody>
  </table>
</div>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script> 
<script type="text/javascript">
	/* var t2 = window.setTimeout("queryMsg()",60000);//使用字符串执行方法
	function queryMsg(){
		location.href="/messageController/queryAllMsg.action";
	} */
	
	$(function(){
		$.post("/messageController/queryAllMsg.action",function(data1){
			var data = eval("("+data1+")");
			var array = eval(data);  
		    var len = array.length;
		    var hasread = "";
		    for(var i=0 ;i<len; i++){
		    	if(array[i].msgType==1){
		    		array[i].msgType='公告';
		    	}
		    	if(array[i].msgType==2){
		    		array[i].msgType='资费变更';
		    	}
		    	if(array[i].msgType==3){
		    		array[i].msgType='充值确认';
		    	}
		    	if(array[i].msgType==4){
		    		array[i].msgType='余额提醒';
		    	}
		    	if(array[i].msgType==5){
		    		array[i].msgType='系统消息';
		    	}
		    	if(array[i].msgType==6){
		    		array[i].msgType='管理员指定消息';
		    	}
		    	if(array[i].hasread==0){
		    		hasread='未读';
		    	}
		    	if(array[i].hasread==1){
		    		hasread="<font color='gray'>已读</font>";
		    	}
		    	$("#dataTable").append("<tr class='text-c'>"+
		    							"<td><input type='checkbox' name='check' value='" + array[i].msgId + "'></td>"+
		    							"<td>"+Number(i+1) + "</td>"+
		    							"<td>"+array[i].msgType + "</td>"+
		    							"<td>"+array[i].msgTitle + "</td>"+
		    							"<td>"+formatDateTime(array[i].createDate) + "</td>"+
		    							"<td>"+hasread +"</td>"+
		    							"<td>"+
		    								"<a href='readMsg.jsp?id="+array[i].msgId+"&title="+array[i].msgTitle+"&date="+array[i].createDate+"&msgDesc="+array[i].msgDesc+"' ><font color='green'>查看</font></a>"+
		    							"</td>"+
		    						"\</tr>");  
		    }   
		})
	});
	
	function del() {
		var msgIds="";
		var selections = $('input[name="check"]:checked');
		if(selections.length < 1){
			layer.alert("请选择您要删除的内容!");
			return false;
		}
		layer.confirm('确认要删除吗？',function(){
			for(var i=0;i<selections.length;i++){
				msgIds +=  selections[i].value + ",";
			}
			msgIds = msgIds.substring(0, msgIds.length-1);
			$.post("/messageController/delMsg",{"msgIds":msgIds},function(data){
				data = eval("("+data+")");
				if(data == 0){
					layer.alert("系统出现错误，删除失败！");
				}else{
					window.location.reload();
				}
			});
    	})
	}

	var formatDateTime = function(date) {
		date = new Date(date);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		m = m < 10 ? ('0' + m) : m;
		var d = date.getDate();
		d = d < 10 ? ('0' + d) : d;
		var h = date.getHours();
		var minute = date.getMinutes();
		minute = minute < 10 ? ('0' + minute) : minute;
		return y + '-' + m + '-' + d + ' ' + h + ':' + minute;
	};

	$("#checkall").click(function() {
		$("input[name='check']").each(function() {
			if (this.checked) {
				this.checked = false;
			} else {
				this.checked = true;
			}
		});
	})

</script>
</body>
</html>