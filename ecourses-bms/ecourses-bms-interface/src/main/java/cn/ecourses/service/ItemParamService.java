package cn.ecourses.service;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesItemParam;

public interface ItemParamService {

	EasyUIDataGridResult getItemParamList(int page, int rows);
	ECoursesResult getItemParamByCid(long cid);
	ECoursesResult insertItemParam(EcoursesItemParam itemParam);
	ECoursesResult deleteItemParamByIds(String ids);
	EcoursesItemParam getItemParamById(long itemId);
	
}
