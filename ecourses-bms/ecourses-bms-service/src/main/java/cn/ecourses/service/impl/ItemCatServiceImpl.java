package cn.ecourses.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ecourses.common.pojo.EasyUITreeNode;
import cn.ecourses.mapper.EcoursesItemCatMapper;
import cn.ecourses.pojo.EcoursesItemCat;
import cn.ecourses.pojo.EcoursesItemCatExample;
import cn.ecourses.service.ItemCatService;
import cn.ecourses.pojo.EcoursesItemCatExample.Criteria;

//分类管理

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private EcoursesItemCatMapper itemCatMapper;
	
	@Override
	public List<EasyUITreeNode> getItemCatlist(long parentId) {
		//根据parentId查询子节点列表
		EcoursesItemCatExample example = new EcoursesItemCatExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<EcoursesItemCat> list = itemCatMapper.selectByExample(example);
		//创建返回结果List
		List<EasyUITreeNode> resultList = new ArrayList<>();
		//把列表转换成EasyUITreeNode列表
		for (EcoursesItemCat ecoursesItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			//设置属性
			node.setId(ecoursesItemCat.getId());
			node.setText(ecoursesItemCat.getName());
			node.setState(ecoursesItemCat.getIsParent()?"closed":"open");
			//添加到结果列表
			resultList.add(node);
		}
		//返回结果
		return resultList;
	}

}
