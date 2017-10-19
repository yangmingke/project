<%@ page contentType="text/html; charset=UTF-8"%>
<div class="menu">
    <div class="parent">
      <ul>
        <li class="manage"><a href="<%=path%>/user/account"><img src="<%=path%>/images/appcenter.png">管理中心</a></li>
        <li class="developer"><a  href="<%=path%>/user/userCenter"><img src="<%=path%>/images/developer.png">开发者中心</a>
        </li>
        <li class="app"><a href="<%=path%>/app/appManager"><img src="<%=path%>/images/appcenter.png">应用中心</a>
        </li>
        <li class="statement"><a href="<%=path%>/bill/bill"><img src="<%=path%>/images/statement.png">账务账单</a>
        </li>
      </ul>
    <div class="menu_link">
    	<a href="<%=path%>/app/addPage" title="开发者资料" class="app">应用创建</a>
	    <a href="<%=path%>/user/feeConfig" title="服务资费" class="money">资费配置</a>
	    <a href="<%=path%>/user/userCenter" title="开发者资料" class="doc">开发者信息</a>
<%-- 	    <a href="<%=path%>/user/serviceCharge" title="服务资费" class="money">服务资费</a> --%>
<%-- 	    <a href="<%=path%>/user/correctPwdPage" title="修改密码" class="correct_pwd">修改密码</a> --%>
    </div>
    <div class="child">
	    <p style="display:none;">
		    <a href="<%=path%>/user/userCenter" title="开发者信息" id="menu_1_1">开发者信息</a>
		    <a href="<%=path%>/user/feeConfig" title="资费配置" id="menu_1_2">资费配置</a> 
		    <a href="<%=path%>/user/oAuthDispather" title="认证信息" id="menu_1_3">认证信息</a>
		    <a href="<%=path%>/user/notice" title="消息通知" id="menu_1_4">消息通知</a>
		    <a href="<%=path%>/user/correctPwdPage" title="修改密码" class="last" id="menu_1_5">修改密码</a>
	    </p>
	    <p style="display:none;">
		    <a href="<%=path%>/app/appManager" title="应用管理" id="menu_2_1">应用管理</a>
		    <a href="<%=path%>/app/testDemo" title="测试DEMO" id="menu_2_2">测试DEMO</a>
		    <!-- <a href="<%=path%>/app/client" title="client账号">client账号</a> -->
		    <a href="<%=path%>/app/message" title="短信管理" class="last" id="menu_2_3">短信管理</a>
		    <a href="<%=path%>/app/ring" title="语音管理" class="last" id="menu_2_4">铃声管理</a>
		    <a href="<%=path%>/app/cloudNotice" title="云通知" class="last" id="menu_2_5">语音通知</a>
		    <a href="<%=path%>/app/EventBalance" title="业务余额" class="last" id="menu_2_6">业务余额</a>
	    </p>
	    <p style="display:none;">
		    <a href="<%=path%>/bill/bill" title="消费账单" id="menu_3_1">消费账单</a>
		    <a href="<%=path%>/bill/billMonth" title="月结账单" id="menu_3_2">月结账单</a>
		    <a href="<%=path%>/bill/chargeList" title="充值详单" class="last" id="menu_3_3">充值详单</a>
		    <a href="<%=path%>/bill/invoice" title="充值详单" class="last" id="menu_3_4">开具发票</a>
	    </p>
    </div>
    </div>
    
  </div>
  <div class="clear"></div>
