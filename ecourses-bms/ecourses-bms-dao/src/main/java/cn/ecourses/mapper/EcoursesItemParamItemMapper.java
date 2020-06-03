package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesItemParamItem;
import cn.ecourses.pojo.EcoursesItemParamItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesItemParamItemMapper {
    int countByExample(EcoursesItemParamItemExample example);

    int deleteByExample(EcoursesItemParamItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcoursesItemParamItem record);

    int insertSelective(EcoursesItemParamItem record);

    List<EcoursesItemParamItem> selectByExampleWithBLOBs(EcoursesItemParamItemExample example);

    List<EcoursesItemParamItem> selectByExample(EcoursesItemParamItemExample example);

    EcoursesItemParamItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcoursesItemParamItem record, @Param("example") EcoursesItemParamItemExample example);

    int updateByExampleWithBLOBs(@Param("record") EcoursesItemParamItem record, @Param("example") EcoursesItemParamItemExample example);

    int updateByExample(@Param("record") EcoursesItemParamItem record, @Param("example") EcoursesItemParamItemExample example);

    int updateByPrimaryKeySelective(EcoursesItemParamItem record);

    int updateByPrimaryKeyWithBLOBs(EcoursesItemParamItem record);

    int updateByPrimaryKey(EcoursesItemParamItem record);
}