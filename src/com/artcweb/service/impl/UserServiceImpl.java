package com.artcweb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artcweb.baen.User;
import com.artcweb.dao.UserDao;
import com.artcweb.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements  UserService{
	
	@Autowired
	private UserDao userDao;
	

	/**
	* @Title: checkLoginParam
	* @Description: 验证登录参数
	* @param user
	* @return
	*/
	@Override
	public String checkLoginParam(User user) {
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
	public User login(User user) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userName", user.getUserName());
		paramMap.put("password", user.getPassword());
		return userDao.get(paramMap);
		 
	}

}
