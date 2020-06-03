package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesOrderItem;
import cn.ecourses.pojo.EcoursesOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesOrderItemMapper {
    int countByExample(EcoursesOrderItemExample example);

    int deleteByExample(EcoursesOrderItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(EcoursesOrderItem record);

    int insertSelective(EcoursesOrderItem record);

    List<EcoursesOrderItem> selectByExample(EcoursesOrderItemExample example);

    EcoursesOrderItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EcoursesOrderItem record, @Param("example") EcoursesOrderItemExample example);

    int updateByExample(@Param("record") EcoursesOrderItem record, @Param("example") EcoursesOrderItemExample example);

    int updateByPrimaryKeySelective(EcoursesOrderItem record);

    int updateByPrimaryKey(EcoursesOrderItem record);
}