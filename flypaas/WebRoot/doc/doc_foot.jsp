<%@page import="com.flypaas.utils.SysConfig"%>
<%
	String pathDf = SysConfig.getInstance().getProperty("host");
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<!--公共底部bottom bof-->
<div class="bottom">
  <div class="bottom_wrapper">
    <dl class="about">
      <dt>新手指南</dt>
      <dd><a href="<%=pathDf%>/doc/doc_guide5-1.jsp" target="_blank">使用指南</a></dd>
      <dd><a href="<%=pathDf%>/doc/doc_guide6-1.jsp" target="_blank">创建流程</a></dd>
      <dd><a href="<%=pathDf%>/doc/doc_guide3-1.jsp" target="_blank">审核规范</a></dd>
	  <dd><a href="<%=pathDf%>/doc/doc_guide7-1.jsp" target="_blank">充值流程</a></dd>
      <dd><a href="<%=pathDf%>/product_service/freetrial" target="_blank">免费体验</a></dd>
    </dl>
    <dl class="service">
      <dt>开发者</dt>
      <dd><a href="<%=pathDf%>/document" target="_blank">接口文档</a></dd>
      <dd><a href="<%=pathDf%>/product_service/download" target="_blank">下载中心</a></dd>
      <dd><a href="<%=pathDf%>/doc/doc_faq1-1.jsp" target="_blank">常见问题</a></dd>
      <dd><a href="<%=pathDf%>/price" target="_blank">价格</a></dd>
    </dl>
    <dl class="link">
      <dt>公司</dt>
      <dd><a href="<%=pathDf%>/about/companyIntro" target="_blank">关于我们</a></dd>
      <dd><a href="<%=pathDf%>/about/service" target="_blank">我们的服务</a></dd>
<%-- 	  <dd><a href="<%=pathDf%>/about/items" target="_blank">服务条款</a></dd> --%>
      <dd><a href="<%=pathDf%>/about/cooperation" target="_blank">联系我们</a></dd>
	  <dd><a href="<%=pathDf%>/about/report" target="_blank">媒体报道</a></dd>
	  <dd><a href="<%=pathDf%>/about/news" target="_blank">新闻中心</a></dd>
    </dl>
	<!--<dl class="solution">
      <dt>解决方案</dt>
      <dd><a href="http://caller.flypaas.com/" target="_blank"><img src="<%=pathDf%>/doc/images/vcode.png" /></a></dd>
      <dt>友情链接</dt>
      <dd><a style="color:#999; font-size:12px;" target="_blank" href="http://apistore.baidu.com/">百度 API Store</a></dd>
      <dd><a style="color:#999; font-size:12px;" target="_blank" href="http://www.cloudwise.com/">云智慧</a></dd>
      <dd><a href="http://oneapm.com/" target="_blank" style="color:#999; font-size:12px;">OneAPM</a></dd>
      <dd><a href="http://www.sequoiadb.com/" target="_blank" style="color:#999; font-size:12px;">巨杉数据库</a></dd>
      <dd><a href="http://www.pgyer.com/" target="_blank" style="color:#999; font-size:12px;">蒲公英</a></dd>
    </dl>-->
    <dl class="share">
      <dt>关注快传</dt>
	  <dd><a href="http://weibo.com/u/5121723872" target="_blank" class="sina">快传微博</a><span class="wechat">快传微信</span></dd>
      <dd><img src="<%=pathDf%>/doc/images/sina_code.png" width="100" class="sinacode_img" /><img src="<%=pathDf%>/doc/images/weixin_code.png" width="100" class="weixincode_img" /></dd>
    </dl>
  </div>
</div>

<script type="text/javascript">
	$(function(){
		$(".sina").hover(
				function(){
					$("#ig1").show();
				},
				function(){
					$("#ig1").hide();
				}		
		);
		$(".wechat").hover(
				function(){
					$("#ig2").show();
				},
				function(){
					$("#ig2").hide();
				}		
		);
		
	});
</script>
<!--公共底部bottom eof--> 