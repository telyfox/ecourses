package cn.ecourses.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ecourses.common.jedis.JedisClient;
import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.common.utils.JsonUtils;
import cn.ecourses.content.service.ContentService;
import cn.ecourses.mapper.EcoursesContentMapper;
import cn.ecourses.pojo.EcoursesContent;
import cn.ecourses.pojo.EcoursesContentExample;
import cn.ecourses.pojo.EcoursesContentExample.Criteria;

//内容管理Service
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private EcoursesContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;
	
	@Override
	public ECoursesResult addContent(EcoursesContent content) {
		//将内容数据插入到内容表
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入到数据库
		contentMapper.insert(content);
		//缓存同步,删除缓存中对应的数据。
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return ECoursesResult.ok();
	}


	// 根据内容分类id查询内容列表
	@Override
	public List<EcoursesContent> getContentListByCid(long cid) {
		//查询缓存
		try {
			//如果缓存中有直接响应结果
			String json = jedisClient.hget(CONTENT_LIST, cid + "");
			if (StringUtils.isNotBlank(json)) {
				List<EcoursesContent> list = JsonUtils.jsonToList(json, EcoursesContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果没有查询数据库
		EcoursesContentExample example = new EcoursesContentExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<EcoursesContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//把结果添加到缓存
		try {
			jedisClient.hset(CONTENT_LIST, cid + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public ECoursesResult deleteContentByIds(String ids) {
		String[] idArray = ids.split(","); 
		for (String id : idArray) {  
			contentMapper.deleteByPrimaryKey(Long.valueOf(id));  
        }  
		return ECoursesResult.ok();
	}


	@Override
	public ECoursesResult updateContent(EcoursesContent content) {
		//将内容数据插入到内容表
		content.setUpdated(new Date());
		//插入到数据库
		contentMapper.updateByPrimaryKeySelective(content);
		//缓存同步,删除缓存中对应的数据。
		jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
		return ECoursesResult.ok();
	}


	@Override
	public EasyUIDataGridResult queryContentList(Long categoryId, int page, int rows) {
		//分页处理
		PageHelper.startPage(page, rows);
                //查询课程列表
		EcoursesContentExample example = new EcoursesContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
		List<EcoursesContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//取记录总条数
		PageInfo<EcoursesContent> pageInfo = new PageInfo<>(list);
                //创建EasyUIDataGridResult结果集
		EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
