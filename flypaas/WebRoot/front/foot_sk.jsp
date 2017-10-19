<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!--公共底部bottom bof-->
<div class="bottom">
  <div class="bottom_wrapper">
    <dl class="about">
      <dt>新手指南</dt>
      <dd><a href="http://docs.flypaas.com/doku.php?id=管理中心使用指南" target="_blank">使用指南</a></dd>
      <dd><a href="http://docs.flypaas.com/doku.php?id=新手指引#应用创建流程" target="_blank">创建流程</a></dd>
      <dd><a href="http://docs.flypaas.com/doku.php?id=应用审核规范" target="_blank">审核规范</a></dd>
	  <dd><a href="http://docs.flypaas.com/doku.php?id=新手指引#充值流程" target="_blank">充值流程</a></dd>
      <dd><a href="<%=path%>/product_service/freetrial" target="_blank">免费体验</a></dd>
    </dl>
    <dl class="service">
      <dt>开发者</dt>
      <dd><a href="http://docs.flypaas.com/doku.php" target="_blank">接口文档</a></dd>
      <dd><a href="<%=path%>/product_service/download" target="_blank">下载中心</a></dd>
      <dd><a href="http://docs.flypaas.com/doku.php?id=faqs" target="_blank">常见问题</a></dd>
      <dd><a href="<%=path%>/price" target="_blank">价格</a></dd>
    </dl>
    <dl class="link">
      <dt>公司</dt>
      <dd><a href="<%=path%>/about/companyIntro" target="_blank">关于我们</a></dd>
      <dd><a href="<%=path%>/about/service" target="_blank">我们的服务</a></dd>
      <!--<dd><a href="<%=path%>/about/items" target="_blank">服务条款</a></dd> -->
      <dd><a href="<%=path%>/about/cooperation" target="_blank">联系我们</a></dd>
      <dd><a href="<%=path%>/about/report" target="_blank">媒体报道</a></dd>
      <dd><a href="<%=path%>/about/news" target="_blank">新闻中心</a></dd>
      <!--<dd><a href="<%=path%>/about/partners" target="_blank">合作伙伴</a></dd>-->
      <dd><a href="http://bbs.flypaas.com/forum.php?mod=viewthread&tid=156&extra=page=1" target="_blank">招贤纳士</a></dd>
    </dl>
    <dl class="share">
      <dt>关注快传</dt>
	    <dd><a href="http://weibo.com/u/5121723872" target="_blank" class="sina">快传微博</a><span class="wechat">快传微信</span></dd>
      <dd><img src="<%=path%>/front/images/sina_code.png" width="100" class="sinacode_img" /><img src="<%=path%>/front/images/weixin_code.png" width="100" class="weixincode_img" /></dd>
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