package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesItemCat;
import cn.ecourses.pojo.EcoursesItemCatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesItemCatMapper {
    int countByExample(EcoursesItemCatExample example);

    int deleteByExample(EcoursesItemCatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcoursesItemCat record);

    int insertSelective(EcoursesItemCat record);

    List<EcoursesItemCat> selectByExample(EcoursesItemCatExample example);

    EcoursesItemCat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcoursesItemCat record, @Param("example") EcoursesItemCatExample example);

    int updateByExample(@Param("record") EcoursesItemCat record, @Param("example") EcoursesItemCatExample example);

    int updateByPrimaryKeySelective(EcoursesItemCat record);

    int updateByPrimaryKey(EcoursesItemCat record);
}