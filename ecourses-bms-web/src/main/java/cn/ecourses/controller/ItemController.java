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
import cn.ecourses.pojo.EcoursesItem;
import cn.ecourses.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public EcoursesItem getItemById(@PathVariable Long itemId) {
		EcoursesItem ecoursesItem = itemService.getItemById(itemId);
		return ecoursesItem;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		//调用服务查询商品列表
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	
	 //课程添加功能 
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult addItem(EcoursesItem item, String desc) {
		ECoursesResult result = itemService.addItem(item, desc);
		return result;
	}
	
	@RequestMapping("/item/delete")
	@ResponseBody
	public ECoursesResult deleteParam(@RequestParam("ids") String ids) {
	    ECoursesResult result = itemService.deleteItemByIds(ids);
	    return result;
	}
	
	 //课程修改功能 
	@RequestMapping(value="/item/update", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult updateItem(EcoursesItem item, String desc) {
		ECoursesResult result = itemService.updateItem(item, desc);
		return result;
	}
	
	//课程下架
	@RequestMapping("/item/instock")
	@ResponseBody
	public ECoursesResult instockParam(@RequestParam("ids") String ids) {
	    ECoursesResult result = itemService.instockItemByIds(ids);
	    return result;
	}
	
	//课程上架
	@RequestMapping("/item/reshelf")
	@ResponseBody
	public ECoursesResult reshelfParamParam(@RequestParam("ids") String ids) {
	    ECoursesResult result = itemService.reshelfItemByIds(ids);
	    return result;
	}
}
