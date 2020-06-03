package cn.ecourses.content.service;

import java.util.List;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesContent;

public interface ContentService {
	ECoursesResult addContent(EcoursesContent content);
	List<EcoursesContent> getContentListByCid(long cid);
	EasyUIDataGridResult queryContentList(Long categoryId, int page, int rows);
	ECoursesResult deleteContentByIds(String ids);
	ECoursesResult updateContent(EcoursesContent content);
}
