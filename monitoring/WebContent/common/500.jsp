<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>500错误</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${ctx}/css/matrix-style.css" />
<style type="text/css">
	body { background: #eee;}
	.error_ex { padding: 100px 0px;}
	.div_debug td{
			text-align:left;
			line-height:normal;
		}
</style>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
      <div class="span3">&nbsp;</div>
      <div class="span6">
        <div class="widget-box">
          <div class="widget-content">
            <div class="error_ex">
              <h1>500</h1>
              <h3>抱歉，服务器内部错误</h3>
              <p>我们正在努力修复，请稍后重试</p>
              <a href="${ctx}/index" class="btn btn-warning btn-big">返回首页</a> </div>
          </div>
        </div>
      </div>
      <div class="span3">&nbsp;</div>
       <div class="div_debug" style="display:none;">
			<s:debug></s:debug> 
		</div>
    </div>
  </div>
</body>
</html>