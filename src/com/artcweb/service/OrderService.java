
package com.artcweb.service;

import com.artcweb.baen.LayUiResult;
import com.artcweb.baen.Order;

public interface OrderService extends BaseService<Order, Integer> {

	/**
	* @Title: deleteByBatch
	* @Description: 批量删除
	* @param array
	* @return
	*/
	public int deleteByBatch(String array);

	/**
	* @Title: findByPage
	* @Description: 分页查找
	* @param entity
	* @param result
	* @return
	*/
	public LayUiResult findByPage(Order entity, LayUiResult result);


}
