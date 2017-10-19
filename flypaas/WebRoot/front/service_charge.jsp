<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>服务资费</title>
<%@include file="/page/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/page/head.jsp" %>
<!--公共头部header eof--> 



<!--公共导航菜单 bof-->
<%@include file="/page/menu.jsp" %>
<!--公共导航菜单 eof--> 

<!--主体部分content bof-->
<div class="content">
  <div class="content_wrapper">
    <div class="content_box">
      <div class="main_tit">
        <h1>服务资费</h1>
      </div>
      <div class="main_ctn">
        <div class="tab_ctn service_ctn">
          <div class="info">
              <ul class="basic_info">
                <li>
                  <dl>
                    <dt>当前套餐</dt>
                    <dd>${pck.packageName }</dd>
                  </dl>
                  <dl>
                    <dt>当前余额</dt>
                    <dd><span class="money">￥${acctBalance.balance }</span></dd>
                  </dl>
                  <dl class="link">
                    <a href="<%=path%>/pay/newOrder" class="recharge">充 值</a><a href="javascript:void(0)" id="consult" class="consult">联系商务咨询</a>
                  </dl>
                </li>
              </ul>
            <div class="clear"></div>
				<s:if test="pckList!=null">
				 <s:iterator value="pckList" var="p">
				            <div class="service_list">
				                <h5>${p.packageName }</h5>
				 				<s:if test="#p.feeItemList!=null">
				        	 	<s:iterator value="#p.feeItemList" var="i">
						              <dl>
						                <dt>${i.feeName}：</dt>
						                <dd>
						                	<s:if test="#i.interFee==2">
						                  		<a href="<%=path %>/user/feeRate" class="blue">查询费率</a>
						                  	</s:if>
						                  	<s:else>
							                	<s:if test="#i.feeType==1">
							                  	${i.fee }元
							                  	</s:if>
							                  	<s:elseif test="#i.feeType==2">
							                  	${i.fee }元/月
							                  	</s:elseif>
							                  	<s:elseif test="#i.feeType==3">
							                  	${i.fee }元/分
							                  	</s:elseif>
							                  	<s:elseif test="#i.feeType==4">
							                  	${i.fee }元/条
							                  	</s:elseif>
							                  	<s:elseif test="#i.feeType==5">
							                  	${i.fee }元
							                  	</s:elseif>
						                  	</s:else>
						                </dd>
						              </dl>
								</s:iterator>
								</s:if>
								<s:if test="#p.feeItemList.size()%2!=0">
									<dl>
						               <dt>&nbsp;</dt>
						               <dd>
						               &nbsp;
						               </dd>
						             </dl>
								</s:if>
				            </div>
			   </s:iterator>
			</s:if>
          <!--弹层(联系商务) bof-->
          <div class="float_box float_box1 consult_box" id="consult_box" style="display:none;">
            <div class="float_tit">
              <h3>联系商务</h3>
              <span class="close"></span>
            </div>
            <div class="float_ctn">
                <p>本通话使用的回拨通话能力，完全免费，我们将对您的号码严格保密，请放心使用。<br />您也可以电话拨打${cpHotNum}来咨询售前。</p>
                <p><input type="text" id="phone" placeholder="请输入您的手机号（只支持国内手机号）"/></p>
                <p><span id="pspan" style="display:none;color:green"></span></p>
                <p class="btn">
                  <input type="submit" value="免费咨询" class="blue_btn" id="consult"/>
                  <input type="button" value="关 闭" class="cancel_btn grey_btn" />
                </p>
            </div>
          </div>
          <script type="text/javascript">
          $(function(){

            $(".basic_info a.consult").click(function(){
              $("#consult_box").show();
              $(".background_box").show();
            });

            //取消关闭
          	$(".cancel_btn").click(function(){
           	 $("#consult_box").hide();
             $(".background_box").hide();
             $("#pspan").hide();
             $("#pspan").text("");
            });
            
            
            $("#consult").click(function(){
            	var phone = $("#phone").val();
            	if(!verifyMobile(phone)){
            		return;
            	}else{
            		$("#pspan").text("正在接通中.请耐心等待...");
            		$("#pspan").fadeIn();
            		$.ajax({
            			url:"<%=path%>/user/callBack",
            			type:"post",
            			data:"phone="+phone,
            			dataType: "text",
            			success: function (data) {
            				$("#pspan").text("已接通");
            				$("#pspan").fadeIn();
                        },
                        error: function (msg) {
                        	$("#pspan").hide();
                        }
            		});
            	}
            	
            });
          });
          </script>
          <!--弹层(联系商务) eof-->

        </div>
      </div>
    </div>
  </div>
</div>
</div>
</div>

<!--主体部分content eof--> 

<!--公共底部footer bof-->
<%@include file="/front/foot.jsp" %>
<!--公共底部footer bof-->

</body>
</html>
