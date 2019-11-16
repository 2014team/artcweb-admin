package com.artcweb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artcweb.baen.AdminUser;
import com.artcweb.dao.AdminUserDao;
import com.artcweb.service.AdminUserService;

@Service
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUser, Integer> implements  AdminUserService{
	
	@Autowired
	private AdminUserDao userDao;
	

	/**
	* @Title: checkLoginParam
	* @Description: 验证登录参数
	* @param user
	* @return
	*/
	@Override
	public String checkLoginParam(AdminUser user) {
		String userName = user.getUserName();
		if(StringUtils.isBlank(userName)){
			return "用户名不能为空!";
		}
		String password = user.getPassword();
		if(StringUtils.isBlank(password)){
			return "密码不能为空!";
		}
		return null;
	}

	/**
	* @Title: login
	* @Description: 登录
	* @param user
	* @return
	*/
	@Override
	public AdminUser login(AdminUser user) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userName", user.getUserName());
		paramMap.put("password", user.getPassword());
		return userDao.get(paramMap);
		 
	}

}
