package cn.ecourses.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ecourses.common.pojo.EasyUITreeNode;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.content.service.ContentCategoryService;
import cn.ecourses.mapper.EcoursesContentCategoryMapper;
import cn.ecourses.pojo.EcoursesContentCategory;
import cn.ecourses.pojo.EcoursesContentCategoryExample;
import cn.ecourses.pojo.EcoursesContentCategoryExample.Criteria;

//Content classification management Service
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private EcoursesContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCatList(long parentId) {
		// List of query subnodes based on parentid
		EcoursesContentCategoryExample example = new EcoursesContentCategoryExample();
		Criteria criteria = example.createCriteria();
		//Setting up query conditions
		criteria.andParentIdEqualTo(parentId);
		//Execute the query
		List<EcoursesContentCategory> catList = contentCategoryMapper.selectByExample(example);
		//Convert to EasyUITreeNode list
		List<EasyUITreeNode> nodeList = new ArrayList<>();
		for (EcoursesContentCategory ecoursesContentCategory : catList) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(ecoursesContentCategory.getId());
			node.setText(ecoursesContentCategory.getName());
			node.setState(ecoursesContentCategory.getIsParent()?"closed":"open");
			//Add to the list
			nodeList.add(node);
		}
		return nodeList;
	}

	@Override
	public ECoursesResult addContentCategory(long parentId, String name) {
		//Create a POJO object corresponding to the ecourses_content_category table
		EcoursesContentCategory contentCategory = new EcoursesContentCategory();
		//Setting the properties of the POJO
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		//1(normal),2(delete)
		contentCategory.setStatus(1);
		//The default sort is 1
		contentCategory.setSortOrder(1);
		//The newly added node must be a leaf node
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//Insert into the database
		contentCategoryMapper.insert(contentCategory);
		//Determine the isparent property of the parent node. If not true is changed to true
		//Query the parent node according to parentid
		EcoursesContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			//Update to the database
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		//Return to the result, return to ECoursesResult, including POJO
		return ECoursesResult.ok(contentCategory);
	}

	@Override
	public ECoursesResult updateContentCategory(Long id, String name) {
		//创建一个pojo
		EcoursesContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult deleteContentCategory(Long id) {
		deleteCategoryAndChildNode(id);
		return ECoursesResult.ok();
	}

	@Override
	public List<EcoursesContentCategory> getChildNodeList(Long id) {
		//查询所有父节点为传入id的节点
	    EcoursesContentCategoryExample example = new EcoursesContentCategoryExample();
	    Criteria criteria = example.createCriteria();
	    criteria.andParentIdEqualTo(id);
	    //返回所有符合要求的节点
	    return contentCategoryMapper.selectByExample(example);
	}

	@Override
	public void deleteCategoryAndChildNode(Long id) {
		//获取要删除的Category
	    EcoursesContentCategory ecContentCategory = new EcoursesContentCategory();
	    ecContentCategory = contentCategoryMapper.selectByPrimaryKey(id);
	    
	    //判断是否为父节点
	    if(ecContentCategory.getIsParent()){
	        //获得所有该节点下的孩子节点
	        List<EcoursesContentCategory> list = getChildNodeList(id);
	        //删除所有孩子节点
	        for(EcoursesContentCategory category : list){
	            deleteCategoryAndChildNode(category.getId());
	        }
	    }
	    //判断父节点下是否还有其他子节点
	    if(getChildNodeList(ecContentCategory.getParentId()).size()==1){
	        //没有则将父节点标记为叶子节点
	        EcoursesContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(ecContentCategory.getParentId());
	        parentCategory.setIsParent(false);
	        contentCategoryMapper.updateByPrimaryKey(parentCategory);
	        
	    }
	    //删除本节点
	    contentCategoryMapper.deleteByPrimaryKey(id);
	    return;
	}

}
