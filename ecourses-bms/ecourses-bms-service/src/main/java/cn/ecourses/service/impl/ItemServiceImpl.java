package cn.ecourses.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ecourses.common.jedis.JedisClient;
import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.common.utils.IDUtils;
import cn.ecourses.common.utils.JsonUtils;
import cn.ecourses.mapper.EcoursesItemDescMapper;
import cn.ecourses.mapper.EcoursesItemMapper;
import cn.ecourses.pojo.EcoursesItem;
import cn.ecourses.pojo.EcoursesItemDesc;
import cn.ecourses.pojo.EcoursesItemExample;
import cn.ecourses.pojo.EcoursesItemExample.Criteria;
import cn.ecourses.service.ItemService;


//管理Service

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private EcoursesItemMapper itemMapper;
	@Autowired
	private EcoursesItemDescMapper itemDescMapper;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination topicDestination;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_ITEM_PRE}")
	private String REDIS_ITEM_PRE;
	@Value("${ITEM_CACHE_EXPIRE}")
	private Integer ITEM_CACHE_EXPIRE;
	
	
	@Override
	public EcoursesItem getItemById(long itemId) {
		//查询缓存
		try {
			String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":BASE");
			if(StringUtils.isNotBlank(json)) {
				EcoursesItem ecoursesItem = JsonUtils.jsonToPojo(json, EcoursesItem.class);
				return ecoursesItem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//缓存中没有，查询数据库
		//根据主键查询
		//EcoursesItem ecoursesItem = itemMapper.selectByPrimaryKey(itemId);
		EcoursesItemExample example = new EcoursesItemExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andIdEqualTo(itemId);
		//执行查询
		List<EcoursesItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			//把结果添加到缓存
			try {
				jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":BASE", JsonUtils.objectToJson(list.get(0)));
				//设置过期时间
				jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":BASE", ITEM_CACHE_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list.get(0);
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		EcoursesItemExample example = new EcoursesItemExample();
		List<EcoursesItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<EcoursesItem> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public ECoursesResult addItem(EcoursesItem item, String desc) {
		//生成课程id
		final long itemId = IDUtils.genItemId();
		//补全item的属性
		item.setId(itemId);
		//1-正常，2-下架，3-删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//向课程表插入数据
		itemMapper.insert(item);
		//创建一个课程描述表对应的pojo对象。
		EcoursesItemDesc itemDesc = new EcoursesItemDesc();
		//补全属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		//向课程描述表插入数据
		itemDescMapper.insert(itemDesc);
		//发送课程添加消息
		jmsTemplate.send(topicDestination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(itemId + "");
				return textMessage;
			}
		});
		
		//返回成功
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult deleteItemByIds(String ids) {
		String[] idArray = ids.split(","); 
		for (String id : idArray) {  
			itemMapper.deleteByPrimaryKey(Long.valueOf(id));  
        }  
		return ECoursesResult.ok();
	}

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

	@Override
	public ECoursesResult updateItem(EcoursesItem item, String desc) {
		item.setUpdated(new Date());
		//向课程表插入数据
		itemMapper.updateByPrimaryKeySelective(item);
		//创建一个课程描述表对应的pojo对象。
		EcoursesItemDesc itemDesc = new EcoursesItemDesc();
		//补全属性
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		//向课程描述表插入数据
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		
		//返回成功
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult instockItemByIds(String ids) {
		String[] idArray = ids.split(","); 
		EcoursesItem item = new EcoursesItem();
		for (String id : idArray) {  
			item.setId(Long.valueOf(id));
			item.setStatus((byte) 2);
			itemMapper.updateByPrimaryKeySelective(item);  
        }  
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult reshelfItemByIds(String ids) {
		String[] idArray = ids.split(","); 
		EcoursesItem item = new EcoursesItem();
		for (String id : idArray) {  
			item.setId(Long.valueOf(id));
			item.setStatus((byte) 1);
			itemMapper.updateByPrimaryKeySelective(item);  
        }  
		return ECoursesResult.ok();
	}


}
