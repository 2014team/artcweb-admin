package com.artcweb.controller;

	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.artcweb.baen.User;
import com.artcweb.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/admin/user/list")
	public Object list(){
		List<User> userList = userService.select(null);
		return userList;
	}

}
