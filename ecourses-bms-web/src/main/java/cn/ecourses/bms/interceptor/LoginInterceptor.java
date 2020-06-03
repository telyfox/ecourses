package cn.ecourses.bms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.ecourses.common.utils.CookieUtils;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.pojo.EcoursesAdmin;
import cn.ecourses.service.TokenService;

public class LoginInterceptor implements HandlerInterceptor{

	@Value("${LOGIN_URL}")
	private String LOGIN_URL;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//从cookie中取token
		String token = CookieUtils.getCookieValue(request, "token");
		//判断token是否存在
		if (StringUtils.isBlank(token)) {
			//如果token不存在，未登录状态，跳转到登录页面。用户登录成功后，跳转到当前请求的url
			response.sendRedirect(LOGIN_URL + "/page/login?redirect=" + request.getRequestURL());
			//拦截
			return false;
		}
		//如果token存在，需要调用sso系统的服务，根据token取用户信息
		ECoursesResult ecoursesResult = tokenService.getAdminByToken(token);
		//如果取不到，用户登录已经过期，需要登录。
		if (ecoursesResult.getStatus() != 200) {
			//如果token不存在，未登录状态，跳转到sso系统的登录页面。用户登录成功后，跳转到当前请求的url
			response.sendRedirect(LOGIN_URL + "/page/login?redirect=" + request.getRequestURL());
			//拦截
			return false;
		}
		//如果取到用户信息，是登录状态，需要把用户信息写入request。
		EcoursesAdmin admin = (EcoursesAdmin) ecoursesResult.getData();
		request.setAttribute("admin", admin);
		
		return true;
	}

}
