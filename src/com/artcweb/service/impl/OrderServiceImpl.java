
package com.artcweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artcweb.baen.LayUiResult;
import com.artcweb.baen.Order;
import com.artcweb.dao.OrderDao;
import com.artcweb.service.OrderService;
import com.artcweb.util.ImageUtil;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Integer> implements OrderService {

	@Autowired
	private OrderDao orderDao;

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @param array
	 * @return
	 */
	@Override
	public int deleteByBatch(String array) {

		return orderDao.deleteByBatch(array);
	}

	/**
	 * @Title: findByPage
	 * @Description: 分页查找
	 * @param entity
	 * @param result
	 * @return
	 */
	@Override
	public LayUiResult findByPage(Order entity, LayUiResult result) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("entity", entity);
		paramMap.put("page", result);
		int count = findByPageCount(paramMap);
		if (count > 0) {
			List<Order> dataList = findByPage(paramMap);

			// 图片路径处理
			if (null != dataList && dataList.size() > 0) {
				for (Order order : dataList) {
					imageUrlDeal(order);
				}
			}

			result.success(dataList);
			result.setCount(count);
		}
		return result;
	}

	private void imageUrlDeal(Order entity) {

		if (null == entity) {
			return;
		}
		String imageUrl = ImageUtil.imageUrlDeal(entity.getImageUrl());
		entity.setImageUrl(imageUrl);

	}

	/**
	 * @Title: checkParamByApi
	 * @Description: 参数验证
	 * @param entity
	 * @return
	 */
	@Override
	public String checkParamByApi(Order entity) {

		String mobile = entity.getMobile();
		if (StringUtils.isBlank(mobile)) {
			return "手机号码不能为空!";

		}
		return null;
	}

	/**
	 * @Title: getOrderDetailByApi
	 * @Description: 获取订单详情
	 * @param paramMap
	 * @return
	 */
	@Override
	public Order getOrderDetailByApi(Map<String, Object> paramMap) {

		return orderDao.getOrderDetail(paramMap);
	}

	/**
	 * @Title: findPageByApi
	 * @Description: 分页查找
	 * @param entity
	 * @param result
	 * @return
	 */
	@Override
	public LayUiResult findPageByApi(Order entity, LayUiResult result) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("entity", entity);
		paramMap.put("page", result);
		int count = orderDao.findPageByApiCount(paramMap);
		if (count > 0) {
			List<Order> dataList = orderDao.findPageByApi(paramMap);

			// 图片路径处理
			if (null != dataList && dataList.size() > 0) {
				for (Order order : dataList) {
					imageUrlDeal(order);
				}
			}

			result.success(dataList);
			result.setCount(count);
		}
		return result;
	}

	/**
	 * @Title: updateCurrentStep
	 * @Description: 更新当前步骤
	 * @param paramMap
	 * @return
	 */
	@Override
	public Integer updateCurrentStep(Map<String, Object> paramMap) {

		return orderDao.updateCurrentStep(paramMap);
	}

}
