package cn.shiro.demo.dao;

import cn.shiro.demo.entity.UserInfo;

/**
 * 获取用户信息DAO
 * 
 * @className:getUserInfoGloble
 * @description:
 * @company:美讯在线
 * @author：lh
 * @createDate：2018年2月23日下午2:57:04
 * @version:1.0
 */
public interface UserDao {
	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param userCode
	 * @return
	 */
	UserInfo getUserInfoByUserCode(String userCode);
}
