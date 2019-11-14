package com.artcweb.service;

import com.artcweb.baen.User;

public interface UserService extends BaseService<User, Integer> {

	/**
	* @Title: checkLoginParam
	* @Description: 验证登录参数
	* @param user
	* @return
	*/
	public String checkLoginParam(User user);

	/**
	* @Title: login
	* @Description: 登录
	* @param user
	* @return
	*/
	public User login(User user);
	
	
	

}
