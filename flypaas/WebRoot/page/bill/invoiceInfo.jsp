<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>发票详情</title>
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
        <h1>开具发票</h1>
      </div>
      <div class="main_ctn">
        <div class="invoice_edit">
            <p>
              <label>开具类型</label><s:if test="invoice.openType==2">企业</s:if><s:else>个人</s:else>
            </p>
            <div class="clear"></div>
            <p><label>开具发票金额</label>${invoice.money} 元</p>
            <p><label>发票抬头信息</label>${invoice.title}</p>
            <p><label>发票类型</label><s:if test="invoice.type==1">增值税专用发票</s:if><s:else>增值税普通发票</s:else></p>
            <s:if test="invoice.type==1">
            <p><label>银行账号</label>${invoice.bankaccount }</p>
            <p><label>开户行</label>${invoice.bankaddr }</p>
            <p><label>纳税人识别号</label>${invoice.identificationnum}</p>
            <p><label>一般纳税人资格认证</label><img id="pic" src="<%=path%>/file/view?FileName=<u:des3 value='${invoice.identificationimg}'/>"  alt="一般纳税人资格认证" /></p>
            </s:if>
           <hr class="hr" />
           <p>
             <label>邮寄地址</label>${addr.provinceName }&nbsp;&nbsp;${addr.cityName }&nbsp;&nbsp;${addr.street }
           </p>
           <div class="clear"></div>
           <p><label>邮政编码</label>${addr.postnum }</p>
           <p><label>联系人</label>${addr.contacts }</p>
           <p><label>联系电话</label>${addr.contactmobile }</p>
           <p class="btn" id="btn">
             <input type="button" value="返回" onclick="history.go(-1)"/>
           </p>
        </div>
      </div>
    </div>
  </div>
</div>


<script type="text/javascript">
	
	$(function(){
		$(".child p").eq(2).show().siblings("p").hide();
		$("#menu_3_4").addClass("active");
	});
</script> 


<!--主体部分content eof--> 

<!--公共底部footer bof-->
<%@include file="/page/foot.jsp"%>
<!--公共底部footer bof--> 

</body>
</html>
