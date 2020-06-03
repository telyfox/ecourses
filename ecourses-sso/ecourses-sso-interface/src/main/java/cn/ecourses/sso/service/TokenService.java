package cn.ecourses.sso.service;

import cn.ecourses.common.utils.ECoursesResult;

//根据token查询用户信息

public interface TokenService {

	ECoursesResult getUserByToken(String token);
}
