package com.flypaas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flypaas.model.User;
import com.flypaas.service.UserServiceI;

@Controller
@RequestMapping("/userController")
public class UserController {

	private UserServiceI userService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	@RequestMapping("/showUser")
	public String showUser(HttpServletRequest request) {
		User u = userService.queryUser("flypaas01");
		request.setAttribute("user", u);
		return "showUser";
	}

}
