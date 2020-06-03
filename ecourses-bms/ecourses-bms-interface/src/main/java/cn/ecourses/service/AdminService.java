package cn.ecourses.service;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesAdmin;

public interface AdminService {
	EasyUIDataGridResult getAdminList(int page, int rows);
	ECoursesResult checkData(String param, int type);
	EcoursesAdmin getAdminById(long id);
	ECoursesResult deleteAdminByIds(String ids);
	ECoursesResult insertAdmin(EcoursesAdmin admin);
	ECoursesResult updateAdmin(EcoursesAdmin admin);
}
