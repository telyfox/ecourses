package cn.ecourses.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.mapper.EcoursesOrderBmsMapper;
import cn.ecourses.mapper.EcoursesOrderItemMapper;
import cn.ecourses.mapper.EcoursesOrderMapper;
import cn.ecourses.pojo.EcoursesOrder;
import cn.ecourses.pojo.EcoursesOrderBms;
import cn.ecourses.pojo.EcoursesOrderItemExample;
import cn.ecourses.pojo.EcoursesOrderItemExample.Criteria;
import cn.ecourses.service.BmsOrderListService;

@Service
public class BmsOrderListServiceImpl implements BmsOrderListService {

	@Autowired
	EcoursesOrderBmsMapper orderBmsMapper;
	@Autowired
	EcoursesOrderMapper orderMapper;
	@Autowired
	EcoursesOrderItemMapper orderItemMapper;
	
	@Override
	public EasyUIDataGridResult getBmsOrderList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		List<EcoursesOrderBms> list = orderBmsMapper.getOrderBmsList();
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<EcoursesOrderBms> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public ECoursesResult deleteBmsOrderListByOrderIds(String orderIds) {
		String[] idArray = orderIds.split(","); 
		EcoursesOrderItemExample example = new EcoursesOrderItemExample();
		Criteria criteria = example.createCriteria();
		
		for (String id : idArray) {  
			orderMapper.deleteByPrimaryKey(id);
			criteria.andOrderIdEqualTo(id);
			orderItemMapper.deleteByExample(example);
        }  
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult deliveryOrder(EcoursesOrder order) {
		order.setUpdateTime(new Date());
		order.setConsignTime(new Date());
		order.setStatus(4);
		orderMapper.updateByPrimaryKeySelective(order);
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult stopDeliveryOrder(String orderIds) {
		String[] idArray = orderIds.split(","); 
		EcoursesOrder order = new EcoursesOrder();
		for (String id : idArray) {  
			order.setOrderId(id);
			order.setUpdateTime(new Date());
			order.setStatus(3);
			order.setShippingName("无");
			order.setShippingCode("0");
			orderMapper.updateByPrimaryKeySelective(order);
		}
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult completeOrder(String orderIds) {
		String[] idArray = orderIds.split(","); 
		EcoursesOrder order = new EcoursesOrder();
		for (String id : idArray) {
			order.setOrderId(id);
			order.setUpdateTime(new Date());
			order.setEndTime(new Date());
			order.setStatus(5);
			orderMapper.updateByPrimaryKeySelective(order);
		}
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult closeOrder(String orderIds) {
		String[] idArray = orderIds.split(","); 
		EcoursesOrder order = new EcoursesOrder();
		for (String id : idArray) {
			order.setOrderId(id);
			order.setUpdateTime(new Date());
			order.setCloseTime(new Date());;
			order.setStatus(6);
			orderMapper.updateByPrimaryKeySelective(order);
		}
		return ECoursesResult.ok();
	}

}
