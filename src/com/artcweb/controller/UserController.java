
package com.artcweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.artcweb.baen.LayUiResult;
import com.artcweb.baen.PicPackage;
import com.artcweb.baen.User;
import com.artcweb.service.UserService;


@Controller
@RequestMapping("/admin/center/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * @Title: toList
	 * @Description: 列表UI
	 * @return
	 */
	@RequestMapping(value = "/list/ui")
	public String toList() {
		return "/user/user";
	}
	
	/**
	 * @Title: toAdd
	 * @Description: 新增UI
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String toAdd() {
		return "/user/admin_user_edit";
	}

	/**
	 * @Title: toEdit
	 * @Description: 编辑UI
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public String toEdit(@PathVariable Integer id, HttpServletRequest request) {
		 User entity = userService.get(id);
		request.setAttribute("entity", entity);
		return "/user/admin_user_edit";
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
	public LayUiResult save(User entity) {

		LayUiResult result = new LayUiResult();

		// 参数验证
		String checkResult = userService.checkSaveParam(entity);
		if (StringUtils.isNotBlank(checkResult)) {
			result.failure(checkResult);
			return result;
		}

		Integer operator = null;
		Integer id = entity.getId();

		// 修改
		if (null != id) {
			// 验证唯一性
			String checkUpdateUnique = userService.checkUpdateUnique(entity);
			if (StringUtils.isNotBlank(checkUpdateUnique)) {
				result.failure(checkUpdateUnique);
				return result;
			}
			operator = userService.update(entity);
			// 保存
		}
		else {
			// 验证唯一性
			String checkAddUnique = userService.checkAddUnique(entity);
			if (StringUtils.isNotBlank(checkAddUnique)) {
				result.failure(checkAddUnique);
				return result;
			}
			operator = userService.save(entity);
		}

		if (null != operator && operator > 0) {
			result.success();
			return result;
		}

		result.failure();
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
	public LayUiResult list( User entity, HttpServletRequest request) {

		// 获取参数
		Integer page = Integer.valueOf(request.getParameter("page"));
		Integer limit = Integer.valueOf(request.getParameter("limit"));
		LayUiResult result = new LayUiResult(page, limit);
		result = userService.findByPage(entity, result);
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
	public LayUiResult delete(PicPackage entity) {

		LayUiResult result = new LayUiResult();
		// 获取参数
		Integer id = entity.getId();
		if(null == id){
			result.failure("参数[id]不能为空!");
			return result;
		}
		
		int delResult = userService.delete(id);
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

		int deleteResult = userService.deleteByBatch(array);
		if (deleteResult > 0) {
			result.success();
			return result;
		}
		result.failure();
		return result;
	}

}
