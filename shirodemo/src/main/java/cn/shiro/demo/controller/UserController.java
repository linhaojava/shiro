package cn.shiro.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.shiro.demo.entity.ActiveUser;
import cn.shiro.demo.exception.CustomException;

@Controller
public class UserController {
	@RequestMapping(value = "/towelcome")
	public String towelcome(ModelMap map) {
		Subject subject = SecurityUtils.getSubject();
		ActiveUser user = (ActiveUser) subject.getPrincipal();
		map.put("userInfo", user);
		return "welcome";
	}

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) throws Exception {
		// 如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		// 根据shiro返回的异常类路径判断，抛出指定异常信息
		if (exceptionClassName != null) {
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				// 最终会抛给异常处理器
				throw new CustomException("账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				throw new CustomException("用户名/密码错误");
			} else if ("randomCodeError".equals(exceptionClassName)) {
				throw new CustomException("验证码错误 ");
			} else {
				throw new Exception();// 最终在异常处理器生成未知错误
			}
		}
		return "login";
	}
}
