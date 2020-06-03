package cn.ecourses.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ecourses.item.pojo.Item;
import cn.ecourses.pojo.EcoursesItem;
import cn.ecourses.pojo.EcoursesItemDesc;
import cn.ecourses.service.ItemService;


//详情页面展示Controller

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	
	@RequestMapping("/item/{itemId}")
	public String showItemInfo(@PathVariable Long itemId, Model model) {
		//调用服务取基本信息
		EcoursesItem ecoursesItem = itemService.getItemById(itemId);
		Item item = new Item(ecoursesItem);
		//取商品描述信息
		EcoursesItemDesc itemDesc = itemService.getItemDescById(itemId);
		//把信息传递给页面
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		//返回逻辑视图
		return "item";
	}
}
