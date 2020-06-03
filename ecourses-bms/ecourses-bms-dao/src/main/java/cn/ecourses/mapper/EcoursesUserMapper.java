package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesUser;
import cn.ecourses.pojo.EcoursesUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesUserMapper {
    int countByExample(EcoursesUserExample example);

    int deleteByExample(EcoursesUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcoursesUser record);

    int insertSelective(EcoursesUser record);

    List<EcoursesUser> selectByExample(EcoursesUserExample example);

    EcoursesUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcoursesUser record, @Param("example") EcoursesUserExample example);

    int updateByExample(@Param("record") EcoursesUser record, @Param("example") EcoursesUserExample example);

    int updateByPrimaryKeySelective(EcoursesUser record);

    int updateByPrimaryKey(EcoursesUser record);
}