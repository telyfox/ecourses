package cn.ecourses.mapper;

import cn.ecourses.pojo.EcoursesAdmin;
import cn.ecourses.pojo.EcoursesAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EcoursesAdminMapper {
    int countByExample(EcoursesAdminExample example);

    int deleteByExample(EcoursesAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EcoursesAdmin record);

    int insertSelective(EcoursesAdmin record);

    List<EcoursesAdmin> selectByExample(EcoursesAdminExample example);

    EcoursesAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EcoursesAdmin record, @Param("example") EcoursesAdminExample example);

    int updateByExample(@Param("record") EcoursesAdmin record, @Param("example") EcoursesAdminExample example);

    int updateByPrimaryKeySelective(EcoursesAdmin record);

    int updateByPrimaryKey(EcoursesAdmin record);
}