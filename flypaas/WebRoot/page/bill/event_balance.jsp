<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>资费配置</title>
<%@include file="/page/resource.jsp"%>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/page/head.jsp"%>
<!--公共头部header eof--> 


<!--公共导航菜单 bof-->
<%@include file="/page/menu.jsp"%>
<!--公共导航菜单 eof--> 


<!--主体部分content bof-->
<div class="content">
  <div class="content_wrapper">
    <div class="content_box">
      <div class="main_tit">
        <h1>业务余额表</h1>
      </div>
      <div class="main_ctn">
        <div class="tab_ctn fee_ctn">
         <!--  <div class="text"> <b>提醒：</b>
            <p>1、第一个应用上线时计算套餐按照天计算；首个应用上线和最后一个应用下线， 分别进行套餐收费和中止收费。</p>
            <p>2、最后一个应用下线时已经扣除套餐费不返还，下月起不扣除套餐费用。</p>
            <p>3、开发者可以根据应用的实际消费情况选择适合的套餐，以便节省费用。</p>
			<p>4、变更资费套餐时，账户需要有足够的余额，如果余额不足，无法完成变更资费动作。</p>
          </div> -->
          <div class="info">
          	  <s:if test="dataList!=null">
          	  	<s:iterator value="dataList" id="data">
          	  		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		              <tbody>
		                <tr>
		                  <th>业务类型</th>
		                  <th>余额</th>
		                  <th>状态</th>
		                  <th>过期时间</th>
		                </tr>
		                <tr>
		                  <td>
		                  	${data.event_name }
		                  </td>
		                  <td>
		                  	${data.balance }
		                  </td>
		                  <td>
		                  	<s:if test="#data.status==1">
		                  	正常
		                  	</s:if>
		                  	<s:else>
		                  	失效
		                  	</s:else>
		                  </td>
		                  <td>
		                  	<s:if test="#data.status==1">
		                  		${data.exp_date }
		                  	</s:if>
		                  </td>
		                </tr>
		              </tbody>
		            </table>
          	  	</s:iterator>
            </s:if>
            <s:else>
            	暂无数据!
            </s:else>
          </div>         
        </div>
      </div>
    </div>
  </div>
</div>

<!--主体部分content eof--> 

<!--公共底部footer bof-->
<%@include file="/page/foot.jsp"%>
<!--公共底部footer bof-->
<script type="text/javascript">
              $(function(){
            	  $("#menu_2_6").addClass("active");
            	  $(".child p").eq(1).show().siblings("p").hide();
              });
              </script>
</body>
</html>
