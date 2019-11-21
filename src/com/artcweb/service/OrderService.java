
package com.artcweb.service;

import java.util.Map;

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

	/**
	 * @Title: findPageByApi
	 * @Description: 分页查找
	 * @param entity
	 * @param result
	 * @return
	 */
	public LayUiResult findPageByApi(Order entity, LayUiResult result);

	/**
	 * @Title: checkParamByApi
	 * @Description: 参数验证
	 * @param entity
	 * @return
	 */
	public String checkParamByApi(Order entity);

	/**
	 * @Title: getOrderDetailByApi
	 * @Description: 获取订单详情
	 * @param paramMap
	 * @return
	 */
	public Order getOrderDetailByApi(Map<String, Object> paramMap);
	
	
	/**
	* @Title: updateCurrentStep
	* @Description: 更新当前步骤
	* @param paramMap
	* @return
	*/
	Integer updateCurrentStep(Map<String,Object> paramMap);

}
