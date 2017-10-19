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
<title>flypaas开放平台管理中心——月结账单</title>
<%@include file="/page/resource.jsp"%>
</head>
<body id="03-3">
<!--头部header bof-->
<%@include file="/page/head.jsp"%>
<!--头部header eof--> 

<!--主体content bof-->
<div class="content"> 
  
  <!--侧边side bof-->
  <%@include file="/page/left.jsp"%>
  <!--侧边side bof--> 
  
  <!--右侧main bof-->
  <div class="main">
    <div class="breadcrumbs">
      <ul>
        <li><a href="#">财务管理</a></li>
        <li class="active"><a href="#">月结账单</a></li>
      </ul>
    </div>
    <div class="main_tab_tit">
      <ul>
        <li onclick="href2url('${path}/bill/bill')">消费记录</li>
		<li class="active"  onclick="href2url('${path}/bill/billMonth')">月结账单</li>
      </ul>
    </div>
	
	
	 <div class="bill_search">
	        <form id="billMonthFrm" name="billMonthFrm">
		        <ul>
		          <li class="li1">
		          <div class="select_box select_app">
		          	<label>选择年份：</label>
			          <div tabindex="2" class="select">	
						<span>
						${year}
						<i>&nbsp;</i>
						</span>
								<s:if test="yearList!=null" >
									<ul>
				                  	<s:iterator value="yearList" var="y">
						                  <li class="li1" value="${y}" onclick="year(this.value)">${y}</li>
				                  	</s:iterator>
									</ul>
								</s:if>
					</div>
		          </div>
				  <input type="button" value="查询" id="monthBtn"/>
	              <span class="tips">* 显示年度账单，因系统结算每个自然月5号以后提供上个月度账单查询</span>
		          </li>
		      </ul>
		      <input type="hidden" id="year" name="year" value="${year}" />
	       	  <input type="hidden" id="month" name="month" value="${month}" />
	       	  <input type="hidden" id="day" name="day" />
	      </form>
	    </div>							

    <div class="month_consumption">
      <div class="month">
        <ul>
          <li value="1" <s:if test="month==1">class="first active"</s:if><s:else>class="first"</s:else>><a href="javascript:void(0)" >${year}年01月</a></li>
          <li value="2" <s:if test="month==2">class="active"</s:if> ><a href="javascript:void(0)">02月</a></li>
          <li value="3" <s:if test="month==3">class="active"</s:if>><a href="javascript:void(0)">03月</a></li>
          <li value="4" <s:if test="month==4">class="active"</s:if>><a href="javascript:void(0)">04月</a></li>
          <li value="5" <s:if test="month==5">class="active"</s:if>><a href="javascript:void(0)">05月</a></li>
          <li value="6" <s:if test="month==6">class="active"</s:if>><a href="javascript:void(0)">06月</a></li>
          <li value="7" <s:if test="month==7">class="active"</s:if>><a  href="javascript:void(0)">07月</a></li>
          <li value="8" <s:if test="month==8">class="active"</s:if>><a href="javascript:void(0)">08月</a></li>
          <li value="9" <s:if test="month==9">class="active"</s:if>><a href="javascript:void(0)">09月</a></li>
          <li value="10" <s:if test="month==10">class="active"</s:if>><a href="javascript:void(0)">10月</a></li>
          <li value="11" <s:if test="month==11">class="active"</s:if>><a href="javascript:void(0)">11月</a></li>
          <li value="12" <s:if test="month==12">class="active"</s:if>><a href="javascript:void(0)">12月</a></li>
        </ul>
      </div>
      <div class="consumption">
        <div class="total">
          <dl><dt>该月消费总额：</dt><dd><span class="green1">￥${monthCsm}</span>元</dd></dl>
        </div>
			 <s:if test="monthList!=null&&monthList.size()>0">
        	 	<s:iterator value="monthList" id="entry2">
<%--         	 		<s:if test="#entry2.charge_type!=2&&#entry2.charge_type!=5"> --%>
		              <dl>
		                <dt><s:property value="#entry2.event_name"/>：</dt>
		                <dd><span class="green1">
		                	￥<s:property value="#entry2.actual_fee"/>元
		                	</span>
		                </dd>
		              </dl>
<%-- 		            </s:if> --%>
				</s:iterator>
			</s:if>
      </div>
    </div>

    <div class="bill_download">
      <h1>账单下载<span class="green1"> ( 点击数字下载“每日”祥单 )</span></h1>
      <div class="download_month">
        <p>
        <a href="javascript:void(0)">1</a>
        <a href="javascript:void(0)">2</a>
        <a href="javascript:void(0)">3</a>
        <a href="javascript:void(0)">4</a>
        <a href="javascript:void(0)">5</a>
        <a href="javascript:void(0)">6</a>
        <a href="javascript:void(0)">7</a>
        <a href="javascript:void(0)">8</a>
        <a href="javascript:void(0)">9</a>
        <a href="javascript:void(0)">10</a>
        <a href="javascript:void(0)">11</a>
        <a href="javascript:void(0)">12</a>
        <a href="javascript:void(0)">13</a>
        <a href="javascript:void(0)">14</a>
        <a href="javascript:void(0)">15</a>
        <a href="javascript:void(0)">16</a>
        <a href="javascript:void(0)">17</a>
        <a href="javascript:void(0)">18</a>
        <a href="javascript:void(0)">19</a>
        <a href="javascript:void(0)">20</a>
        <a href="javascript:void(0)">21</a>
        <a href="javascript:void(0)">22</a>
        <a href="javascript:void(0)">23</a>
        <a href="javascript:void(0)">24</a>
        <a href="javascript:void(0)">25</a>
        <a href="javascript:void(0)">26</a>
        <a href="javascript:void(0)">27</a>
        <a href="javascript:void(0)">28</a>
        <a href="javascript:void(0)">29</a>
        <a href="javascript:void(0)">30</a>
        <a href="javascript:void(0)">31</a>
        </p>
        <p><span class="tips">* 月结详单，以“每日”生成下载链接</span></p>
      </div>
    </div>
    
  </div>  
  <!--右侧main bof-->   
</div>

<!--主体content eof--> 

<!--底部footer bof-->
<%@include file="/page/foot.jsp" %>
<!--底部footer eof-->

<script type="text/javascript">
	function year(v){
		$("#year").val(v);
	}
	function month(v){
		$("#month").val(v);
		sbmt();
	}
	function sbmt(){
		$("#billMonthFrm").attr("action","<%=path%>/bill/billMonth");
		$("#billMonthFrm").submit();
	}
	function downSbmt(){
		$("#billMonthFrm").attr("action","<%=path%>/download/acctDayZip?_="+ Math.random());
		$("#billMonthFrm").submit();
	}
	function hilg(){
		$(this).addClass("active");
		$(this).siblings("a").removeClass("active");
	}
	function day(v){
		$("#day").val(v);
	}
	$(function(){
		$(".month li").click(function(){
			month(this.value);
		});
		$("#monthBtn").click(function(){
			sbmt();
		});
		$(".download_month p a").click(function(){
			var v = $(this).text();
			hilg();
			day(v);
			downSbmt();
		});
	});

</script>
</body>
</html>