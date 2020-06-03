package cn.ecourses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesOrder;
import cn.ecourses.service.BmsOrderListService;

@Controller
public class BmsOrderListController {

	@Autowired
	private BmsOrderListService bmsOrderListService;
	
	@RequestMapping("/bms/order/list")
	@ResponseBody
	public EasyUIDataGridResult getBmsOrderList(Integer page, Integer rows) {
		//调用服务查询订单列表
		EasyUIDataGridResult result = bmsOrderListService.getBmsOrderList(page, rows);
		return result;
	}
	
	@RequestMapping("/bms/order/delete")
	@ResponseBody
	public ECoursesResult deleteBmsOrderList(@RequestParam("ids") String ids) {
	    ECoursesResult result = bmsOrderListService.deleteBmsOrderListByOrderIds(ids);
	    return result;
	}
	
	@RequestMapping(value="/bms/order/delivery", method=RequestMethod.POST)
	@ResponseBody
	public ECoursesResult deliveryOrder(EcoursesOrder order) {
		ECoursesResult result = bmsOrderListService.deliveryOrder(order);
		return result;
	}
	
	@RequestMapping("/bms/order/stop/delivery")
	@ResponseBody
	public ECoursesResult stopDeliveryOrder(@RequestParam("ids") String ids) {
	    ECoursesResult result = bmsOrderListService.stopDeliveryOrder(ids);
	    return result;
	}
	
	@RequestMapping("/bms/order/complete")
	@ResponseBody
	public ECoursesResult completeOrder(@RequestParam("ids") String ids) {
	    ECoursesResult result = bmsOrderListService.completeOrder(ids);
	    return result;
	}
	
	@RequestMapping("/bms/order/close")
	@ResponseBody
	public ECoursesResult closeOrder(@RequestParam("ids") String ids) {
	    ECoursesResult result = bmsOrderListService.closeOrder(ids);
	    return result;
	}
	
	
}
