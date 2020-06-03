package cn.ecourses.service;

import java.util.List;

import cn.ecourses.common.pojo.EasyUITreeNode;

public interface ItemCatService {

	List<EasyUITreeNode> getItemCatlist(long parentId);
}
