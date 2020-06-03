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
import cn.ecourses.pojo.EcoursesAdmin;
import cn.ecourses.service.AdminService;


@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/admin/list")
	@ResponseBody
	public EasyUIDataGridResult getUserList(Integer page, Integer rows) {
		
		EasyUIDataGridResult result = adminService.getAdminList(page, rows);
		return result;
	}
	
	@RequestMapping("/admin/query/{adminId}")
	@ResponseBody
	public EcoursesAdmin getAdminById(@PathVariable Long adminId) {
		EcoursesAdmin admin = adminService.getAdminById(adminId);
		return admin;
	}
	
	@RequestMapping("/admin/delete")
	@ResponseBody
	public ECoursesResult deleteAdmin(@RequestParam("ids") String ids) {
	    ECoursesResult result = adminService.deleteAdminByIds(ids);
	    return result;
	}
	
	@RequestMapping(value="/admin/insert", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult insertAdmin(EcoursesAdmin admin) {
		ECoursesResult result = adminService.insertAdmin(admin);
		return result;
	}
	
	@RequestMapping(value="/admin/update", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult updateAdmin(EcoursesAdmin admin) {
		ECoursesResult result = adminService.updateAdmin(admin);
		return result;
	}
}
