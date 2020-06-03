package cn.ecourses.order.service;

import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.order.pojo.OrderInfo;

public interface OrderService {

	ECoursesResult createOrder(OrderInfo orderInfo);
}
