package cn.ecourses.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ecourses.common.jedis.JedisClient;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.common.utils.JsonUtils;
import cn.ecourses.pojo.EcoursesAdmin;
import cn.ecourses.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	
	@Override
	public ECoursesResult getAdminByToken(String token) {
		
		//根据token到redis中取用户信息
		String json = jedisClient.get("ADMINSESSION:" + token);
		//取不到用户信息，登录已经过期，返回登录过期
		if (StringUtils.isBlank(json)) {
			return ECoursesResult.build(201, "用户登录已经过期");
		}
		//取到用户信息更新token的过期时间
		jedisClient.expire("ADMINSESSION:" + token, SESSION_EXPIRE);
		//返回结果，ECoursesResult其中包含EcoursesUser对象
		EcoursesAdmin admin = JsonUtils.jsonToPojo(json, EcoursesAdmin.class);
		return ECoursesResult.ok(admin);
	}

}
