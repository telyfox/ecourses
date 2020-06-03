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
import cn.ecourses.mapper.EcoursesAdminMapper;
import cn.ecourses.pojo.EcoursesAdmin;
import cn.ecourses.pojo.EcoursesAdminExample;
import cn.ecourses.pojo.EcoursesUser;
import cn.ecourses.pojo.EcoursesUserExample;
import cn.ecourses.pojo.EcoursesAdminExample.Criteria;
import cn.ecourses.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private EcoursesAdminMapper adminMapper;
	
	@Override
	public EasyUIDataGridResult getAdminList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		EcoursesAdminExample example = new EcoursesAdminExample();
		List<EcoursesAdmin> list = adminMapper.selectByExample(example);
		//创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		//取分页结果
		PageInfo<EcoursesAdmin> pageInfo = new PageInfo<>(list);
		//取总记录数
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	@Override
	public ECoursesResult checkData(String param, int type) {
		//根据不同的type生成不同的查询条件
		EcoursesAdminExample example = new EcoursesAdminExample();
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
		List<EcoursesAdmin> list = adminMapper.selectByExample(example);
		//判断结果中是否包含数据
		if (list != null && list.size()>0) {
			//如果有数据返回false
			return ECoursesResult.ok(false);
		}
		//如果没有数据返回true
		return ECoursesResult.ok(true);
	}

	@Override
	public EcoursesAdmin getAdminById(long id) {
		EcoursesAdminExample example = new EcoursesAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<EcoursesAdmin> list = adminMapper.selectByExample(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			EcoursesAdmin admin = list.get(0);
			return admin;
		}
		return null;
	}

	@Override
	public ECoursesResult deleteAdminByIds(String ids) {
		String[] idArray = ids.split(","); 
		for (String id : idArray) {  
			adminMapper.deleteByPrimaryKey(Long.valueOf(id));
		}  
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult insertAdmin(EcoursesAdmin admin) {
		//数据有效性校验
		if (StringUtils.isBlank(admin.getUsername()) || StringUtils.isBlank(admin.getPassword()) 
				|| StringUtils.isBlank(admin.getPhone())) {
			return ECoursesResult.build(400, "管理员数据不完整，添加失败");
		}
		ECoursesResult result = checkData(admin.getUsername(), 1);
		if (!(boolean) result.getData()) {
			return ECoursesResult.build(400, "此用户名已经被占用");
		}
		//生成userId
		long adminId = IDUtils.genUserId();
		admin.setId(adminId);
		//对密码进行md5加密
		String md5Pass = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
		admin.setPassword(md5Pass);
		//补全pojo
		admin.setCreated(new Date());
		admin.setUpdated(new Date());
		//插入到规格参数模板表
		adminMapper.insert(admin);
		return ECoursesResult.ok();
	}

	@Override
	public ECoursesResult updateAdmin(EcoursesAdmin admin) {
		System.out.println(admin.getUsername());
		//对密码进行md5加密
		String md5Pass = DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
		admin.setPassword(md5Pass);
		//补全pojo
		admin.setUpdated(new Date());
		//插入到规格参数模板表
		adminMapper.updateByPrimaryKeySelective(admin);
		return ECoursesResult.ok();
	}

}
