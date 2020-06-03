package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesOrder;
import cn.ecourses.pojo.EcoursesOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesOrderMapper {
    int countByExample(EcoursesOrderExample example);

    int deleteByExample(EcoursesOrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(EcoursesOrder record);

    int insertSelective(EcoursesOrder record);

    List<EcoursesOrder> selectByExample(EcoursesOrderExample example);

    EcoursesOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") EcoursesOrder record, @Param("example") EcoursesOrderExample example);

    int updateByExample(@Param("record") EcoursesOrder record, @Param("example") EcoursesOrderExample example);

    int updateByPrimaryKeySelective(EcoursesOrder record);

    int updateByPrimaryKey(EcoursesOrder record);
}