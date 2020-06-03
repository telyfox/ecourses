package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesOrderBms;
import java.util.List;

public interface EcoursesOrderBmsMapper {
    int countOrderBmsList();

    List<EcoursesOrderBms> getOrderBmsList();

}