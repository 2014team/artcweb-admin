
package com.artcweb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.artcweb.baen.Order;

@Repository
public interface OrderDao extends BaseDao<Order, Integer> {

	/**
	* @Title: deleteByBatch
	* @Description: 批量删除
	* @param array
	* @return
	*/
	public int deleteByBatch(String array);
	
	/**
	* @Title: getOrderDetail
	* @Description: 获取订单详情
	* @param paramMap
	* @return
	*/
	public Order getOrderDetail(Map<String,Object> paramMap);
	
	
	/**
	 * @Title: findPageByApi
	 * @Description: 分页查找
	 * @param entity
	 * @param result
	 * @return
	 */
	List<Order> findPageByApi(Map<String,Object> paramMap);
	
	Integer findPageByApiCount(Map<String,Object> paramMap);
	
	Integer updateCurrentStep(Map<String,Object> paramMap);

	
}