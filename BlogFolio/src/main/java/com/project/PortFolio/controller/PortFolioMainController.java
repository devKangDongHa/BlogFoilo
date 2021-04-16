package com.project.PortFolio.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.PortFolio.service.InterPortFolioService;

@Controller
public class PortFolioMainController {
	
	@Autowired
	private InterPortFolioService service;

	@RequestMapping(value="/PortFolio")
	public ModelAndView PortFolioMain(HttpServletRequest request, ModelAndView mav) {
		
		mav.setViewName("portfolio/portfoliomain.tiles2");
		
		return mav;
	}
	
	
}
