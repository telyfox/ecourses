package cn.ecourses.item.listener;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.ecourses.item.pojo.Item;
import cn.ecourses.pojo.EcoursesItem;
import cn.ecourses.pojo.EcoursesItemDesc;
import cn.ecourses.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;

//监听课程添加消息，生成对应的静态页面

public class HtmlGenListener implements MessageListener {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Value("${HTML_GEN_PATH}")
	private String HTML_GEN_PATH;

	@Override
	public void onMessage(Message message) {
		try {
			//创建一个模板，参考jsp
			//从消息中取id
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			Long itemId = new Long(text);
			//等待事务提交
			Thread.sleep(1000);
			//根据课程id查询信息，课程基本信息和描述。
			EcoursesItem ecoursesItem = itemService.getItemById(itemId);
			Item item = new Item(ecoursesItem);
			//取课程描述
			EcoursesItemDesc itemDesc = itemService.getItemDescById(itemId);
			//创建一个数据集，把课程数据封装
			Map data = new HashMap<>();
			data.put("item", item);
			data.put("itemDesc", itemDesc);
			//加载模板对象
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			//创建一个输出流，指定输出的目录及文件名。
			Writer out = new FileWriter(HTML_GEN_PATH + itemId + ".html");
			//生成静态页面。
			template.process(data, out);
			//关闭流
			out.close();
			
		} catch (Exception e) {
			
		}

	}

}
