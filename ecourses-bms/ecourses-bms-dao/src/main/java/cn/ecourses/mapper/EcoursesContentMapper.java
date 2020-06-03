package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesContent;
import cn.ecourses.pojo.EcoursesContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesContentMapper {
    int countByExample(EcoursesContentExample example);

    int deleteByExample(EcoursesContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcoursesContent record);

    int insertSelective(EcoursesContent record);

    List<EcoursesContent> selectByExampleWithBLOBs(EcoursesContentExample example);

    List<EcoursesContent> selectByExample(EcoursesContentExample example);

    EcoursesContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcoursesContent record, @Param("example") EcoursesContentExample example);

    int updateByExampleWithBLOBs(@Param("record") EcoursesContent record, @Param("example") EcoursesContentExample example);

    int updateByExample(@Param("record") EcoursesContent record, @Param("example") EcoursesContentExample example);

    int updateByPrimaryKeySelective(EcoursesContent record);

    int updateByPrimaryKeyWithBLOBs(EcoursesContent record);

    int updateByPrimaryKey(EcoursesContent record);
}