package cn.ecourses.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ecourses.cart.service.CartService;
import cn.ecourses.common.utils.CookieUtils;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.common.utils.JsonUtils;
import cn.ecourses.pojo.EcoursesItem;
import cn.ecourses.pojo.EcoursesUser;
import cn.ecourses.service.ItemService;

//购物车处理Controller

@Controller
public class CartController {
	
	@Value("${COOKIE_CART_EXPIRE}")
	private Integer COOKIE_CART_EXPIRE;
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private CartService cartService;

	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue="1")Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		//判断用户是否登录
		EcoursesUser user = (EcoursesUser) request.getAttribute("user");
		//如果是登录状态，把购物车写入redis
		if (user != null) {
			//保存到服务端
			cartService.addCart(user.getId(), itemId, num);
			//返回逻辑视图
			return "cartSuccess";
		}
		//如果未登录使用cookie
		//从cookie中取购物车列表
		List<EcoursesItem> cartList = getCartListFromCookie(request);
		//判断课程在课程列表中是否存在
		boolean flag = false;
		for (EcoursesItem ecoursesItem : cartList) {
			//如果存在数量相加
			if (ecoursesItem.getId() == itemId.longValue()) {
				flag = true;
				//找到课程，数量相加
				ecoursesItem.setNum(ecoursesItem.getNum() + num);
				//跳出循环
				break;
			}
		}
		//如果不存在
		if (!flag) {
			//根据课程id查询课程信息。得到一个EcoursesItem对象
			EcoursesItem ecoursesItem = itemService.getItemById(itemId);
			//设置课程数量
			ecoursesItem.setNum(num);
			//取一张图片
			String image = ecoursesItem.getImage();
			if (StringUtils.isNotBlank(image)) {
				ecoursesItem.setImage(image.split(",")[0]);
			}
			//把课程添加到课程列表
			cartList.add(ecoursesItem);
		}
		//写入cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);
		//返回添加成功页面
		return "cartSuccess";
	}
	
	//从cookie中取购物车列表的处理

	private List<EcoursesItem> getCartListFromCookie(HttpServletRequest request) {
		String json = CookieUtils.getCookieValue(request, "cart", true);
		//判断json是否为空
		if (StringUtils.isBlank(json)) {
			return new ArrayList<>();
		}
		//把json转换成课程列表
		List<EcoursesItem> list = JsonUtils.jsonToList(json, EcoursesItem.class);
		return list;
	}
	
	//展示购物车列表

	@RequestMapping("/cart/cart")
	public String showCatList(HttpServletRequest request, HttpServletResponse response) {
		//从cookie中取购物车列表
		List<EcoursesItem> cartList = getCartListFromCookie(request);
		//判断用户是否为登录状态
		EcoursesUser user = (EcoursesUser) request.getAttribute("user");
		//如果是登录状态
		if (user != null) {
			//从cookie中取购物车列表
			//如果不为空，把cookie中的购物车课程和服务端的购物车课程合并。
			cartService.mergeCart(user.getId(), cartList);
			//把cookie中的购物车删除
			CookieUtils.deleteCookie(request, response, "cart");
			//从服务端取购物车列表
			cartList = cartService.getCartList(user.getId());
			
		}
		//把列表传递给页面
		request.setAttribute("cartList", cartList);
		//返回逻辑视图
		return "cart";
	}
	
	//更新购物车课程数量
	 
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public ECoursesResult updateCartNum(@PathVariable Long itemId, @PathVariable Integer num
			, HttpServletRequest request ,HttpServletResponse response) {
		//判断用户是否为登录状态
		EcoursesUser user = (EcoursesUser) request.getAttribute("user");
		if (user != null) {
			cartService.updateCartNum(user.getId(), itemId, num);
			return ECoursesResult.ok();
		}
		//从cookie中取购物车列表
		List<EcoursesItem> cartList = getCartListFromCookie(request);
		//遍历课程列表找到对应的课程
		for (EcoursesItem ecoursesItem : cartList) {
			if (ecoursesItem.getId().longValue() == itemId) {
				//更新数量
				ecoursesItem.setNum(num);
				break;
			}
		}
		//把购物车列表写回cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);
		//返回成功
		return ECoursesResult.ok();
	}
	
	//删除购物车课程
	 
	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request,
			HttpServletResponse response) {
		//判断用户是否为登录状态
		EcoursesUser user = (EcoursesUser) request.getAttribute("user");
		if (user != null) {
			cartService.deleteCartItem(user.getId(), itemId);
			return "redirect:/cart/cart.html";
		}
		//从cookie中取购物车列表
		List<EcoursesItem> cartList = getCartListFromCookie(request);
		//遍历列表，找到要删除的课程
		for (EcoursesItem ecoursesItem : cartList) {
			if (ecoursesItem.getId().longValue() == itemId) {
				//删除课程
				cartList.remove(ecoursesItem);
				//跳出循环
				break;
			}
		}
		//把购物车列表写入cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList), COOKIE_CART_EXPIRE, true);
		//返回逻辑视图
		return "redirect:/cart/cart.html";
	}
}
