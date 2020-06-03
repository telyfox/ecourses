package cn.ecourses.service;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.pojo.EcoursesItem;
import cn.ecourses.pojo.EcoursesItemDesc;
import cn.ecourses.common.utils.ECoursesResult;

public interface ItemService {
	EcoursesItem getItemById(long ItemId);
	EcoursesItemDesc getItemDescById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
	ECoursesResult addItem(EcoursesItem item, String desc);
	ECoursesResult updateItem(EcoursesItem item, String desc);
	ECoursesResult deleteItemByIds(String ids);
	ECoursesResult instockItemByIds(String ids);
	ECoursesResult reshelfItemByIds(String ids);
	
}
