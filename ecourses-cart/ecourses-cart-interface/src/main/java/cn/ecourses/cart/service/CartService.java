package cn.ecourses.cart.service;

import java.util.List;

import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesItem;

public interface CartService {

	ECoursesResult addCart(long userId, long itemId, int num);
	ECoursesResult mergeCart(long userId, List<EcoursesItem> itemList);
	List<EcoursesItem> getCartList(long userId);
	ECoursesResult updateCartNum(long userId, long itemId, int num);
	ECoursesResult deleteCartItem(long userId, long itemId);
	ECoursesResult clearCartItem(long userId);
}
