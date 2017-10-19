package com.yzx.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.yzx.rest.models.Account;
import com.yzx.rest.models.AppBill;
import com.yzx.rest.models.Client;
import com.yzx.rest.models.SDK;
import com.yzx.rest.models.SessionInfo;
import com.yzx.rest.resp.ListResp;
import com.yzx.rest.resp.ListRespt;

@Path(value = "/{version}/Accounts/{accountSid}")
public interface AccountService {
	@Produces({"application/json","application/xml"})
	/**
	 * 基础信息鉴权
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @return
	 */
	@POST
	@Path("/authentic")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp authentic(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig);
	
	/**
	 * 主账号查询接口（公共接口）
	 * @param version
	 * @param accountSid
	 * @param Authorization
	 * @param sig
	 * @return 
	 */
	@GET
	@Path("")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ListResp AccountInfo(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String Authorization,
			@QueryParam("sig") String sig);
	
	/**
	 * 子账号占用状态查询
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
	@Path("/userState")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp userState(@PathParam("version") String version,@PathParam("accountSid") String accountSid,String userInfoKey);
	
	/**
	 * 子账号创建接口（公共接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
	@Path("/Clients")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp createClient(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,Client client);
	
	/**
	 * 获取apList
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
	@Path("/session/getaplist")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp getAplist(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,SessionInfo sessionInfo);
	
	
	/**
	 * 路由申请接口（公共接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
	@Path("/session/create")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp createSession(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,SessionInfo route);
	
	/**
	 * 路由释放接口（公共接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
	@Path("/session/release")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp releaseSession(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,SessionInfo route);
	
	
	/**
	 * 子账号删除接口（公共接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
	@Path("/dropClient")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp closeClient(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,Client client);
	
	/**
	 * 子账号分页查询接口（公共接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
    @Path("/clientList")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp findClients(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,Client client);
	
	/**
	 * 根据clientNumber查询子账号（公共接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param clientNumber
	 * @param appId
	 * @return
	 */
	@GET
    @Path("/Clients")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp findClientByName(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,@QueryParam("clientNumber") String clientNumber
			,@QueryParam("appId") String appId);
	
	/**
	 * 根据手机号查询子账号接口（公共接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param mobile
	 * @param appId
	 * @return
	 */
	@GET
    @Path("/ClientsByMobile")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp findClientByMobile(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,@QueryParam("mobile") String mobile
			,@QueryParam("appId") String appId);
	
	/**
	 * 子账号充值(公共接口)
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
	@Path("/chargeClient")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp chargeClient(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,Client client);
	
	/**
	 * 换绑（定制接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
	@Path("/Clients/band")
	public ListResp bandClients(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,Client client);
	
	/**
	 * 解绑（定制接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
	@Path("/Clients/delBand")
	public ListResp DelBand(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,Client client);
	
	/**
	 * 修改密码
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param appId
	 * @param callID
	 * @return
	 */
	@POST
    @Path("/auditPwd")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp auditPwd(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,Client client);
	
	/**
	 * 拉取测试client（DEMO定制接口）
	 * @param version
	 * @param auth
	 * @param sig
	 * @param account
	 * @return
	 */
	@POST
	@Path("/login")
	public ListRespt login(@PathParam("version") String version,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,Account account);
	
	/**
	 * 拉取新DEMO测试client（新DEMO定制接口）
	 * @param version
	 * @param auth
	 * @param sig
	 * @param account
	 * @return
	 */
	@POST
	@Path("/loginNewDemo")
	public ListRespt loginNewDemo(@PathParam("version") String version,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,Account account);
	
	
	/**
	 * 根据昵称查询子账号（定制接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param friendlyName
	 * @param appId
	 * @return
	 */
	@GET
    @Path("/ClientsByFriendlyName")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp findClientByFriendName(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,@QueryParam("friendlyName") String friendlyName
			,@QueryParam("appId") String appId);
	
	
	/**
	 * 应用账单（公共接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param accountBill
	 * @return
	 */
	@POST
	@Path("/billList")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp billList(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,AppBill accountBill);
	
	/**
	 * 根不定参数查询子账号（定制接口,该接口目前未公布使用）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param friendlyName
	 * @param appId
	 * @return
	 */
	@GET
    @Path("/ClientsByParam")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp findClientByParam(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,@QueryParam("param") String param
			,@QueryParam("appId") String appId);
	
	
	/**
	 * 监控接口
	 */
	@GET
	@Path("/Clients/monitor")
	public String monitor();
	
	/**
	 * 子账号创建接口（公共接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
	@Path("/applySDKID")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp applySDKID(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,SDK sdk);
	
	
	/**
	 * 子账号删除接口（公共接口）
	 * @param version
	 * @param accountSid
	 * @param auth
	 * @param sig
	 * @param client
	 * @return
	 */
	@POST
	@Path("/dropSDKID")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ListResp dropSDKID(@PathParam("version") String version,@PathParam("accountSid") String accountSid,@HeaderParam("Authorization") String auth,
			@QueryParam("sig") String sig,SDK sdk);
}
