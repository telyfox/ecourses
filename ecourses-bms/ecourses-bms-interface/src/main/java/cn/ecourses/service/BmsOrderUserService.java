package cn.ecourses.service;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesOrderShipping;

public interface BmsOrderUserService {
	EasyUIDataGridResult getBmsOrderUserList(int page, int rows);
	ECoursesResult deleteBmsOrderUserByIds(String ids);
	EcoursesOrderShipping getBmsOrderUserByOrderId(String orderId);
	ECoursesResult updateBmsOrderUser(EcoursesOrderShipping orderShipping);
}
