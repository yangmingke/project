<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——资费配置</title>
	<%@include file="/page/resource.jsp"%>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/highcharts.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/modules/exporting.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
</head>
<body id="03-1">
	<!--头部header bof-->
	<%@include file="/page/head.jsp" %>
	<!--头部header eof-->

	<!--主体content bof-->
	<div class="content">

		<!--侧边side bof-->
		<%@include file="/page/left.jsp" %>
		<!--侧边side bof-->
		<input type="hidden" value="<%=path%>" id="path_fo_js" />
		<!--侧边side bof-->

		<!--右侧main bof-->
		<div class="main">
		    <div class="breadcrumbs">
				<ul>
					<li><a href="javascript:;">财务管理</a></li>
					<li class="active"><a href="javascript:;">财务总览</a></li>
				</ul>
			</div>
		
			<div class="main_tab_tit">
				<ul>
					<li onclick="location.href='<%=path%>/finance/index'">账户信息</li>
					<li class="active">资费配置</li>
					<li onclick="location.href='<%=path%>/app/EventBalance'">红包账户</li>
				</ul>
			</div>
		    
		    <!--说明state_box bof-->
		    <div class="state_box">
		      <p>*  第一个应用上线时计算套餐按照天计算；首个应用上线和最后一个应用下线， 分别进行套餐收费和中止收费。</p>
		      <p>*  最后一个应用下线时已经扣除套餐费不返还，下月起不扣除套餐费用。</p>
		      <p>*  开发者可以根据应用的实际消费情况选择适合的套餐，以便节省费用。</p>
		      <p>*  变更资费套餐时，账户需要有足够的余额，如果余额不足，无法完成变更资费动作。</p>
		    </div>
		    <!--说明state_box eof-->
		    
		      <div class="table_box">
		        <div class="table_relate">
		          <ul>
		            <li class="li1 fee_unmodify">
		              <dl>
		                <dt>当前资费类型：</dt>
		                <dd><span class="green1">${pck.packageName}</span></dd>
		              </dl>
		              <dl class="relate_link"><a href="javascript:;" class="modify">修改资费类型</a></dl>
		            </li>
		            <li class="li1 fee_modify" style="display:none;">
		            	<form method="post" action="<%=path%>/user/modifyPck" id="modifyPckForm">
			              <dl>
			                <dt>当前资费类型：</dt>
			                <dd>
			                  <div class="select">
			                    <span>${pck.packageName}<i>&nbsp;</i></span>
			                    <ul>
			                      <s:if test="BasePckList!=null">
			                      	<s:iterator value="BasePckList" var="pk">
				                    	<li <s:if test="#pk.packageId==pck.packageId">class="selected"</s:if> onclick="modifyPck('${pk.packageId}')">${pk.packageName }</li>
			                      	</s:iterator>
			                      </s:if>
			                    </ul>
			                  </div>
			                </dd>
			              </dl>
			              <dl class="relate_link">
								<input type="hidden" id="pckId" name="pckId"/>
				              <a href="javascript:;" class="save" id="pckSubmit">保存</a>
				              <a href="javascript:;" class="cancel">取消</a>
			              </dl>
		            	</form>
		            </li>
		            <li class="li2">
		              <dl>
		                <dt>最低消费：</dt>
		                <dd>
			                <span class="green1">
			                	<s:if test="pck.isLowestCharge==null || pck.isLowestCharge==0">
		                    		0
		                    	</s:if>
		                    	<s:else>
		                    		${pck.lowestCharge}
		                    	</s:else>
							</span>元/月
						</dd>
		              </dl>
		              <dl>
		                <dt>有效期：</dt>
		                <dd>
			                <span class="green1">
			                	<s:if test="pck.status==0">
			                    	失效
			                    </s:if>
			                    <s:else>
			                    	正常
		                    	</s:else>
							</span>
						</dd>
		              </dl>
		            </li>
		          </ul>
		        </div>
		        
		        
            <!-- 当前套餐资费项 -->  
           	  <table cellpadding="0" cellspacing="0" border="0" width="100%">
              <tbody>
                <tr>
                  <th>业务类型</th>
                  <th>计费方式</th>
                  <th>资费</th>
                </tr>
	            <s:if test="pck!=null && pck.feeItemList!=null">
            	<s:iterator value="pck.feeItemList" var="c">
				                <tr>
				                  <td>
				                  ${c.feeName}
				                  <s:if test="#c.isShowNbr==1">
				                  	(单显官方)
				                  </s:if>
				                  <s:if test="#c.isShowNbr==2">
				                  	(单显手机号)
				                  </s:if>
				                  </td>
				                  <td>
				                  	<s:if test="#c.feeType==1">
				                  	一次性收费
				                  	</s:if>
				                  	<s:elseif test="#c.feeType==2">
				                  	租用
				                  	</s:elseif>
				                  	<s:elseif test="#c.feeType==3">
				                  	时长
				                  	</s:elseif>
				                  	<s:elseif test="#c.feeType==4">
				                  	数量
				                  	</s:elseif>
				                  	<s:elseif test="#c.feeType==5">
				                  	许可费
				                  	</s:elseif>
				                  	<s:elseif test="#c.feeType==6">
				                  	租用
				                  	</s:elseif>
				                  </td>
				                  <td>
				                  	<s:if test="#c.interFee==2">
				                  	<a href="<%=path %>/user/feeRate" class="blue">查询费率</a>
				                  	</s:if>
				                  	<s:else>
				                  		<s:if test="#c.feeType==1">
					                  	${c.fee }元
					                  	</s:if>
					                  	<s:elseif test="#c.feeType==2">
					                  	${c.fee }元/月
					                  	</s:elseif>
					                  	<s:elseif test="#c.feeType==3">
					                  	${c.fee }元/分
					                  	</s:elseif>
					                  	<s:elseif test="#c.feeType==4">
					                  	${c.fee }元/条
					                  	</s:elseif>
					                  	<s:elseif test="#c.feeType==5">
					                  	${c.fee }元
					                  	</s:elseif>
					                  	<s:elseif test="#c.feeType==6">
					                  	${c.fee }元/日
					                  	</s:elseif>
				                  	</s:else>
				                  </td>
				                </tr>
            			</s:iterator>
			            </s:if>
			            <s:else>
	           				<%
	           					Map map = (Map)request.getAttribute("feeConfigMap");
	           					out.print(map.get("content"));
	           				%>
			            </s:else>
              </tbody>
            </table>
		   </div>
		
		  </div>  
		<!--右侧main bof-->

	</div>
	<!--主体content eof-->
	
	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->

<script type="text/javascript">
$(function(){
	$(".table_box table tr:even").addClass("even");//表格变色
	
	//资费档修改显示
	$(".relate_link .modify").click(function(){
		$(".fee_modify").show();
		$(".fee_unmodify").hide();
	});
	
	//资费档修改隐藏
	$(".relate_link .cancel").click(function(){
		$(".fee_modify").hide();
		$(".fee_unmodify").show();
	});
	
	$("#pckSubmit").click(function(){
		var pckId = $("#pckId").val();
		var currentPckId = "${pck.packageId}";
		if(pckId==""||pckId==currentPckId){
			$(".fee_modify").hide();
			$(".fee_unmodify").show();
		}else{
			$("#modifyPckForm").submit();
		}
	});
});

function modifyPck(id){
	 $("#pckId").val(id);
}
</script>
</body>
</html>