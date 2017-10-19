<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>生成渠道链接</title>
</head>

<body>
   <div class="main_ctn">
	<h1><a href="javascript:;" class="back" onclick="back()">返 回</a>渠道管理/生成渠道链接</h1>
       <div class="admin_material admin_form channel_form">
          <form method="post" id="mainForm">
             <p>
              <label>渠道ID</label>
              <span>${param.id}</span>
            </p>
            <p>
              <label>原始地址</label>
              <textarea id="url" name="url">${data.url}</textarea>
            </p>
            <p>
               <label>渠道地址</label>
               <span id="url_des">${data.url_des}</span>
            </p>
            <hr class="hr" />
            <p class="btn">
              <input type="button" value="生成" class="org_btn" onclick="created()"/>
              <input type="button" value="取 消" class="grey_btn" onclick="back()"/>
            </p>
          </form>
        </div>
      </div>

<script type="text/javascript">
	function created(){
		var url = $("#url").val();
		$("#url_des").text("");
		if(null == url ||url==""){
			return ;
		}
		$.post("${ctx}/channel/createdUrl",{id:'${param.id}',url:url},function(data){
			$("#url_des").text(data.url_des);
		});
	}
</script>
</body>
</html>