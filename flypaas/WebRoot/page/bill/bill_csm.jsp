<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u" uri="/flypaas-tags" %>
<span class="csm_total">合计总额：<span class="green1">￥${empty page.params ? 0 :page.params.total}</span>元</span>
 <table id="bill_csm_div" cellpadding="0" cellspacing="0" border="0" width="100%">
 	<tbody>
<s:if test="type == 'client'">
            <tr>
              <th>类别</th>
              <th>时间</th>
              <th>client账号</th>
              <th>状态</th>
              <th>数量</th>
              <th>费用</th>
            </tr>
            <s:if test="page.list!=null&&page.list.size()>0">
            <s:iterator value="page.list" var="t">
	            <tr>
	              <td>client账号</td>
	              <td><s:date name="#t.start_time" format="yyyy-MM-dd HH:mm:ss" />
<%-- 	              <fmt:formatDate value="${t.start_time}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
	              </td>
	              <td>${t.client_number}</td>
	              <td>${1 == t.status?'正常':'关闭'}</td>
	              <td>1 个</td> 
	              <td>￥ 0.0000</td>
	            </tr>
            </s:iterator>
            </s:if>
            <s:else>
            	 <tr>
	              <td>暂无数据！</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
            </s:else>
          </tbody>
        </table>
</s:if>
<s:elseif test="type == 'voicecode'">
          <tr>
           <th>类别</th>
           <th>起始时间</th>
           <th>主叫/被叫</th>
           <th>状态</th>
           <th>数量</th>
           <th>费用</th>
          </tr>
           <s:if test="page.list!=null&&page.list.size()>0">
            <s:iterator value="page.list" var="t">
	            <tr>
	              <td>语音验证码</td>
	              <td><s:date name="#t.start_time" format="yyyy-MM-dd HH:mm:ss" /></td>
	              <td>${empty t.from_number ||'null'== t.from_number?' - ':t.from_number}/${t.to_number}</td>
	              <td>${1==t.status?'成功':0==t.status?'失败':'未接听'}</td>
	              <td>1 个</td>
	              <td>￥ ${t.fee_fmt}</td>
	            </tr>
            </s:iterator>
            </s:if>
             <s:else>
            	 <tr>
	              <td>暂无数据！</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
            </s:else>
        </tbody>
      </table>
</s:elseif>
<s:elseif test="type == 'voice'">
          <tr>
            <th>类别</th>
            <th>起止时间</th>
            <th>主叫</th>
            <th>被叫</th>
            <th>状态</th>
            <th>持续时间</th>
            <th>费用</th>
          </tr>
    <s:if test="page.list!=null&&page.list.size()>0">
            <s:iterator value="page.list" var="t">
	            <tr>
	              <td>
	              	<s:if test="1==#t.call_type||2==#t.call_type">直拨</s:if>
	              	<s:elseif test="3==#t.call_type||4==#t.call_type">回拨</s:elseif>
	              	<s:elseif test="5==#t.call_type">互联网语音</s:elseif>
	              	<s:elseif test="6==#t.call_type">录音</s:elseif>
	              	<s:elseif test="7==#t.call_type||10==#t.call_type">国际漫游</s:elseif>
	              	<s:elseif test="8==#t.call_type||9==#t.call_type">语音群聊</s:elseif>
	              	<s:elseif test="11==#t.call_type">密号通话</s:elseif>
	              </td>
	              <td><s:date name="#t.start_time" format="yyyy-MM-dd HH:mm:ss" /> -
	              <s:date name="#t.end_time" format="yyyy-MM-dd HH:mm:ss" /></td>
	              <td>${t.from_number}</td>
	              <td>
	              	<s:set name="_to_number" value="#t.to_number" />
	              	<%
	              		Object _to_number = request.getAttribute("_to_number");
	              		if(null != _to_number){
	              			String s = (String)_to_number;
	              			if(s.startsWith("0083")||s.startsWith("0086")||s.startsWith("0087")){
	              					s = s.substring(4);
	              				if(null != s && s.startsWith("0")){
	              					s = s.substring(1);
	              				}
	              				_to_number = s;
	              			}
	              		}
	              		out.println(_to_number);
	              	%>
	              </td>
	              <td>${t.call_time > 0 ?'成功':'失败'}</td>
	              <td><u:secondFmt value="${t.call_time}"/></td>
	              <td>￥ ${t.fee_fmt}</td>
	            </tr>
            </s:iterator>
            </s:if>
             <s:else>
            	 <tr>
	              <td>暂无数据！</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
            </s:else>
        </tbody>
      </table>
</s:elseif>

