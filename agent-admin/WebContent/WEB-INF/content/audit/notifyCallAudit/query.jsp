<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- 
语音通知审核
 -->
<html>
<head>
	<title>语音通知审核</title>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.jplayer.min.js"></script>
	<script type="text/javascript">
		$(function(){
			play_fun  = function (url){
				if(url.indexOf('.amr') >-1){
					$("#music").html("");
					$("#music_url").attr("src",url);
				}else{
					$("#music_url").attr("src","");
					$("#music").html("<audio src='"+url+"' autoplay='true'></audio>");
				}
			};
		});
	</script>
</head>
<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/notifyCallAudit/query">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="ID/应用ID/应用名称/归属开发者" class="txt_227" />
            </li>
           <li class="time">
              	<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date,-1" maxToday="-1" />
				<span>至</span>
            	<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date,1" maxToday=""  />
            </li>
            <li>
            	<u:select id="audit_status" value="${param.audit_status}" placeholder="审核状态" dictionaryType="notify_call_audit_status" includeValue=",1,2,3" />
            </li>
              <li>
            	<u:select id="upload_status" value="${param.upload_status}" placeholder="上传状态" dictionaryType="notify_call_upload_status" includeValue=",1,2,3,4" />
            </li>
            <li>
            	<u:select id="send_status" value="${param.send_status}" placeholder="发送状态" dictionaryType="notify_call_send_status" includeValue=",1,2,3,4,5" />
            </li>
            <li>
              <input type="submit" value="查 询" class="search" />
            </li>
          </ul>
        </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
        <table cellpadding="0" cellspacing="0" border="0">
          <tbody>
            <tr>
              <th width="50px">Id</th>
              <th>开发者</th>
              <th>应用ID</th>
              <th>应用名称</th>
              <th>通知类型</th>
              <th>内容显示</th>
              <th>创建时间</th>
              <th>更新时间</th>
              <th>审核状态</th>
              <th>上传状态</th>
              <th>发送状态</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr>
	            	<td>${id}</td>
	            	<td>${email}</td>
	            	<td>${app_sid}</td>
	            	<td>${app_name}</td>
	            	<td><u:ucparams key="${type}" type="notify_call_type"/></td>
	            	<td>
	            	<s:if test="type ==1">${user_file_name }</s:if>
	            	<s:if test="type ==0"><u:truncate length="20" value="${content}"/></s:if>
	            	</td>
	            	<td>${create_date}</td>
	            	<td>${update_date}</td>
	            	<td id="${id}_audit_name"><u:ucparams key="${audit_status}" type="notify_call_audit_status"/></td>
	            	<td><u:ucparams key="${upload_status}" type="notify_call_upload_status"/></td>
	            	<td><u:ucparams key="${send_status}" type="notify_call_send_status"/></td>
	            	<td>
	            		<s:if test="type==1">
							<a href="javascript:;" onclick="play_fun('${ctx}/file/view?path=${RING_BASE}/${file_path}/${file_name}');">播放</a>
	            		</s:if>
	            		<s:if test="audit_status==1">
	            			<u:authority menuId="245">
	            				| <a href="javascript:;" onclick="pass_showBox('${id}','${sid}','${email}','${type}');">通过</a>
	            				| <a href="javascript:;" onclick="unpass_showBox('${id}','${sid}','${email}','${type}');">不通过</a>
	            			</u:authority>
						</s:if>         	
						<s:elseif test="audit_status==3">
							<u:authority menuId="245">
								| <a href="javascript:;" onclick="unpass_showReason(this, '${id}','${sid}','${audit_desc}')" class="reasons">补充原因</a>
								<textarea id="audit_desc_${id}" style="display:none;">${audit_desc}</textarea>
							</u:authority>
	            		</s:elseif>
					</td>
	            </tr>
	        </s:iterator>
          </tbody>
        </table>
		<u:page page="${page}" formId="mainForm" />
      </div>
      <div id="music" style="display: none;"></div>
		<object width="0" height="0" classid="clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B" codebase="http://www.apple.com/qtactivex/qtplugin.cab">
			<param name="src" value=""><param name="controller" value="true">
			<param name="type" value="video/quicktime"><param name="autoplay" value="true">
			<param name="target" value="myself"><param name="bgcolor" value="black">
			<param name="pluginspage" value="http://www.apple.com/quicktime/download/index.html">
			<embed id="music_url" src="" autostart="true" width="1" type="audio/amr"  height="1" ></embed>
		</object>
      
