package com.artcweb.service;

import com.artcweb.baen.AdminUser;

public interface AdminUserService extends BaseService<AdminUser, Integer> {

	/**
	* @Title: checkLoginParam
	* @Description: 验证登录参数
	* @param user
	* @return
	*/
	public String checkLoginParam(AdminUser user);

	/**
	* @Title: login
	* @Description: 登录
	* @param user
	* @return
	*/
	public AdminUser login(AdminUser user);
	
	
	

}
