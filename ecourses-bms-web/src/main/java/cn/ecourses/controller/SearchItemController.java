package cn.ecourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.search.service.SearchItemService;


//导入数据到索引库
@Controller
public class SearchItemController {
	
	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping("/index/item/import")
	@ResponseBody
	public ECoursesResult importItemList() {
		ECoursesResult ecoursesResult = searchItemService.importAllItems();
		return ecoursesResult;
		
	}
}
