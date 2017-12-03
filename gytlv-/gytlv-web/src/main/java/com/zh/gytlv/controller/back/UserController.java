package com.zh.gytlv.controller.back;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zh.gytlv.entity.User;
import com.zh.gytlv.service.back.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String toLogin() {
		return "/back/login";
	}

	/**
	 * 到达注册页面
	 * 
	 * @return
	 */
	@RequestMapping("/reg")
	public String toReg() {
		return "/back/reg";
	}

	/**
	 * 验证码页面
	 * 
	 * @return
	 */
	@RequestMapping("/validatecode")
	public String validatecode() {
		return "/back/validatecode";
	}

	/**
	 * 登录校验
	 * 
	 * @param request
	 * @param username
	 * @param password
	 * @param rememberMe
	 * @return
	 */
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	@ResponseBody
	public Object checkLogin(HttpServletRequest request, String username, String password, boolean rememberMe) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			try {
				UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
				resultMap.put("status", 200);
				resultMap.put("msg", "登录成功!");
				currentUser.login(token);
			}
			catch (UnknownAccountException ae) {
				resultMap.put("status", 500);
				resultMap.put("msg","该用户不存在!");
			} catch (AuthenticationException ae) {
				resultMap.put("status", 500);
				resultMap.put("msg", "用户身份认证失败!");
			}
		}
		return resultMap;
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/doReg")
	@ResponseBody
	public Object doReg(HttpServletRequest request, String username, String password, String randomcode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		User u = userService.getUserByName(username);
		// 校验用户名
		if (u != null) {
			resultMap.put("status", 500);
			return resultMap;
		}
		// 校验验证码
		if (!request.getSession().getAttribute("validateCode").equals(randomcode)) {
			resultMap.put("status", 501);
			return resultMap;
		}
		userService.regUser(username, password);
		request.getSession().removeAttribute("validateCode");
		resultMap.put("status", 200);
		return resultMap;
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "/";
	}
}
