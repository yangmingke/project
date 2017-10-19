<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>文档更新</title>
<%@include file="/page/resource.jsp" %>
</head>
<body>
<%@include file="/page/head.jsp" %>

<!--主体部分content bof-->
<div class="content">
  <div class="content_wrapper"> 
    <div class="reg_box">
      <div class="reg_info reg_success">
      		<form action="/doc/upDoc" name="addDocFrm" id="addDocFrm" method="post" enctype="multipart/form-data">
      			<div id="fileDiv">
			        <input type="file"  class="file_1" id="1" name="file"/><br/>
			        <input type="file"  class="file_1" id="2" name="file"/><br/>
			        <input type="file"  class="file_1" id="3" name="file"/><br/>
			        <input type="file"  class="file_1" id="4" name="file"/><br/>
			        <input type="file"  class="file_1" id="5" name="file"/>
		        </div>
		        <img id="plusImg" src="<%=path %>/images/plus.png" style="width:20px;float:right;cursor:pointer" />
		        <br/>
		        <input type="submit" value="提交" />
	        </form>
      </div>
    </div>
  </div>
</div>
<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%@include file="foot.jsp" %>
<!--公共底部footer bof--> 

<script type="text/javascript">
	$(function(){
		$(".file_1").change(function(){
			var vl = $(this).val();
			$(this).siblings(".file_txt").val(vl);
		});	
		$("#plusImg").click(function(){
			var lastId = $(".file_1:last").attr("id");
			lastId = lastId+1 ;
			var file="<br><input type='file' class='file_1' name='file' id='"+lastId+"'/>";
			$("#fileDiv").append(file);
			$("input[id='"+lastId+"']").wrap("<div class='file'></div>");
			$("input[id='"+lastId+"']").before("<input type='text' class='file_txt' name='file_txt' /><input type='button' value='浏览' class='file_btn' />");
			$("input[id='"+lastId+"']").change(function(){
				var vl = $(this).val();
				$(this).siblings(".file_txt").val(vl);
			});	
		});
	});
</script>

</body>
</html>
