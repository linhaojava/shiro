package cn.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义realm
 * 
 * @className:CustomRealmGloble
 * @description:
 * @company:美讯在线
 * @author：lh
 * @createDate：2018年2月23日上午10:44:02
 * @version:1.0
 */
public class CustomRealm extends AuthorizingRealm {
	/**
	 * 设置自定义Realm名
	 */
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName("customRealm");
	}

	/**
	 * 用于授权的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		//获取主身份信息
		String username = (String)principals.getPrimaryPrincipal();
		//使用身份信息从数据库中查询权限信息
		//次数模拟数据库中信息
		List<String> permissions=new ArrayList<String>();
		permissions.add("user:create");
		permissions.add("user:update");
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}

	/**
	 * 用于认证的方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		// 模拟从数据库中获取数据和输入的token比对
		// 获取用户
		String userCode = (String) token.getPrincipal();
		// 判断用户是否存在--使用userCode从数据库中获取用户信息
		if (userCode == null) {
			return null;
		}
		String password = "123456";
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password,
				this.getName());
		return simpleAuthenticationInfo;
	}

}
