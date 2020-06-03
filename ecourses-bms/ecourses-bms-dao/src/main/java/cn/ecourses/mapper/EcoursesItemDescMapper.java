package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesItemDesc;
import cn.ecourses.pojo.EcoursesItemDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesItemDescMapper {
    int countByExample(EcoursesItemDescExample example);

    int deleteByExample(EcoursesItemDescExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(EcoursesItemDesc record);

    int insertSelective(EcoursesItemDesc record);

    List<EcoursesItemDesc> selectByExampleWithBLOBs(EcoursesItemDescExample example);

    List<EcoursesItemDesc> selectByExample(EcoursesItemDescExample example);

    EcoursesItemDesc selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") EcoursesItemDesc record, @Param("example") EcoursesItemDescExample example);

    int updateByExampleWithBLOBs(@Param("record") EcoursesItemDesc record, @Param("example") EcoursesItemDescExample example);

    int updateByExample(@Param("record") EcoursesItemDesc record, @Param("example") EcoursesItemDescExample example);

    int updateByPrimaryKeySelective(EcoursesItemDesc record);

    int updateByPrimaryKeyWithBLOBs(EcoursesItemDesc record);

    int updateByPrimaryKey(EcoursesItemDesc record);
}