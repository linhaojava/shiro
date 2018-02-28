package cn.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义realm和凭证
 * 
 * @className:CustomCreditRealmGloble
 * @description:
 * @company:美讯在线
 * @author：lh
 * @createDate：2018年2月23日上午11:36:22
 * @version:1.0
 */
public class CustomCreditRealm extends AuthorizingRealm {
	/**
	 * 设置自定义realm和凭证
	 */
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName("customRealmCredit");
	}

	/**
	 * 用于授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 用于认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userCode = (String) token.getPrincipal();
		if (userCode == null) {
			return null;
		}
		String password = "cb571f7bd7a6f73ab004a70322b963d5";
		String salt = "eteokues";
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password,
				ByteSource.Util.bytes(salt), this.getName());
		return simpleAuthenticationInfo;
	}

}
