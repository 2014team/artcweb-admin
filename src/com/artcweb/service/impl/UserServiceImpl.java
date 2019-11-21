
package com.artcweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artcweb.baen.LayUiResult;
import com.artcweb.baen.User;
import com.artcweb.dao.UserDao;
import com.artcweb.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {
	@Autowired
	private UserDao  userDao;
	
	/**
	* @Title: checkSaveParam
	* @Description: 参数验证
	* @param entity
	* @return
	*/
	@Override
	public String checkSaveParam(User entity) {
		String mobile = entity.getMobile();
		if(StringUtils.isBlank(mobile)){
			return "参数[mobile]不能为空!";
		}
		Integer sort = entity.getSort();
		if(null == sort){
			return "参数[sort]不能为空!";
		}
		return null;
	}

	/**
	* @Title: checkUpdateUnique
	* @Description: 更新唯一性验证
	* @param entity
	* @return
	*/
	@Override
	public String checkUpdateUnique(User entity) {
		List<User> list = null;
		// 分类ID与分类名唯一性验证
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", entity.getId());
		paramMap.put("mobile", entity.getMobile());
		list = userDao.checkUnique(paramMap);
		if (null != list && list.size() > 0) {
			return "手机号码已存在!";
		}
		return null;
	}


	/**
	* @Title: checkAddUnique
	* @Description: 保存唯一性验证
	* @param entity
	* @return
	*/
	@Override
	public String checkAddUnique(User entity) {
		List<User> list = null;
		// 分类ID与分类名唯一性验证
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("mobile", entity.getMobile());
		list = userDao.checkUnique(paramMap);
		if (null != list && list.size() > 0) {
			return "手机号码已存在!";
		}
		return null;
	}

	/**
	* @Title: findByPage
	* @Description: 分页查找
	* @param entity
	* @param result
	* @return
	*/
	@Override
	public LayUiResult findByPage(User entity, LayUiResult result) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("entity", entity);
		paramMap.put("page", result);
		int count = findByPageCount(paramMap);
		if (count > 0) {
			List<User> dataList = findByPage(paramMap);
			result.setData(dataList);
			result.setCount(count);
		}
		return result;
	}

	/**
	* @Title: deleteByBatch
	* @Description: 批量删除
	* @param array
	* @return
	*/
	@Override
	public int deleteByBatch(String array) {
		return userDao.deleteByBatch(array);
	}

}
