package cn.ecourses.cart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ecourses.cart.service.CartService;
import cn.ecourses.common.jedis.JedisClient;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.common.utils.JsonUtils;
import cn.ecourses.mapper.EcoursesItemMapper;
import cn.ecourses.pojo.EcoursesItem;

//购物车处理服务

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_CART_PRE}")
	private String REDIS_CART_PRE;
	@Autowired
	private EcoursesItemMapper itemMapper;
	
	@Override
	public ECoursesResult addCart(long userId, long itemId, int num) {
		//向redis中添加购物车。
		//数据类型是hash key：用户id field：课程id value：课程信息
		//判断课程是否存在
		Boolean hexists = jedisClient.hexists(REDIS_CART_PRE + ":" + userId, itemId + "");
		//如果存在数量相加
		if (hexists) {
			String json = jedisClient.hget(REDIS_CART_PRE + ":" + userId, itemId + "");
			//把json转换成EcoursesItem
			EcoursesItem item = JsonUtils.jsonToPojo(json, EcoursesItem.class);
			item.setNum(item.getNum() + num);
			//写回redis
			jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "", JsonUtils.objectToJson(item));
			return ECoursesResult.ok();
		}
		//如果不存在，根据课程id取课程信息
		EcoursesItem item = itemMapper.selectByPrimaryKey(itemId);
		//设置购物车数据量
		item.setNum(num);
		//取一张图片
		String image = item.getImage();
		if (StringUtils.isNotBlank(image)) {
			item.setImage(image.split(",")[0]);
		}
		//添加到购物车列表
		jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "", JsonUtils.objectToJson(item));
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult mergeCart(long userId, List<EcoursesItem> itemList) {
		//遍历课程列表
		//把列表添加到购物车。
		//判断购物车中是否有此课程
		//如果有，数量相加
		//如果没有添加新的课程
		for (EcoursesItem ecoursesItem : itemList) {
			addCart(userId, ecoursesItem.getId(), ecoursesItem.getNum());
		}
		//返回成功
		return ECoursesResult.ok();
	}

	@Override
	public List<EcoursesItem> getCartList(long userId) {
		//根据用户id查询购车列表
		List<String> jsonList = jedisClient.hvals(REDIS_CART_PRE + ":" + userId);
		List<EcoursesItem> itemList = new ArrayList<>();
		for (String string : jsonList) {
			//创建一个EcoursesItem对象
			EcoursesItem item = JsonUtils.jsonToPojo(string, EcoursesItem.class);
			//添加到列表
			itemList.add(item);
		}
		return itemList;
	}

	@Override
	public ECoursesResult updateCartNum(long userId, long itemId, int num) {
		//从redis中取课程信息
		String json = jedisClient.hget(REDIS_CART_PRE + ":" + userId, itemId + "");
		//更新课程数量
		EcoursesItem ecoursesItem = JsonUtils.jsonToPojo(json, EcoursesItem.class);
		ecoursesItem.setNum(num);
		//写入redis
		jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "", JsonUtils.objectToJson(ecoursesItem));
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult deleteCartItem(long userId, long itemId) {
		// 删除购物车课程
		jedisClient.hdel(REDIS_CART_PRE + ":" + userId, itemId + "");
		return ECoursesResult.ok();
	}
	
	@Override
	public ECoursesResult clearCartItem(long userId) {
		//删除购物车信息
		jedisClient.del(REDIS_CART_PRE + ":" + userId);
		return ECoursesResult.ok();
	}

}