<!--弹层(审核通过)-->
<div class="float_box audit_box" id="audit_box_pass" style="display:none;">
  <div class="float_tit">
    <h3>审核通过</h3>
  </div>
  <div class="float_ctn">
    <form method="post" id="audit_form">
      <p>是否确认此语音通知通过审核</p>
      <input type="hidden" id="pass_audit_id" name="id"/>
      <input type="hidden" id="pass_audit_sid" name="sid"/>
      <input type="hidden" id="pass_audit_email" name="email"/>
      <input type="hidden" id="pass_entity_type" name="entity_type"/>
      <input type="hidden" id="pass_audit_type" name="type" value="1" />
      <span name="msg" class="error" style="display:none;"></span>
      <p class="btn">
        <input type="button" value="确 定" onclick="audit(this,'audit_form','audit_box_pass',1);" />
        <input type="button" value="取 消" onclick="hideBox('audit_box_pass')" class="cancel_btn grey_btn" />
      </p>
    </form>
  </div>
</div>

<!--弹层(审核不通过)-->
<div class="float_box audit_box" id="audit_box_unpaas" style="display:none;">
  <div class="float_tit">
    <h3>审核不通过</h3>
  </div>
  <div class="float_ctn">
  	<form method="post" id="audit_un_form">
      <p>填写应用审核不通过原因：<br />
        <textarea id="audit_desc" name="audit_desc" maxlength="500"></textarea>
      </p>
      <u:select id="no_pass_reason" placeholder="快速填写不通过原因" dictionaryType="appAudit_no_pass_reason" excludeValue="" callback="selectReason"/>
      <div class="clear"></div>
      <span id="msg" class="error" style="display:none;"></span>
      <p class="btn">
	      <input type="hidden" id="unpass_audit_id" name="id"/>
	      <input type="hidden" id="unpass_audit_sid" name="sid"/>
	      <input type="hidden" id="unpass_audit_email" name="email"/>
	      <input type="hidden" id="unpass_entity_type" name="entity_type"/>
	      <input type="hidden" id="unpass_audit_type" name="type" value="0" />
        <input type="button" value="确 定" onclick="audit(this, 'audit_un_form','audit_box_unpaas',0)" />
        <input type="button" value="取 消" onclick="hideBox('audit_box_unpaas')" class="cancel_btn grey_btn" />
      </p>
    </form>
  </div>
</div>

<!--补充原因弹层-->
<div class="float_box audit_box" id="audit_add_reason_box" style="display:none;">
  <div class="float_tit">
    <h3>审核不通过</h3>
  </div>
  <div class="float_ctn">
  	<form method="post" id="audit_un_form_reason">
      <p>填写应用审核不通过原因：<br />
        <textarea id="audit_desc_reason" name="audit_desc" maxlength="500"></textarea>
      </p>
      <u:select id="no_pass_reason_add" placeholder="快速填写不通过原因" dictionaryType="appAudit_no_pass_reason" excludeValue="" callback="selectReason_add"/>
      <div class="clear"></div>
      <span id="msg" class="error" style="display:none;"></span>
      <p class="btn">
      	 <input type="hidden" id="unpass_audit_reason" name="id"/>
      	 <input type="hidden" id="unpass_sid_reason" name="sid"/>
        <input type="button" value="确 定" onclick="saveReason(this, 'audit_un_form_reason','audit_add_reason_box')" />
        <input type="button" value="取 消" onclick="hideBox('audit_add_reason_box')" class="cancel_btn grey_btn" />
      </p>
    </form>
  </div>
