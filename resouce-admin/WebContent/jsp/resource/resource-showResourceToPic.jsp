<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx}/css/pintuer.css">
<link rel="stylesheet" href="${ctx}/css/admin.css">
<script src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/pintuer.js"></script>
</head>
<style>
	table{
		width: 100%;
		height: 730px;
		border-color: #FCFCFC;
	}
	.left{
		width: 200px;
		border-right-color: #DDDDDD;
	}
</style>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 资源节点实时状态图</strong></div>
  <div class="padding border-bottom">
      <ul class="search">
        <li>
          <select id="status" class="input" style="width:150px; line-height:17px;">
            <option value="">状态：所有</option>
            <option value="0">正常工作</option>
            <option value="1">审核中...</option>
            <option value="2">不通过</option>
            <option value="3">故障</option>
            <option value="4">暂停</option>
            <option value="5">下线</option>
          </select>
        </li>
        <li>
	        <input type="text" placeholder="输入关键字搜索资源" id="keyword" class="input" style="width:250px; line-height:20px;display:inline-block" />&nbsp;&nbsp;&nbsp;
	        <a href="javascript:void(0)" class="button border-main icon-search" onclick="jumpTo()" > 查询</a>
        </li>
      </ul>
    </div>
    <table border="1" >
		<tr>
			<td class="left" valign="top">
				<a href="##" onclick="showResourceStatusToPic()">显示节点</a>
			</td>
			<td>
				<div id="main" style="width:90%; height:100%; margin: 0 auto"></div>
			</td>
		</tr>	
	</table>
</div>
</body>
<script src="${ctx}/js/resource/echarts.min.js"></script>
<script src="${ctx}/js/resource/resourceStatusToPic.js"></script>
</html>