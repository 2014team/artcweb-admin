
package com.artcweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.artcweb.baen.AdminUser;
import com.artcweb.baen.JsonResult;
import com.artcweb.service.AdminUserService;
import com.artcweb.util.SessionUtil;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;

	/**
	 * @Title: login
	 * @Description: 登录界面
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {

		return "login";
	}

	/**
	 * @Title: logout
	 * @Description: 退出
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {

		// 删除用户session信息
		SessionUtil.deleteSessionUser(request);
		return "redirect:/admin/login";
	}

	/**
	 * @Title: loginSubmit
	 * @Description: 登录提交
	 * @param user
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login/submit")
	public JsonResult loginSubmit(AdminUser user, HttpServletRequest request) {

		JsonResult jsonResult = new JsonResult();
		// 基本参数验证
		String errorMsg = adminUserService.checkLoginParam(user);
		if (StringUtils.isNotBlank(errorMsg)) {
			jsonResult.failure(errorMsg);
			return jsonResult;
		}

		// 登录
		AdminUser userResult = adminUserService.login(user);
		if (null == userResult) {
			jsonResult.failure("请认真核对账号、密码！");
			return jsonResult;
		}

		// 保存用户session信息
		SessionUtil.saveSessionUser(request, userResult);

		jsonResult.success("登录成功！");
		return jsonResult;
	}

	/**
	 * @Title: index
	 * @Description: 后台中心
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/center/index")
	public String centerIndex(HttpServletRequest request) {

		AdminUser user = SessionUtil.getSessionUser(request);
		if (null == user) {
			return "redirect:/admin/login.do";
		}
		request.setAttribute("user", user);
		return "index";
	}

}
