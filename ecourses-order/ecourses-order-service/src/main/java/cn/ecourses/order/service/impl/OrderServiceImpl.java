package cn.ecourses.order.service.impl;

import java.util.Date;
import java.util.List;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ecourses.common.jedis.JedisClient;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.mapper.EcoursesOrderItemMapper;
import cn.ecourses.mapper.EcoursesOrderMapper;
import cn.ecourses.mapper.EcoursesOrderShippingMapper;
import cn.ecourses.order.pojo.OrderInfo;
import cn.ecourses.order.service.OrderService;
import cn.ecourses.pojo.EcoursesOrderItem;
import cn.ecourses.pojo.EcoursesOrderShipping;

//订单处理服务

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private EcoursesOrderMapper orderMapper;
	@Autowired
	private EcoursesOrderItemMapper orderItemMapper;
	@Autowired
	private EcoursesOrderShippingMapper orderShippingMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_ID_GEN_KEY}")
	private String ORDER_ID_GEN_KEY;
	@Value("${ORDER_ID_START}")
	private String ORDER_ID_START;
	@Value("${ORDER_DETAIL_ID_GEN_KEY}")
	private String ORDER_DETAIL_ID_GEN_KEY;
	
	@Override
	public ECoursesResult createOrder(OrderInfo orderInfo) {
		//生成订单号。使用redis的incr生成。
		if (!jedisClient.exists(ORDER_ID_GEN_KEY)) {
			jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_START);
		}
		String orderId = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
		//补全orderInfo的属性
		orderInfo.setOrderId(orderId);
		//1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		orderInfo.setStatus(1);
		orderInfo.setCreateTime(new Date());
		orderInfo.setUpdateTime(new Date());
		//插入订单表
		orderMapper.insert(orderInfo);
		//向订单明细表插入数据。
		List<EcoursesOrderItem> orderItems = orderInfo.getOrderItems();
		for (EcoursesOrderItem ecoursesOrderItem : orderItems) {
			//生成明细id
			String odId = jedisClient.incr(ORDER_DETAIL_ID_GEN_KEY).toString();
			//补全pojo的属性
			ecoursesOrderItem.setId(odId);
			ecoursesOrderItem.setOrderId(orderId);
			//向明细表插入数据
			orderItemMapper.insert(ecoursesOrderItem);
		}
		//向订单物流表插入数据
		EcoursesOrderShipping orderShipping = orderInfo.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		orderShippingMapper.insert(orderShipping);
		//返回ECoursesResult，包含订单号
		return ECoursesResult.ok(orderId);
	}

}
