package cn.ecourses.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ecourses.common.jedis.JedisClient;
import cn.ecourses.common.utils.JsonUtils;
import cn.ecourses.mapper.EcoursesItemDescMapper;
import cn.ecourses.pojo.EcoursesItemDesc;
import cn.ecourses.service.ItemDescService;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_PRE}")
	private String REDIS_ITEM_PRE;
	@Autowired
	private EcoursesItemDescMapper itemDescMapper;
	@Value("${ITEM_CACHE_EXPIRE}")
	private Integer ITEM_CACHE_EXPIRE;
	
	@Override
	public EcoursesItemDesc getItemDescById(long itemId) {
		//查询缓存
		try {
			String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":DESC");
			if(StringUtils.isNotBlank(json)) {
				EcoursesItemDesc ecoursesItemDesc = JsonUtils.jsonToPojo(json, EcoursesItemDesc.class);
				return ecoursesItemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		EcoursesItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		//把结果添加到缓存
		try {
			jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":DESC", JsonUtils.objectToJson(itemDesc));
			//设置过期时间
			jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":DESC", ITEM_CACHE_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}

}
