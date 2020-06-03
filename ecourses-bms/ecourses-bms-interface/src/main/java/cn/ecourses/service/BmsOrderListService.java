package cn.ecourses.service;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesOrder;

public interface BmsOrderListService {
	EasyUIDataGridResult getBmsOrderList(int page, int rows);
	ECoursesResult deleteBmsOrderListByOrderIds(String orderIds);
	ECoursesResult deliveryOrder(EcoursesOrder order);
	ECoursesResult stopDeliveryOrder(String orderIds);
	ECoursesResult completeOrder(String orderIds);
	ECoursesResult closeOrder(String orderIds);
}
