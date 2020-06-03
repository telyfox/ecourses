package cn.ecourses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecourses.common.pojo.EasyUITreeNode;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.content.service.ContentCategoryService;

//内容分类管理Controller


@Controller
@RequestMapping("/content/category")
public class ContentCatController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(
			@RequestParam(name="id", defaultValue="0")Long parentId) {
		List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
		return list;
	}
	
	
	 //添加分类节点
	 
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult createContentCategory(Long parentId, String name) {
		//调用服务添加节点
		ECoursesResult ecoursesResult = contentCategoryService.addContentCategory(parentId, name);
		return ecoursesResult;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ECoursesResult updateNode(Long id, String name) {
		ECoursesResult result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ECoursesResult deleteNode(Long id) {
		ECoursesResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}
}
