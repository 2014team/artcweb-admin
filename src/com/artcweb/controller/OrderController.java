
package com.artcweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.artcweb.baen.LayUiResult;
import com.artcweb.baen.Order;
import com.artcweb.baen.PicPackage;
import com.artcweb.baen.User;
import com.artcweb.constant.ComeFromConstant;
import com.artcweb.service.OrderService;
import com.artcweb.service.PicPackageService;
import com.artcweb.service.UserService;

@Controller
@RequestMapping("/admin/center/order")
public class OrderController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private PicPackageService picPackageService;

	/**
	 * @Title: toList
	 * @Description: 到列表UI
	 * @return
	 */
	@RequestMapping(value = "/list/ui")
	public String toList() {

		return "/order/order";
	}

	/**
	 * @Title: toAdd
	 * @Description: 到新增UI
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String toAdd(HttpServletRequest request) {

		// 用户信息
		Map<String, Object> paramMap = null;
		List<User> userList = userService.select(paramMap);
		request.setAttribute("userList", userList);

		// 获取套餐信息
		List<PicPackage> packageList = picPackageService.select(paramMap);
		request.setAttribute("packageList", packageList);
		return "/order/order_edit";
	}

	/**
	 * @Title: toEdit
	 * @Description: 到编辑UI
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public String toEdit(@PathVariable Integer id, HttpServletRequest request) {

		// 获取 订单信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Order order = orderService.getById(paramMap);
		request.setAttribute("order", order);

		// 获取套餐信息
		paramMap.clear();
		List<PicPackage> packageList = picPackageService.select(paramMap);
		request.setAttribute("packageList", packageList);

		// 用户信息
		List<User> userList = userService.select(paramMap);
		request.setAttribute("userList", userList);

		return "/order/order_edit";
	}

	/**
	 * @Title: save
	 * @Description: 保存
	 * @param adminCate
	 * @param operator
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save")
	public LayUiResult save(Order entity, MultipartFile file, HttpServletRequest request) {

		int template = entity.getTemplate();
		LayUiResult result = null;
		// 选择模本
		if (template == 1) {
			entity.setComeFrom(ComeFromConstant.TEMPLATE);
			result = orderService.saveChooseTemplate(entity);
		}
		else {// 新建模板
			entity.setComeFrom(ComeFromConstant.CUSTOM_MAKE);
			result = orderService.saveNewTemplate(entity, file, request);
		}
		return result;
	}

	/**
	 * @Title: list
	 * @Description: 列表
	 * @param adminCate
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.POST,
					RequestMethod.GET }, produces = "application/json; charset=UTF-8")
	public LayUiResult list(Order entity, HttpServletRequest request) {

		// 获取参数
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));
		LayUiResult result = new LayUiResult(page, limit);
		result = orderService.findByPage(entity, result);
		return result;
	}

	/**
	 * @Title: delete
	 * @Description: 删除
	 * @param adminCate
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = { RequestMethod.POST,
					RequestMethod.GET }, produces = "application/json; charset=UTF-8")
	public LayUiResult delete(Order entity) {

		LayUiResult result = new LayUiResult();
		// 获取参数
		Integer id = entity.getId();
		int delResult = orderService.delete(id);
		if (delResult > 0) {
			result.success();
			return result;
		}

		result.failure();
		return result;
	}

	/**
	 * @Title: deleteBatch
	 * @Description: 批量删除
	 * @param array
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete/batch", method = { RequestMethod.POST,
					RequestMethod.GET }, produces = "application/json; charset=UTF-8")
	public LayUiResult deleteBatch(String array) {

		LayUiResult result = new LayUiResult();
		if (StringUtils.isBlank(array)) {
			result.failure();
			return result;
		}

		array = array.replace("[", "").replace("]", "");

		int deleteResult = orderService.deleteByBatch(array);
		if (deleteResult > 0) {
			result.success();
			return result;
		}
		result.failure();
		return result;
	}

}
