package cn.shiro.demo.service;

import cn.shiro.demo.entity.UserInfo;

public interface UserService {
	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param userCode
	 * @return
	 */
	UserInfo getUserInfoByUserCode(String userCode);
}
