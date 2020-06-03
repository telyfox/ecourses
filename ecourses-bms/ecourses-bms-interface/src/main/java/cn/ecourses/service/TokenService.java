package cn.ecourses.service;

import cn.ecourses.common.utils.ECoursesResult;

public interface TokenService {
	ECoursesResult getAdminByToken(String token);
}
