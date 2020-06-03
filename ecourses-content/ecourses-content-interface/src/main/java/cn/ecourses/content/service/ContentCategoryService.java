package cn.ecourses.content.service;

import java.util.List;

import cn.ecourses.common.pojo.EasyUITreeNode;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesContentCategory;

public interface ContentCategoryService {
	List<EasyUITreeNode> getContentCatList(long parentId);
	ECoursesResult addContentCategory(long parentId, String name);
	ECoursesResult updateContentCategory(Long id, String name);
	ECoursesResult deleteContentCategory(Long id);
	List<EcoursesContentCategory> getChildNodeList(Long id);
	void deleteCategoryAndChildNode(Long id);
}
