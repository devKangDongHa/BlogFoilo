package com.project.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping(value="/main.com")
	public String main(HttpServletRequest request) {
		
		return "/main/main";
	}
	
}
