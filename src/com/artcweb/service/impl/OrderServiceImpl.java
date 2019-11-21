
package com.artcweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artcweb.baen.LayUiResult;
import com.artcweb.baen.Order;
import com.artcweb.dao.OrderDao;
import com.artcweb.service.OrderService;

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
			result.setData(dataList);
			result.setCount(count);
		}
		return result;
	}

}
