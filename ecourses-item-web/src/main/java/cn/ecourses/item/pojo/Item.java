package cn.ecourses.item.pojo;

import cn.ecourses.pojo.EcoursesItem;

public class Item extends EcoursesItem {
	
	public Item(EcoursesItem ecoursesItem) {
		this.setId(ecoursesItem.getId());
		this.setTitle(ecoursesItem.getTitle());
		this.setSellPoint(ecoursesItem.getSellPoint());
		this.setPrice(ecoursesItem.getPrice());
		this.setNum(ecoursesItem.getNum());
		this.setBarcode(ecoursesItem.getBarcode());
		this.setImage(ecoursesItem.getImage());
		this.setCid(ecoursesItem.getCid());
		this.setStatus(ecoursesItem.getStatus());
		this.setCreated(ecoursesItem.getCreated());
		this.setUpdated(ecoursesItem.getUpdated());
	}

	public String[] getImages() {
		String image2 = this.getImage();
		if (image2 != null && !"".equals(image2)) {
			String[] strings = image2.split(",");
			return strings;
		}
		return null;
	}
}
