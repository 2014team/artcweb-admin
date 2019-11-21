
package com.artcweb.service;

import com.artcweb.baen.LayUiResult;
import com.artcweb.baen.User;

public interface UserService extends BaseService<User, Integer> {

	/**
	* @Title: checkSaveParam
	* @Description: 参数验证
	* @param entity
	* @return
	*/
	public String checkSaveParam(User entity);

	/**
	* @Title: checkUpdateUnique
	* @Description: 更新唯一性验证
	* @param entity
	* @return
	*/
	public String checkUpdateUnique(User entity);

	/**
	* @Title: checkAddUnique
	* @Description: 保存唯一性验证
	* @param entity
	* @return
	*/
	public String checkAddUnique(User entity);

	/**
	* @Title: findByPage
	* @Description: 分页查找
	* @param entity
	* @param result
	* @return
	*/
	public LayUiResult findByPage(User entity, LayUiResult result);

	/**
	* @Title: deleteByBatch
	* @Description: 批量删除
	* @param array
	* @return
	*/
	public int deleteByBatch(String array);
}
