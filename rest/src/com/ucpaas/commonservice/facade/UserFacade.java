package com.ucpaas.commonservice.facade;

import com.ucpaas.commonservice.model.UserInfo;
import com.ucpaas.commonservice.model.base.ResultInfo;

/**
 * 开发者用户服务接口
 * 
 * @author luke
 * 
 */
public interface UserFacade {

	/**
	 * 更新用户信息,同时删除缓存中的数据
	 * 
	 * @param userInfo
	 * @return ResultInfo
	 *         <p>
	 *         userInfo==null,返回actionResult=false;errorCode=ErrorCode.C200001 <br/>
	 *         操作成功,返回actionResult=true;errorCode=ErrorCode.C100000; 更新1条记录,affectedRows=1;
	 *         更新0条记录,affectedRows=0; <br/>
	 *         系统内部错误,返回actionResult=false;errorCode=ErrorCode.C200099 <br/>
	 */
	ResultInfo<UserInfo> updateBySidCache(UserInfo userInfo);

}
