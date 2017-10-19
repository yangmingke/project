package com.flypaas.service.user;

import java.util.List;

import com.flypaas.model.TbRsRoleUser;
import com.flypaas.model.TbRsUserInfo;

/**
 * 用户管理
 * @author win10
 *
 */
public interface UserService {
	
	/**
     * 修改用户密码
     * @param tbRsUserInfo
     * @return
     */
    public int editUserPwd(TbRsUserInfo tbRsUserInfo);
    
    /**
     * 修改用户信息
     * @param tbRsUserInfo
     * @return
     */
    public int editUser(TbRsUserInfo tbRsUserInfo);
    
    /**
     * 
     * 查询所有状态正常的用户（管理员，运营人员，账务人员）
     * @return
     */
    public List<TbRsUserInfo> queryUserList(String netSid);
    
    /**
     * 查询当前账号的用户数量
     * @return
     */
    public int queryCount(String netSid);
    
    /**
     * 管理员进去后可添加用户
     * @param tbRsUserInfo
     * @return
     */
    public int addUser(TbRsUserInfo tbRsUserInfo);
    
   /**
    * 修改用户信息
    * @param record
 * @param tbRsRoleUser 
    * @return
    */
    public int updateByPrimaryKeySelective(TbRsUserInfo record);
    
    /**
     * 删除当前选中的用户
     * @param sid
     * @return
     */
    public int delUser(String sid);
    
    /**
     * 按sid查询用户的信息
     * @param sid
     * @return
     */
    public TbRsUserInfo queryUserInfoById(String sid);

	int updateSelective(TbRsUserInfo record, TbRsRoleUser tbRsRoleUser);
    
}
