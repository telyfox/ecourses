package cn.ecourses.sso.service;

import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesUser;

public interface RegisterService {

	ECoursesResult checkData(String param, int type);
	ECoursesResult register(EcoursesUser user);
}
