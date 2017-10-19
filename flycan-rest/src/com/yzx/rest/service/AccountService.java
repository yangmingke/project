package com.yzx.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.yzx.rest.models.Account;
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
	
}
