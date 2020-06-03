package cn.ecourses.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ecourses.common.pojo.SearchItem;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.search.mapper.ItemMapper;
import cn.ecourses.search.service.SearchItemService;


//索引库维护Service
@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public ECoursesResult importAllItems() {
		try {
			//查询课程列表
			List<SearchItem> itemList = itemMapper.getItemList();
			//遍历课程列表
			for (SearchItem searchItem : itemList) {
				//创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				//向文档对象中添加域
				document.addField("id", searchItem.getId());
				document.addField("item_title", searchItem.getTitle());
				document.addField("item_sell_point", searchItem.getSell_point());
				document.addField("item_price", searchItem.getPrice());
				document.addField("item_image", searchItem.getImage());
				document.addField("item_category_name", searchItem.getCategory_name());
				//把文档对象写入索引库
				solrServer.add(document);
			}
			//提交
			solrServer.commit();
			//返回导入成功
			return ECoursesResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ECoursesResult.build(500, "数据导入时发生异常");
					
		}
	}

}
