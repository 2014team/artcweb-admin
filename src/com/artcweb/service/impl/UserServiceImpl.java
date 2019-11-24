
package com.artcweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artcweb.baen.LayUiResult;
import com.artcweb.baen.Order;
import com.artcweb.baen.User;
import com.artcweb.dao.OrderDao;
import com.artcweb.dao.UserDao;
import com.artcweb.service.UserService;
import com.artcweb.util.ImageUtil;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrderDao orderDao;

	/**
	 * @Title: checkSaveParam
	 * @Description: 参数验证
	 * @param entity
	 * @return
	 */
	@Override
	public String checkSaveParam(User entity) {

		String mobile = entity.getMobile();
		if (StringUtils.isBlank(mobile)) {
			return "参数[mobile]不能为空!";
		}
		Integer sort = entity.getSort();
		if (null == sort) {
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

			// 获取订单
			getOrderList(dataList);

			result.setData(dataList);
			result.setCount(count);
		}
		return result;
	}

	/**
	 * @Title: getOrderList
	 * @Description: 获取订单
	 * @param dataList
	 */
	private void getOrderList(List<User> dataList) {

		if (null == dataList || dataList.size() < 1) {
			return;
		}
		for (User user : dataList) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mobile", user.getMobile());
			List<Order> orderList = orderDao.select(paramMap);
			if (null != orderList && orderList.size() > 0) {
				for (Order order : orderList) {
					// 图片处理
					imageUrlDeal(order);
				}

				user.setOrderCount(orderList.size());
			}

			user.setOrderList(orderList);
		}
	}

	private void imageUrlDeal(Order order) {

		String imageUrl = ImageUtil.imageUrlDeal(order.getImageUrl());
		order.setImageUrl(imageUrl);
	}

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @param array
	 * @return
	 */
	@Override
	public int deleteByBatch(String array) {
		
		List<User> list = userDao.selectByBatch(array);
		int resilt = 0;
		if(null != list && list.size() > 0){
			resilt = userDao.deleteByBatch(array);
			for (User user : list) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("mobile", user.getMobile());
				orderDao.deleteByMap(paramMap);
			}
		}
		return resilt;
	}

	@Override
	public User getByMap(Map<String, Object> paramMap) {

		return userDao.getByMap(paramMap);
	}

	/**
	 * @Title: deleteUser
	 * @Description: 删除用户信息
	 */
	@Override
	public LayUiResult deleteUser(Integer id) {

		LayUiResult layUiResult = new LayUiResult();
		User user = userDao.get(id);
		if (null != user) {
			String mobile = user.getMobile();
			Integer result = userDao.delete(id);
			if (null != result && result > 0) {
				if (StringUtils.isNotBlank(mobile)) {
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("mobile", mobile);
					orderDao.deleteByMap(paramMap);
				}

				layUiResult.success();
				return layUiResult;

			}
		}

		layUiResult.failure();
		return layUiResult;

	}

	
	/**
	* @Title: updateUser
	* @Description: 修改用户信息
	* @param entity
	* @return
	*/
	@Override
	public Integer updateUser(User entity) {
		//获取用户信息
		User user = get(entity.getId());
		
		List<Order> orderList = null;
		if(null != user){
			String mobile = user.getMobile();
			//查询订单修改手机号
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("mobile", mobile);
			orderList = orderDao.selectByMap(paramMap);
			
		}
		
		Integer result = update(entity);
		if(null != result && result > 0){
			if(null != user){
				//查询订单修改手机号
				if(null != orderList && orderList.size() > 0){
					for (Order order : orderList) {
						order.setMobile(entity.getMobile());
						 orderDao.update(order);
					}
			}
		  }
		}
		return result;
	}

}
