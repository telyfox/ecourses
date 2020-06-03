package cn.ecourses.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ecourses.common.utils.JsonUtils;
import cn.ecourses.mapper.EcoursesItemParamItemMapper;
import cn.ecourses.pojo.EcoursesItemParamItem;
import cn.ecourses.pojo.EcoursesItemParamItemExample;
import cn.ecourses.pojo.EcoursesItemParamItemExample.Criteria;
import cn.ecourses.service.ItemParamItemService;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

	@Autowired
	private EcoursesItemParamItemMapper itemParamItemMapper;
	
	@Override
	public String getItemParamByItemId(Long itemId) {
		//根据课程id查询规格参数
		EcoursesItemParamItemExample example = new EcoursesItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		//执行查询
		List<EcoursesItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.size() == 0) {
			return "";
		}
		//取规格参数信息
		EcoursesItemParamItem itemParamItem = list.get(0);
		String paramData = itemParamItem.getParamData();
		//生成html
		// 把规格参数json数据转换成java对象
		List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for(Map m1:jsonList) {
			sb.append("        <tr>\n");
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
			sb.append("        </tr>\n");
			List<Map> list2 = (List<Map>) m1.get("params");
			for(Map m2:list2) {
				sb.append("        <tr>\n");
				sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
				sb.append("            <td>"+m2.get("v")+"</td>\n");
				sb.append("        </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");
		return sb.toString();
	}

}
