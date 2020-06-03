package cn.ecourses.sso.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecourses.common.utils.CookieUtils;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.sso.service.LoginService;

//用户登录处理

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	@RequestMapping("/page/login")
	public String showLogin(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	@RequestMapping("/user/logout")
	public String showLogout(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "logout";
	}
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult login(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		ECoursesResult ecoursesResult = loginService.userLogin(username, password);
		//判断是否登录成功
		if(ecoursesResult.getStatus() == 200) {
			String token = ecoursesResult.getData().toString();
			System.out.println(token);
			System.out.println(TOKEN_KEY);
			//如果登录成功需要把token写入cookie
			CookieUtils.setCookie(request, response, TOKEN_KEY, token);
		}
		//返回结果
		return ecoursesResult;
	}
	
	@RequestMapping(value="/user/logout/{token}", method=RequestMethod.GET)
	@ResponseBody
	public ECoursesResult logout(@PathVariable String token,
			HttpServletRequest request, HttpServletResponse response){
	    ECoursesResult result = loginService.userLogout(token);
	    CookieUtils.setCookie(request, response, TOKEN_KEY, null);
	    return result;
	}
	
	
}
