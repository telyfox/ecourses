package cn.ecourses.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.ecourses.common.jedis.JedisClient;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.common.utils.JsonUtils;
import cn.ecourses.pojo.EcoursesUser;
import cn.ecourses.sso.service.TokenService;

//根据token取用户信息

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	
	@Override
	public ECoursesResult getUserByToken(String token) {
		//根据token到redis中取用户信息
		String json = jedisClient.get("SESSION:" + token);
		//取不到用户信息，登录已经过期，返回登录过期
		if (StringUtils.isBlank(json)) {
			return ECoursesResult.build(201, "用户登录已经过期");
		}
		//取到用户信息更新token的过期时间
		jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
		//返回结果，ECoursesResult其中包含EcoursesUser对象
		EcoursesUser user = JsonUtils.jsonToPojo(json, EcoursesUser.class);
		return ECoursesResult.ok(user);
	}

}
