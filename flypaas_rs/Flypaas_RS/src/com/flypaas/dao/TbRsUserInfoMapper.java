package com.flypaas.dao;

import java.util.List;
import java.util.Map;

import com.flypaas.model.TbRsUserInfo;

public interface TbRsUserInfoMapper {
    int deleteByPrimaryKey(String sid);

    int insert(TbRsUserInfo record);

    int insertSelective(TbRsUserInfo record);

    TbRsUserInfo selectByPrimaryKey(String sid);

    int updateByPrimaryKeySelective(TbRsUserInfo record);

    int updateByPrimaryKey(TbRsUserInfo record);
    /**
     * 登录检测  条数为1进入  为0登录失败
     * @param record
     * @return
     */
    public  Map queryUserInfo(TbRsUserInfo record);
    
    public  TbRsUserInfo queryUserInfo2(String email);
    
    public  int updateUserStatusBySid(String sid);
    
    public int updateUserValiCode(TbRsUserInfo tbRsUserInfo);
    
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
     * 查询所有的用户  状态正常
     * @return
     */
    public List<TbRsUserInfo> queryAllUser(String netSid);
    
    /**
     * 查询当前账号的用户数量
     * @return
     */
    public int queryCount(String netSid);
    
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

	public TbRsUserInfo queryUserByMobile(String mobile);
    
}