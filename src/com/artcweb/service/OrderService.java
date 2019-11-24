
package com.artcweb.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

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
	public Integer updateCurrentStepByApi(Map<String, Object> paramMap);

	/**
	 * @Title: checkSaveParam
	 * @Description: 新增参数验证
	 * @param entity
	 * @return
	 */
	public String checkSaveParam(Order entity);

	/**
	 * @Title: checkAddUnique
	 * @Description: 唯一性验证
	 * @param entity
	 * @return
	 */
	public String checkAddUnique(Order entity);

	/**
	 * @Title: defaultValueDeal
	 * @Description: 默认值处理
	 * @param entity
	 */
	public void defaultValueDeal(Order entity);

	/**
	 * @Title: getById
	 * @Description: 通过id获取订单
	 * @param paramMap
	 * @return
	 */
	public Order getById(Map<String, Object> paramMap);

	/**
	 * @Title: checkUpdateUnique
	 * @Description: 修改唯一性验证
	 * @param entity
	 * @return
	 */
	public String checkUpdateUnique(Order entity);

	/**
	 * @Title: saveChooseTemplate
	 * @Description: 选择模本保存
	 * @param entity
	 * @return
	 */
	public LayUiResult saveChooseTemplate(Order entity);

	/**
	 * @Title: saveNewTemplate
	 * @Description: 新建模板保存
	 * @param entity
	 * @param file
	 * @param request
	 * @return
	 */
	public LayUiResult saveNewTemplate(Order entity, MultipartFile file, HttpServletRequest request);

}
