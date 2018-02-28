package cn.shiro.authenticator;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试shiro认证
 */
public class TestShiro {
	private static final Logger logger = LoggerFactory.getLogger(TestShiro.class);

	/**
	 * 测试shiro认证--使用shiro自带realm
	 */
	@Test
	public void testAuthenticatorBySysRealm() {
		// 使用配置文件获取Factory工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 根据工厂实例获取安全管理器securityManager
		SecurityManager securityManager = factory.getInstance();
		// 将安全管理器设置到当前系统的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		// 获取当前主题
		Subject subject = SecurityUtils.getSubject();
		// 根据用户输入的用户名和密码构造当前系统的token
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
		// 登录认证
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		}
		// 验证是否认证通过
		boolean authenticated = subject.isAuthenticated();
		logger.debug("是否认证通过\t" + authenticated);
		// 退出
		subject.logout();
		authenticated = subject.isAuthenticated();
		logger.debug("是否认证通过\t" + authenticated);
	}

	/**
	 * 测试使用自定义realm实现认证
	 */
	@Test
	public void testAuthenticatorByCustomRealm() {
		// 使用配置文件获取Factory工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiroCustom.ini");
		// 根据工厂实例获取安全管理器securityManager
		SecurityManager securityManager = factory.getInstance();
		// 将安全管理器设置到当前系统的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		// 获取当前主题
		Subject subject = SecurityUtils.getSubject();
		// 根据用户输入的用户名和密码构造当前系统的token
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
		// 登录认证
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		}
		// 验证是否认证通过
		boolean authenticated = subject.isAuthenticated();
		logger.debug("是否认证通过\t" + authenticated);
		// 退出
		// subject.logout();
		// authenticated = subject.isAuthenticated();
		// logger.debug("是否认证通过\t" + authenticated);
	}

	@Test
	public void testCustomRealmCredit() {
		// 使用配置文件获取Factory工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiroCustomCredit.ini");
		// 根据工厂实例获取安全管理器securityManager
		SecurityManager securityManager = factory.getInstance();
		// 将安全管理器设置到当前系统的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		// 获取当前主题
		Subject subject = SecurityUtils.getSubject();
		// 根据用户输入的用户名和密码构造当前系统的token
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
		// 登录认证
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		}
		// 验证是否认证通过
		boolean authenticated = subject.isAuthenticated();
		logger.debug("是否认证通过\t" + authenticated);
	}

	@Test
	public void testSysPermission() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
		SecurityManager instance = factory.getInstance();
		SecurityUtils.setSecurityManager(instance);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 验证是否认证通过
		boolean authenticated = subject.isAuthenticated();
		logger.debug("是否认证通过\t" + authenticated);
		// 基于角色的权限授权
		boolean hasRole = subject.hasRole("role1");
		logger.debug("zhangsan是否拥有角色role1\t" + hasRole);
		// 是否拥有多个角色
		boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1", "role2"));
		logger.debug("zhangsan是否拥有多个角色\t" + hasAllRoles);
		// 使用subject.check
		subject.checkRole("role1");
		subject.checkRoles(Arrays.asList("role1", "role2"));

		// 基于资源的权限管理授权
		boolean permitted = subject.isPermitted("user:create");
		logger.debug("zhangsan是否有创建的权限\t" + permitted);
		boolean permittedAll = subject.isPermittedAll("user:create", "user:update");
		logger.debug("zhangsan是否拥有多个权限\t" + permittedAll);
	}

	/**
	 * 测试用户自定realm实现权限管理授权
	 * 
	 */
	@Test
	public void testCustPermission() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiroCustom.ini");
		SecurityManager instance = factory.getInstance();
		SecurityUtils.setSecurityManager(instance);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 验证是否认证通过
		boolean authenticated = subject.isAuthenticated();
		logger.debug("是否认证通过\t" + authenticated);
		// // 基于角色的权限授权
		// boolean hasRole = subject.hasRole("role1");
		// logger.debug("zhangsan是否拥有角色role1\t" + hasRole);
		// // 是否拥有多个角色
		// boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1",
		// "role2"));
		// logger.debug("zhangsan是否拥有多个角色\t" + hasAllRoles);
		// // 使用subject.check
		// subject.checkRole("role1");
		// subject.checkRoles(Arrays.asList("role1", "role2"));

		// 基于资源的权限管理授权
		boolean permitted = subject.isPermitted("user:create");
		logger.debug("zhangsan是否有创建的权限\t" + permitted);
		boolean permittedAll = subject.isPermittedAll("user:create", "user:update");
		logger.debug("zhangsan是否拥有多个权限\t" + permittedAll);
	}
}
