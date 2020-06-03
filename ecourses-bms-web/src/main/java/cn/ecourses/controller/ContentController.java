package cn.ecourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.content.service.ContentService;
import cn.ecourses.pojo.EcoursesContent;

//内容管理Controller
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult addContent(EcoursesContent content) {
		//调用服务把内容数据保存到数据库
		ECoursesResult ecoursesResult = contentService.addContent(content);
		return ecoursesResult;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ECoursesResult deleteParam(@RequestParam("ids") String ids) {
	    ECoursesResult result = contentService.deleteContentByIds(ids);
	    return result;
	}
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIDataGridResult queryContentList(Long categoryId, int page, int rows) {
		return contentService.queryContentList(categoryId, page, rows);
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult editParam(EcoursesContent content) {
	    ECoursesResult result = contentService.updateContent(content);
	    return result;
	}
}
