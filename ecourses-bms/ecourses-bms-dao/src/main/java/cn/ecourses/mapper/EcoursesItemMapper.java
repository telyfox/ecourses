package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesItem;
import cn.ecourses.pojo.EcoursesItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesItemMapper {
    int countByExample(EcoursesItemExample example);

    int deleteByExample(EcoursesItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcoursesItem record);

    int insertSelective(EcoursesItem record);

    List<EcoursesItem> selectByExample(EcoursesItemExample example);

    EcoursesItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcoursesItem record, @Param("example") EcoursesItemExample example);

    int updateByExample(@Param("record") EcoursesItem record, @Param("example") EcoursesItemExample example);

    int updateByPrimaryKeySelective(EcoursesItem record);

    int updateByPrimaryKey(EcoursesItem record);
}