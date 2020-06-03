package cn.ecourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesOrderShipping;
import cn.ecourses.service.BmsOrderUserService;

@Controller
public class BmsOrderUserController {
	
	@Autowired
	private BmsOrderUserService bmsOrderUserService;
	

	@RequestMapping("/bms/order/user/list")
	@ResponseBody
	public EasyUIDataGridResult getBmsOrderUserList(Integer page, Integer rows) {
		//调用服务查询商品列表
		EasyUIDataGridResult result = bmsOrderUserService.getBmsOrderUserList(page, rows);
		return result;
	}
	
	@RequestMapping("/bms/order/user/delete")
	@ResponseBody
	public ECoursesResult deleteBmsOrderUser(@RequestParam("ids") String ids) {
	    ECoursesResult result = bmsOrderUserService.deleteBmsOrderUserByIds(ids);
	    return result;
	}
	
	@RequestMapping(value="/bms/order/user/update", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult updateBmsOrderUser(EcoursesOrderShipping orderShipping) {
		ECoursesResult result = bmsOrderUserService.updateBmsOrderUser(orderShipping);
		return result;
	}
	
}
