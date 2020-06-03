package cn.ecourses.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ecourses.common.pojo.EasyUIDataGridResult;
import cn.ecourses.common.utils.ECoursesResult;
import cn.ecourses.common.utils.IDUtils;
import cn.ecourses.mapper.EcoursesUserMapper;
import cn.ecourses.pojo.EcoursesUser;
import cn.ecourses.pojo.EcoursesUserExample;
import cn.ecourses.pojo.EcoursesUserExample.Criteria;
import cn.ecourses.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private EcoursesUserMapper userMapper;
	
	@Override
	public EasyUIDataGridResult getUserList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		EcoursesUserExample example = new EcoursesUserExample();
		List<EcoursesUser> list = userMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<EcoursesUser> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public EcoursesUser getUserById(long id) {
		EcoursesUserExample example = new EcoursesUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<EcoursesUser> list = userMapper.selectByExample(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			EcoursesUser user = list.get(0);
			return user;
		}
		return null;
	}

	@Override
	public ECoursesResult deleteUserByIds(String ids) {
		String[] idArray = ids.split(","); 
		for (String id : idArray) {  
			userMapper.deleteByPrimaryKey(Long.valueOf(id));
		}  
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult insertUser(EcoursesUser user) {
		//数据有效性校验
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) 
				|| StringUtils.isBlank(user.getPhone())) {
			return ECoursesResult.build(400, "用户数据不完整，添加失败");
		}
		ECoursesResult result = checkData(user.getUsername(), 1);
		if (!(boolean) result.getData()) {
			return ECoursesResult.build(400, "此用户名已经被占用");
		}
		//生成userId
		final long userId = IDUtils.genUserId();
		user.setId(userId);
		//对密码进行md5加密
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		//补全pojo
		user.setCreated(new Date());
		user.setUpdated(new Date());
		//插入到规格参数模板表
		userMapper.insert(user);
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult checkData(String param, int type) {
		//根据不同的type生成不同的查询条件
		EcoursesUserExample example = new EcoursesUserExample();
		Criteria criteria = example.createCriteria();
		//1：用户名 2：手机号 3：邮箱
		if (type == 1) {
			criteria.andUsernameEqualTo(param);
		} else if (type == 2) {
			criteria.andPhoneEqualTo(param);
		} else if (type == 3) {
			criteria.andEmailEqualTo(param);
		} else {
			return ECoursesResult.build(400, "数据类型错误");
		}
		//执行查询
		List<EcoursesUser> list = userMapper.selectByExample(example);
		//判断结果中是否包含数据
		if (list != null && list.size()>0) {
			//如果有数据返回false
			return ECoursesResult.ok(false);
		}
		//如果没有数据返回true
		return ECoursesResult.ok(true);
	}

	@Override
	public ECoursesResult updateByPrimaryKey(EcoursesUser user) {
		//数据有效性校验
//		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) 
//				|| StringUtils.isBlank(user.getPhone())) {
//			return ECoursesResult.build(400, "用户数据不完整，注册失败");
//		}
		System.out.println(user.getUsername());
		//对密码进行md5加密
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		//补全pojo
		user.setUpdated(new Date());
		//插入到规格参数模板表
		userMapper.updateByPrimaryKeySelective(user);
		return ECoursesResult.ok();
	}

	

}
