package cn.ecourses.order.pojo;

import java.io.Serializable;
import java.util.List;

import cn.ecourses.pojo.EcoursesOrder;
import cn.ecourses.pojo.EcoursesOrderItem;
import cn.ecourses.pojo.EcoursesOrderShipping;

public class OrderInfo extends EcoursesOrder implements Serializable {

	private List<EcoursesOrderItem> orderItems;
	private EcoursesOrderShipping orderShipping;
	public List<EcoursesOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<EcoursesOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public EcoursesOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(EcoursesOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}
