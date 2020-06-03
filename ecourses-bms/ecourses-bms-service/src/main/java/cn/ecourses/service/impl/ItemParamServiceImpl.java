package cn.ecourses.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.mapper.EcoursesItemParamMapper;
import cn.ecourses.pojo.EcoursesItemParam;
import cn.ecourses.pojo.EcoursesItemParamExample;
import cn.ecourses.pojo.EcoursesItemParamExample.Criteria;
import cn.ecourses.service.ItemParamService;

//课程规格参数模板管理

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private EcoursesItemParamMapper itemParamMapper;
	
	@Override
	public EasyUIDataGridResult getItemParamList(int page, int rows) {
		//查询课程列表
		EcoursesItemParamExample example = new EcoursesItemParamExample();
		//分页处理
		PageHelper.startPage(page, rows);
		//List<TbItemParam> list = itemParamMapper.selectByExample(example);
		//使用selectByExampleWithBLOBs()方法查询带text类型字段的结果集
		List<EcoursesItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<EcoursesItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public ECoursesResult getItemParamByCid(long cid) {
		EcoursesItemParamExample example = new EcoursesItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<EcoursesItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			return ECoursesResult.ok(list.get(0));
		}
		
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult insertItemParam(EcoursesItemParam itemParam) {
		//补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult deleteItemParamByIds(String ids) {
		String[] idArray = ids.split(","); 
		for (String id : idArray) {  
			itemParamMapper.deleteByPrimaryKey(Long.valueOf(id));  
        }  
		return ECoursesResult.ok();
	}

	@Override
	public EcoursesItemParam getItemParamById(long itemId) {
		//添加查询条件
				EcoursesItemParamExample example = new EcoursesItemParamExample();
				Criteria criteria = example.createCriteria();
				criteria.andIdEqualTo(itemId);
				List<EcoursesItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
				if (list != null && list.size() > 0) {
					EcoursesItemParam item = list.get(0);
					return item;
				}
				return null;
	}

}
