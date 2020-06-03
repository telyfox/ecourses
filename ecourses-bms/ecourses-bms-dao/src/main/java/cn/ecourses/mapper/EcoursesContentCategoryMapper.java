package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesContentCategory;
import cn.ecourses.pojo.EcoursesContentCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesContentCategoryMapper {
    int countByExample(EcoursesContentCategoryExample example);

    int deleteByExample(EcoursesContentCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcoursesContentCategory record);

    int insertSelective(EcoursesContentCategory record);

    List<EcoursesContentCategory> selectByExample(EcoursesContentCategoryExample example);

    EcoursesContentCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcoursesContentCategory record, @Param("example") EcoursesContentCategoryExample example);

    int updateByExample(@Param("record") EcoursesContentCategory record, @Param("example") EcoursesContentCategoryExample example);

    int updateByPrimaryKeySelective(EcoursesContentCategory record);

    int updateByPrimaryKey(EcoursesContentCategory record);
}