package shirodemo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.shiro.demo.dao.UserDao;
import cn.shiro.demo.entity.ActiveUser;
import cn.shiro.demo.entity.UserInfo;

public class TestUser {
	UserDao userdao;

	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
		userdao = context.getBean(UserDao.class);
	}

	@Test
	public void testUserDao() {
		UserInfo userInfoByUserCode = userdao.getUserInfoByUserCode("zhangsan");
		System.out.println(userInfoByUserCode);
	}
}
