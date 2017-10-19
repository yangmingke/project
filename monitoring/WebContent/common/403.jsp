<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>403页面</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${ctx}/css/matrix-style.css" />
<style type="text/css">
	body { background: #eee;}
	.error_ex { padding: 100px 0px;}
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
              <h1>403</h1>
              <h3>权限不足，禁止访问</h3>
			  <p>请先登录</p>
              <a href="${ctx}/index" class="btn btn-warning btn-big">返回首页</a> </div>
          </div>
        </div>
      </div>
      <div class="span3">&nbsp;</div>
    </div>
  </div>
</body>
</html>