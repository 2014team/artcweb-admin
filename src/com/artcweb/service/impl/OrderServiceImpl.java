
package com.artcweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.artcweb.baen.LayUiResult;
import com.artcweb.baen.Order;
import com.artcweb.baen.PicPackage;
import com.artcweb.constant.ComeFromConstant;
import com.artcweb.constant.UploadConstant;
import com.artcweb.dao.OrderDao;
import com.artcweb.dao.PicPackageDao;
import com.artcweb.service.ImageService;
import com.artcweb.service.OrderService;
import com.artcweb.util.FileUtil;
import com.artcweb.util.ImageUtil;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, Integer> implements OrderService {
	private static Logger logger = Logger.getLogger(OrderServiceImpl.class);
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private PicPackageDao picPackageDao;

	@Autowired
	private ImageService imageService;

	/**
	 * @Title: deleteByBatch
	 * @Description: 批量删除
	 * @param array
	 * @return
	 */
	@Override
	public boolean deleteByBatch(String array,HttpServletRequest request) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idStr", array);
		
		//获取订单
		List<Order> orderList = orderDao.select(paramMap);
		int delResult = 0;
		if(null != orderList && orderList.size() > 0){
			// 删除订单
			delResult = orderDao.deleteByBatch(array);
			if(delResult > 0){
					StringBuffer sb = new StringBuffer();
					for (Order order : orderList) {
						sb.append(order.getPackageId());
						sb.append(",");
					}
					
					String picPackageStr = sb.toString();
					if(picPackageStr.lastIndexOf(",") != -1){
						picPackageStr = picPackageStr.substring(0,picPackageStr.length()-1);
					}
				//删除模本
				Integer picPackageRersult = picPackageDao.deleteByBatch(picPackageStr);
				if(null != picPackageRersult && picPackageRersult > 0){
					//删除图片
					for (Order order : orderList) {
						Integer comeFrom = order.getComeFrom();
						if(comeFrom == ComeFromConstant.CUSTOM_MAKE){
							String imageUrl = order.getImageUrl(); 
							String minImageUrl = order.getMinImageUrl();//删除原来物理图片
								if(StringUtils.isNotBlank(imageUrl)){
									boolean  deleteResult = FileUtil.deleteFile(imageUrl,request);
									logger.info("物理删除图片结果 = "+deleteResult);
								}
								if(StringUtils.isNotBlank(minImageUrl)){
									boolean  deleteResult = FileUtil.deleteFile(minImageUrl,request);
									logger.info("物理删除图片结果 = "+deleteResult);
								}
								
						}
					}
					
				}
				
				return true;	
					
			}
		}
		return false;
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

			result.setData(dataList);
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
		Order order = orderDao.getOrderDetail(paramMap);
		if(null != order){
			imageUrlDeal(order);
			order.setCurrentStep(StringUtils.defaultIfBlank(order.getCurrentStep(), "1"));
		}
		return order;
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
	public Integer updateCurrentStepByApi(Map<String, Object> paramMap) {

		return orderDao.updateCurrentStep(paramMap);
	}

	/**
	 * @Title: checkSaveParam
	 * @Description: 新增参数验证
	 * @param entity
	 * @return
	 */
	@Override
	public String checkSaveParam(Order entity) {

		String mobile = entity.getMobile();
		if (StringUtils.isBlank(mobile)) {
			return "手机号码不能为空!";
		}

		Integer sort = entity.getSort();
		if (null == sort) {
			return "排序号不能为空!";
		}
		return null;
	}

	/**
	 * @Title: checkAddUnique
	 * @Description: 唯一性验证
	 * @param entity
	 * @return
	 */
	@Override
	public String checkAddUnique(Order entity) {

		List<Order> list = null;
		// 分类ID与分类名唯一性验证
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", entity.getUserId());
		paramMap.put("packageId", entity.getPackageId());
		list = orderDao.checkUnique(paramMap);
		if (null != list && list.size() > 0) {
			return "用户已存在该订单!";
		}
		return null;
	}

	/**
	 * @Title: defaultValueDeal
	 * @Description: 默认值处理
	 * @param entity
	 */
	@Override
	public void defaultValueDeal(Order entity) {

		entity.setCurrentStep(StringUtils.defaultIfBlank(entity.getCurrentStep(), ""));

	}

	/**
	 * @Title: getById
	 * @Description: 通过id获取订单
	 * @param paramMap
	 * @return
	 */
	@Override
	public Order getById(Map<String, Object> paramMap) {

		return orderDao.getByMap(paramMap);
	}

	/**
	 * @Title: checkUpdateUnique
	 * @Description: 修改唯一性验证
	 * @param entity
	 * @return
	 */
	@Override
	public String checkUpdateUnique(Order entity) {

		List<Order> list = null;
		// 分类ID与分类名唯一性验证
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", entity.getOrderId());
		paramMap.put("userId", entity.getUserId());
		paramMap.put("packageId", entity.getPackageId());
		list = orderDao.checkUnique(paramMap);
		if (null != list && list.size() > 0) {
			return "手机号码已存在该订单!";
		}

		return null;
	}

	/**
	 * @Title: saveChooseTemplate
	 * @Description: 选择模本保存
	 * @param entity
	 * @return
	 */
	@Override
	public LayUiResult saveChooseTemplate(Order entity) {

		LayUiResult result = new LayUiResult();

		// 参数验证
		String checkResult = checkSaveParam(entity);
		if (StringUtils.isNotBlank(checkResult)) {
			result.failure(checkResult);
			return result;
		}
		//
		Integer operator = null;
		Integer orderId = entity.getOrderId();

		// 修改
		if (null != orderId) {
			// 验证唯一性
			String checkUpdateUnique = checkUpdateUnique(entity);
			if (StringUtils.isNotBlank(checkUpdateUnique)) {
				result.failure(checkUpdateUnique);
				return result;
			}

			Order order = orderDao.get(orderId);
			if (null != order) {
				order.setPackageId(entity.getPackageId());
				order.setMobile(entity.getMobile());
				order.setSort(entity.getSort());
				operator = orderDao.update(order);
			}

		}
		else {	// 保存
			// 验证唯一性
			String checkAddUnique = checkAddUnique(entity);
			if (StringUtils.isNotBlank(checkAddUnique)) {
				result.failure(checkAddUnique);
				return result;
			}
			// 默认值处理
			defaultValueDeal(entity);
			operator = orderDao.save(entity);
		}

		if (null != operator && operator > 0) {
			result.success();
			return result;
		}

		result.failure();
		return result;

	}

	/**
	 * @Title: saveNewTemplate
	 * @Description: 新建模板保存
	 * @param entity
	 * @param file
	 * @param request
	 * @return
	 */
	@Override
	public LayUiResult saveNewTemplate(Order entity, MultipartFile file, HttpServletRequest request) {

		LayUiResult result = new LayUiResult();

		// 参数验证
		String checkResult = chekNewTemplateParam(entity);
		if (StringUtils.isNotBlank(checkResult)) {
			result.failure(checkResult);
			return result;
		}

		Integer operator = null;
		String imageUrl = null;
		String minImageUrl = null;
		Integer packageId = entity.getPackageId();
		Integer orderId = entity.getOrderId();
		if (null != packageId || (null != orderId && orderId > 0)) {// 修改

			String checkUpdateUnique = null;
			if (file != null) {
				// 图片验证
				String errorMsg = imageService.checkImage(file);
				if (StringUtils.isNotBlank(errorMsg)) {
					result.failure(errorMsg);
					return result;
				}
				// 验证唯一性
				checkUpdateUnique = checkUpdateUnique(entity);
				if (StringUtils.isNotBlank(checkUpdateUnique)) {
					result.failure(checkUpdateUnique);
					return result;
				}

				// 上传图片
				imageUrl = imageService.uploadImage(request, file, UploadConstant.SAVE_UPLOAD_PATH);
				minImageUrl = imageService.uploadMinImage(request, file, UploadConstant.SAVE_UPLOAD_PATH);

				entity.setImageUrl(imageUrl);
				entity.setMinImageUrl(minImageUrl);
				operator = update(entity);

			}
			else {

				// 验证唯一性
				checkUpdateUnique = checkUpdateUnique(entity);
				if (StringUtils.isNotBlank(checkUpdateUnique)) {
					result.failure(checkUpdateUnique);
					return result;
				}

				// 修改套餐信息
				PicPackage picPackage = picPackageDao.get(Integer.valueOf(entity.getPackageId()));
				if (null != picPackage) {
					picPackage.setPackageName(entity.getPackageName());
					picPackage.setStep(entity.getStep());
					picPackage.setPins(entity.getPins());
				}
				operator = picPackageDao.update(picPackage);

				if (null != operator && operator > 0) {

					// 修改订单信息
					Order order = orderDao.get(entity.getOrderId());
					order.setMobile(entity.getMobile());
					order.setPackageId(picPackage.getPackageId());
					order.setPackageName(picPackage.getPackageName());
					operator = orderDao.update(order);
				}

				if (null != operator && operator > 0) {
					result.success();
					return result;
				}

			}

		}
		else {// 保存
			
			if(null == file){
				result.failure("请选择图片!");
				return result;
			}

			// 图片验证
			String errorMsg = imageService.checkImage(file);
			if (StringUtils.isNotBlank(errorMsg)) {
				result.failure(errorMsg);
				return result;
			}

			// 验证唯一性
			String checkAddUnique = checkNewTemplateUnique(entity);
			if (StringUtils.isNotBlank(checkAddUnique)) {
				result.failure(checkAddUnique);
				return result;
			}
			// 上传图片
			imageUrl = imageService.uploadImage(request, file, UploadConstant.SAVE_UPLOAD_PATH);
			minImageUrl = imageService.uploadMinImage(request, file, UploadConstant.SAVE_UPLOAD_PATH);
			entity.setMinImageUrl(minImageUrl);
			entity.setImageUrl(imageUrl);

			// 保存套餐信息
			PicPackage picPackage = new PicPackage();
			picPackage.setPackageName(entity.getPackageName());
			picPackage.setMinImageUrl(entity.getMinImageUrl());
			picPackage.setStep(entity.getStep());
			picPackage.setPins(entity.getPins());
			picPackage.setImageUrl(entity.getImageUrl());
			picPackage.setComeFrom(entity.getComeFrom());
			operator = picPackageDao.save(picPackage);
			if (null != operator && operator > 0) {
				// 保存订单信息

				// 默认值处理
				entity.setCurrentStep("");
				entity.setPackageId(picPackage.getPackageId());
				operator = orderDao.save(entity);
			}

			if (null != operator && operator > 0) {
				result.success();
				return result;
			}

		}
		result.failure();
		return result;

	}

	private String chekNewTemplateParam(Order entity) {

		String packageName = entity.getPackageName();
		if (StringUtils.isBlank(packageName)) {
			return "模板名称不能为空!";
		}
		String step = entity.getStep();
		if (StringUtils.isBlank(step)) {
			return "执行步骤不能为空!";
		}
		Integer pins = entity.getPins();
		if (null == pins || pins < 1) {
			return "钉子数量不能为空!";
		}
		String mobile = entity.getMobile();
		if (StringUtils.isBlank(mobile)) {
			return "手机号码不能为空!";
		}
		
		Integer sort = entity.getSort();
		if (null == sort) {
			return "排序号不能为空!";
		}
		return null;
	}

	public String checkNewTemplateUnique(Order entity) {

		List<PicPackage> list = null;
		// 分类ID与分类名唯一性验证
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packageName", entity.getPackageName());
		list = picPackageDao.checkUnique(paramMap);
		if (null != list && list.size() > 0) {
			return "图纸名称已存在!";
		}
		return null;
	}

	
	/**
	* @Title: deleteOrder
	* @Description: 删除订单
	* @param id
	* @param request
	* @return
	*/
	@Override
	public boolean deleteOrder(Integer id ,HttpServletRequest request) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", id);
		Order order = orderDao.getByMap(paramMap);
		if(null != order){
			String imageUrl = order.getImageUrl(); 
			String minImageUrl = order.getMinImageUrl();
			Integer packageId = order.getPackageId();
			Integer comeFrom = order.getComeFrom();
			
			//删除订单
			Integer delOrder = orderDao.delete(id);
			if(null != delOrder && delOrder > 0){
				if(comeFrom == ComeFromConstant.CUSTOM_MAKE){
					//删除定制模板
					Integer delPicPackage = picPackageDao.delete(packageId);
					logger.info("删除定制模板结果 = "+delPicPackage);
					
					//删除原来物理图片
					if(null != delPicPackage && delPicPackage > 0){
						if(StringUtils.isNotBlank(imageUrl)){
							boolean  deleteResult = FileUtil.deleteFile(imageUrl,request);
							logger.info("物理删除图片结果 = "+deleteResult);
						}
						if(StringUtils.isNotBlank(minImageUrl)){
							boolean  deleteResult = FileUtil.deleteFile(minImageUrl,request);
							logger.info("物理删除图片结果 = "+deleteResult);
						}
						
					}
				}
				return true;
			}
			
		}
		return false;
	}
}
