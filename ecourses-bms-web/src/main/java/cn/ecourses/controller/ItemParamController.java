package cn.ecourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesItemParam;
import cn.ecourses.service.ItemParamService;

//课程规格参数模板管理Controller

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public ECoursesResult getItemParamByCid(@PathVariable Long itemCatId) {
		ECoursesResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	@RequestMapping("/item/query/{itemId}")
	@ResponseBody
	public EcoursesItemParam getItemParamById(@PathVariable Long itemId) {
		EcoursesItemParam ecItemParam = itemParamService.getItemParamById(itemId);
		return ecItemParam;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public ECoursesResult insertItemParam(@PathVariable Long cid, String paramData) {
		//创建pojo对象
		EcoursesItemParam itemParam = new EcoursesItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		ECoursesResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ECoursesResult deleteParam(@RequestParam("ids") String ids) {
	    ECoursesResult result = itemParamService.deleteItemParamByIds(ids);
	    return result;
	}
}
