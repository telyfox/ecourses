package cn.ecourses.service;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesUser;

public interface UserService {
	ECoursesResult checkData(String param, int type);
	EasyUIDataGridResult getUserList(int page, int rows);
	EcoursesUser getUserById(long id);
	ECoursesResult deleteUserByIds(String ids);
	ECoursesResult insertUser(EcoursesUser user);
	ECoursesResult updateByPrimaryKey(EcoursesUser user);
}
