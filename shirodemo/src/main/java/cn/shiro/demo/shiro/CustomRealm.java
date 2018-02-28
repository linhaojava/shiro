package cn.shiro.demo.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.shiro.demo.entity.ActiveUser;
import cn.shiro.demo.entity.UserInfo;
import cn.shiro.demo.service.UserService;

/**
 * 自定义realm实现
 * 
 * @className:CustomRealmGloble
 * @description:
 * @company:美讯在线
 * @author：lh
 * @createDate：2018年2月23日下午4:22:35
 * @version:1.0
 */
public class CustomRealm extends AuthorizingRealm {
	@Autowired
	UserService userService;

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName("customRealm");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String usercode = (String) token.getPrincipal();
		UserInfo userInfo = userService.getUserInfoByUserCode(usercode);
		if (userInfo == null) {
			return null;
		}
		String password = userInfo.getPassword();
		String salt = userInfo.getSalt();
		ActiveUser user = new ActiveUser();
		user.setUsercode(usercode);
		user.setUserid(userInfo.getId());
		user.setUsername(userInfo.getUsername());
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password,
				ByteSource.Util.bytes(salt), this.getName());
		return simpleAuthenticationInfo;
	}

}
