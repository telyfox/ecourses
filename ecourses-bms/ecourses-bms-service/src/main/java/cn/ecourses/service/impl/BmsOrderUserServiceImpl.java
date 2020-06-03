package cn.ecourses.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.mapper.EcoursesOrderShippingMapper;
import cn.ecourses.pojo.EcoursesOrderShipping;
import cn.ecourses.pojo.EcoursesOrderShippingExample;
import cn.ecourses.pojo.EcoursesOrderShippingExample.Criteria;
import cn.ecourses.service.BmsOrderUserService;

@Service
public class BmsOrderUserServiceImpl implements BmsOrderUserService {

	@Autowired
	private EcoursesOrderShippingMapper orderShippingMapper;
	
	@Override
	public EasyUIDataGridResult getBmsOrderUserList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		EcoursesOrderShippingExample example = new EcoursesOrderShippingExample();
		List<EcoursesOrderShipping> list = orderShippingMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<EcoursesOrderShipping> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public ECoursesResult deleteBmsOrderUserByIds(String ids) {
		String[] idArray = ids.split(","); 
		for (String id : idArray) {  
			orderShippingMapper.deleteByPrimaryKey(id);  
        }  
		return ECoursesResult.ok();
	}

	@Override
	public EcoursesOrderShipping getBmsOrderUserByOrderId(String orderId) {
		EcoursesOrderShippingExample example = new EcoursesOrderShippingExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<EcoursesOrderShipping> list = orderShippingMapper.selectByExample(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			EcoursesOrderShipping orderShipping = list.get(0);
			return orderShipping;
		}
		return null;
	}

	@Override
	public ECoursesResult updateBmsOrderUser(EcoursesOrderShipping orderShipping) {
		//补全pojo
		orderShipping.setUpdated(new Date());
		//插入到规格参数模板表
		orderShippingMapper.updateByPrimaryKeySelective(orderShipping);
		return ECoursesResult.ok();
	}

}
