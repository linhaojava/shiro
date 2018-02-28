package cn.shiro.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.shiro.demo.dao.UserDao;
import cn.shiro.demo.entity.UserInfo;
import cn.shiro.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public UserInfo getUserInfoByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return userDao.getUserInfoByUserCode(userCode);
	}

}
