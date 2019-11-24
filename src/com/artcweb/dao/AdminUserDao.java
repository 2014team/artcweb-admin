package com.artcweb.dao;

import org.springframework.stereotype.Repository;

import com.artcweb.baen.AdminUser;

@Repository
public interface AdminUserDao extends BaseDao<AdminUser, Integer> {

	public int deleteByBatch(String array);
	public AdminUser getById(Integer id);


}