package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesItemParam;
import cn.ecourses.pojo.EcoursesItemParamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesItemParamMapper {
    int countByExample(EcoursesItemParamExample example);

    int deleteByExample(EcoursesItemParamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcoursesItemParam record);

    int insertSelective(EcoursesItemParam record);

    List<EcoursesItemParam> selectByExampleWithBLOBs(EcoursesItemParamExample example);

    List<EcoursesItemParam> selectByExample(EcoursesItemParamExample example);

    EcoursesItemParam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcoursesItemParam record, @Param("example") EcoursesItemParamExample example);

    int updateByExampleWithBLOBs(@Param("record") EcoursesItemParam record, @Param("example") EcoursesItemParamExample example);

    int updateByExample(@Param("record") EcoursesItemParam record, @Param("example") EcoursesItemParamExample example);

    int updateByPrimaryKeySelective(EcoursesItemParam record);

    int updateByPrimaryKeyWithBLOBs(EcoursesItemParam record);

    int updateByPrimaryKey(EcoursesItemParam record);
}