<s:elseif test="type == 'im'">
          <tr>
                  <th>类别</th>
                  <th>起始时间</th>
                  <th>发送方/接收方</th>
                  <th>状态</th>
                  <th>数量</th>
                  <th>费用</th>
          </tr>
           <s:if test="page.list!=null&&page.list.size()>0">
            <s:iterator value="page.list" var="t">
	            <tr>
	              <td>即时消息</td>
	              <td><s:date name="#t.start_time" format="yyyy-MM-dd HH:mm:ss" /></td>
	              <td>${t.from_number}/${t.to_number}</td>
	              <td>${'1' == t.status?'成功':'失败'}</td>
	              <td>1条</td>
	              <td>￥ ${t.fee_fmt}</td>
	            </tr>
            </s:iterator>
            </s:if>
            <s:else>
            	 <tr>
	              <td>暂无数据！</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
            </s:else>
        </tbody>
      </table>
</s:elseif>
<s:elseif test="type == 'sms'">
          <tr>
               <th>类别</th>
               <th>起始时间</th>
               <th>端口号/被叫</th>
               <th>状态</th>
               <th>数量</th>
               <th>费用</th>
          </tr>
            <s:if test="page.list!=null&&page.list.size()>0">
            <s:iterator value="page.list" var="t">
	            <tr>
	              <td>短信</td>
	              <td><s:date name="#t.start_time" format="yyyy-MM-dd HH:mm:ss" /> </td>
	              <td> - / ${t.to_number}</td>
	              <td>${1 == t.status?'成功':'失败'}</td>
	              <td>1条</td>
	              <td>￥ ${t.fee_fmt}</td>
	            </tr>
            </s:iterator>
            </s:if>
            <s:else>
            	 <tr>
	              <td>暂无数据！</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
            </s:else>
        </tbody>
      </table>
</s:elseif>

<s:elseif test="type == 'mid'">
          <tr>
               <th>验证类别</th>
               <th>起始时间</th>
               <th>验证号码</th>
               <th>状态</th>
               <th>数量</th>
               <th>费用</th>
          </tr>
            <s:if test="page.list!=null&&page.list.size()>0">
            <s:iterator value="page.list" var="t">
	            <tr>
	              <td>${v_type}</td>
	              <td><s:date name="#t.start_time" format="yyyy-MM-dd HH:mm:ss" /></td>
	              <td>${verify_number}</td>
	              <td>${1==t.status?'成功':0==t.status?'失败':'未接听'}</td>
	              <td>${num}</td>
	              <td>￥ ${t.fee_fmt}</td>
	            </tr>
            </s:iterator>
            </s:if>
            <s:else>
            	 <tr>
	              <td>暂无数据！</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
            </s:else>
        </tbody>
      </table>
</s:elseif>

<s:elseif test="type == 'voicenotify'">
          <tr>
            <th>类别</th>
            <th>开始时间</th>
            <th>被叫</th>
            <th>状态</th>
            <th>持续时间</th>
            <th>费用</th>
          </tr>
    <s:if test="page.list!=null&&page.list.size()>0">
            <s:iterator value="page.list" var="t">
	            <tr>
	              <td>语音通知</td>
	              <td><s:date name="#t.start_time" format="yyyy-MM-dd HH:mm:ss" /></td>
	              <td>${t.to_number}</td>
	              <td>${t.call_time > 0 ?'成功':'失败'}</td>
	              <td><u:secondFmt value="${t.call_time}"/></td>
	              <td>￥ ${t.fee_fmt}</td>
	            </tr>
            </s:iterator>
            </s:if>
             <s:else>
            	 <tr>
	              <td>暂无数据！</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
            </s:else>
        </tbody>
      </table>
</s:elseif>

<s:elseif test="type == 'gjmy'">
          <tr>
            <th>类别</th>
            <th>client账号</th>
            <th>手机号码</th>
            <th>前转号码</th>
            <th>开通时间</th>
            <th>扣费时间</th>
            <th>费用</th>
          </tr>
    <s:if test="page.list!=null&&page.list.size()>0">
            <s:iterator value="page.list" var="t">
	            <tr>
	              <td>${t.fee_type > 0 ?'开通扣费':'定时扣费'}</td>
	              <td>${t.client_number}</td>
	              <td>${t.phone}</td>
	              <td>${t.forward_phone }</td>
	              <td><s:date name="#t.start_time" format="yyyy-MM-dd HH:mm:ss" /></td>
	              <td><s:date name="#t.fee_time" format="yyyy-MM-dd HH:mm:ss" /></td>
	              <td>${t.fee_fmt }</td>
	            </tr>
            </s:iterator>
            </s:if>
             <s:else>
            	 <tr>
	              <td>暂无数据！</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	              <td>&nbsp;</td>
	            </tr>
            </s:else>
        </tbody>
      </table>
</s:elseif>
 <u:page page="${page}" formId="queryForm" ajax="1" divId="bills_tab_div" dataDivId="bill_csm_div"></u:page>