package cn.ecourses.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ecourses.content.service.ContentService;
import cn.ecourses.pojo.EcoursesContent;

//首页展示Controller
@Controller
public class IndexController {

	@Value("${CONTENT_BIGAD_ID}")
	private Long CONTENT_BIGAD_ID;
	@Value("${CONTENT_MINIAD_ID}")
	private Long CONTENT_MINIAD_ID;
	@Value("${CONTENT_BESTUP_ID}")
	private Long CONTENT_BESTUP_ID;
	@Value("${CONTENT_BESTDOWN_ID}")
	private Long CONTENT_BESTDOWN_ID;
	@Value("${CONTENT_CHEAPLEFT_ID}")
	private Long CONTENT_CHEAPLEFT_ID;
	@Value("${CONTENT_CHEAPLIST_ID}")
	private Long CONTENT_CHEAPLIST_ID;
	@Value("${CONTENT_MINITITLELIST_ID}")
	private Long CONTENT_MINITITLELIST_ID;
	@Value("${CONTENT_NEWS_ID}")
	private Long CONTENT_NEWS_ID;
	
	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public String showIndex(Model model) {
		//查询内容列表
		List<EcoursesContent> bigAdList = contentService.getContentListByCid(CONTENT_BIGAD_ID);
		List<EcoursesContent> smallAdList = contentService.getContentListByCid(CONTENT_MINIAD_ID);
		List<EcoursesContent> bestUpList = contentService.getContentListByCid(CONTENT_BESTUP_ID);
		List<EcoursesContent> bestDownList = contentService.getContentListByCid(CONTENT_BESTDOWN_ID);
		List<EcoursesContent> cheapLeftList = contentService.getContentListByCid(CONTENT_CHEAPLEFT_ID);
		List<EcoursesContent> cheapList = contentService.getContentListByCid(CONTENT_CHEAPLIST_ID);
		List<EcoursesContent> miniTitleList = contentService.getContentListByCid(CONTENT_MINITITLELIST_ID);
		List<EcoursesContent> newsList = contentService.getContentListByCid(CONTENT_NEWS_ID);
		// 把结果传递给页面
		model.addAttribute("bigAdList", bigAdList);
		model.addAttribute("smallAdList", smallAdList);
		model.addAttribute("bestUpList", bestUpList);
		model.addAttribute("bestDownList", bestDownList);
		model.addAttribute("cheapLeftList", cheapLeftList);
		model.addAttribute("cheapList", cheapList);
		model.addAttribute("miniTitleList", miniTitleList);
		model.addAttribute("newsList", newsList);
		return "index";
	}
}
