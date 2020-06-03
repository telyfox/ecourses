package cn.ecourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecourses.pojo.EcoursesItemDesc;
import cn.ecourses.service.ItemDescService;

@Controller
public class ItemDescController {
	@Autowired
	private ItemDescService itemDescService;
	
	@RequestMapping("/item/query/item/desc/{itemId}")
	@ResponseBody
	public EcoursesItemDesc getItemDescById(@PathVariable Long itemId) {
		EcoursesItemDesc ecItemDesc = itemDescService.getItemDescById(itemId);
		return ecItemDesc;
	}
}
