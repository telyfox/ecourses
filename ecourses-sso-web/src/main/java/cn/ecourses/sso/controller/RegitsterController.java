package cn.ecourses.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesUser;
import cn.ecourses.sso.service.RegisterService;

//注册功能Controller

@Controller
public class RegitsterController {
	
	@Autowired
	private RegisterService registerService;

	@RequestMapping("/page/register")
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public ECoursesResult checkData(@PathVariable String param, @PathVariable Integer type) {
		ECoursesResult ecoursesResult = registerService.checkData(param, type);
		return ecoursesResult;
	}
	
	@RequestMapping(value="/user/register", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult register(EcoursesUser user) {
		ECoursesResult ecoursesResult = registerService.register(user);
		return ecoursesResult;
	}
}