</div>

<script type="text/javascript">
	$(function(){
		//表单验证规则
		validate_obj = $("#audit_un_form").validate({
			rules: {
				audit_desc: "required"
			},
			messages: {
				audit_desc: "请输入原因"
			}
		});
		validate_obj2 = $("#audit_un_form_reason").validate({
			rules: {
				audit_desc: "required"
			},
			messages: {
				audit_desc: "请输入原因"
			}
		});
	});

function pass_showBox(id,sid,email,entity_type){
	$("#pass_audit_id").val(id);
	$("#pass_audit_sid").val(sid);
	$("#pass_audit_email").val(email);
	$("#pass_entity_type").val(entity_type);
	showBox('audit_box_pass');
};

function unpass_showBox(id,sid,email,entity_type){
	$("#unpass_audit_id").val(id);
	$("#unpass_audit_sid").val(sid);
	$("#unpass_audit_email").val(email);
	$("#unpass_entity_type").val(entity_type);
	showBox('audit_box_unpaas');
};


//显示原因
function unpass_showReason(a,id,sid,reason){
	$("#unpass_audit_reason").val(id);
	$("#unpass_sid_reason").val(sid);
	$("#audit_desc_reason").val(reason);
	$("#audit_add_reason_box label.error, #audit_add_reason_box span.error").hide();
	showBox("audit_add_reason_box");
}

//快速填写不通过原因
function selectReason(value, text){
	$("#audit_desc").val(text);
}
//快速填写不通过原因
function selectReason_add(value, text){
	$("#audit_desc_reason").val(text);
}

//保存补充原因
function saveReason(btn,formId, boxId){
	$("#msg").hide();
	if(!validate_obj2.form()){
		return;
	}
	var audit_desc = $("#audit_desc_reason").val();
	$(btn).attr("disabled", true);
	var formData = $("#" + formId).serializeArray();
	var params = {};
	$.each(formData,function(i,o){
		params[o.name] = o.value;
	});
	params['audit_desc'] = audit_desc;
	$.ajax({
		type: "post",
		url: "${ctx}/notifyCallAudit/saveReason",
		data:params,
		success: function(data){
			$(btn).attr("disabled", false);
			
			if(data.result==null){
				$("#msg").text("服务器错误，请联系管理员").show();
				return;
			}else if(data.result=="success"){
				setTimeout(function(){
					hideBox(boxId);
					$("#mainForm").submit();
				}, 1000);
			}
			$("#msg").text(data.msg).show();
		}
	});
}

//显示弹层(审核不通过)
function showAuditUnBox(){
	$("#audit_un_box label.error, #audit_un_box span.error").hide();
	showBox("audit_un_box");
}

//快速填写不通过原因
function selectReason(value, text){
	$("#audit_desc").val(text);
}

//审核
function audit(btn, formId,boxId,type){
	var msg = $("#"+boxId+" [name='msg']");
	msg.hide();
	var audit_desc = "";
	if(type==0){
		if(!validate_obj.form()){
			return;
		}
		audit_desc = $("#audit_desc").val();
	}
	var formData = $("#" + formId).serializeArray();
	var params = {};
	$.each(formData,function(i,o){
		params[o.name] = o.value;
	});
	params['audit_desc'] = audit_desc;
	$(btn).attr("disabled", true);
	$.ajax({
		type: "post",
		url: "${ctx}/notifyCallAudit/audit",
		data:params,
		success: function(data){
			$(btn).attr("disabled", false);
			if(data.result==null){
				msg.text("服务器错误，请联系管理员").show();
				return;
			}
			if(data.result=="success"){
				setTimeout(function(){
					hideBox(boxId);
					//$("#"+id+"_audit_name").text(type==0 ? "审核不通过" : "审核通过");
					//$("#div_operate").remove();
					$("#mainForm").submit();
				}, 1000);
			}
			msg.text(data.msg).show();
		}
	});
}

</script>
</body>
</html>