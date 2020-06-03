package cn.ecourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesUser;
import cn.ecourses.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/list")
	@ResponseBody
	public EasyUIDataGridResult getUserList(Integer page, Integer rows) {
		//调用服务查询商品列表
		EasyUIDataGridResult result = userService.getUserList(page, rows);
		return result;
	}
	
	@RequestMapping("/user/query/{userId}")
	@ResponseBody
	public EcoursesUser getUserById(@PathVariable Long userId) {
		EcoursesUser user = userService.getUserById(userId);
		return user;
	}
	
	@RequestMapping("/user/delete")
	@ResponseBody
	public ECoursesResult deleteUser(@RequestParam("ids") String ids) {
	    ECoursesResult result = userService.deleteUserByIds(ids);
	    return result;
	}
	
	@RequestMapping(value="/user/insert", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult insertUser(EcoursesUser user) {
		ECoursesResult result = userService.insertUser(user);
		return result;
	}
	
	@RequestMapping(value="/user/update", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult updateUser(EcoursesUser user) {
		ECoursesResult result = userService.updateByPrimaryKey(user);
		return result;
	}

}
