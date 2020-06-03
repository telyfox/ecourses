package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesOrderShipping;
import cn.ecourses.pojo.EcoursesOrderShippingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesOrderShippingMapper {
    int countByExample(EcoursesOrderShippingExample example);

    int deleteByExample(EcoursesOrderShippingExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(EcoursesOrderShipping record);

    int insertSelective(EcoursesOrderShipping record);

    List<EcoursesOrderShipping> selectByExample(EcoursesOrderShippingExample example);

    EcoursesOrderShipping selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") EcoursesOrderShipping record, @Param("example") EcoursesOrderShippingExample example);

    int updateByExample(@Param("record") EcoursesOrderShipping record, @Param("example") EcoursesOrderShippingExample example);

    int updateByPrimaryKeySelective(EcoursesOrderShipping record);

    int updateByPrimaryKey(EcoursesOrderShipping record);
}