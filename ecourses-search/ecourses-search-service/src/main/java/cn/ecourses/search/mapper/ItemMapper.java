package cn.ecourses.search.mapper;

import java.util.List;

import cn.ecourses.common.pojo.SearchItem;

public interface ItemMapper {

	List<SearchItem> getItemList();
	SearchItem getItemById(long itemId);
	
}
