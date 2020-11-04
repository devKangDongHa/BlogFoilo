package com.project.Blog.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.Blog.model.BlogBigCategoryVO;
import com.project.Blog.model.BlogBoardVO;
import com.project.Blog.model.BlogSmallCategoryVO;
import com.project.Blog.service.InterBlogService;

import oracle.sql.CLOB;

@Controller
public class BlogMainController {
	
	@Autowired
	private InterBlogService service;
	
	@RequestMapping(value="/Blog")
	public ModelAndView BlogMain(HttpServletRequest request, ModelAndView mav) {
		
		List<BlogBigCategoryVO> bigcategoryList = service.getBigCategoryList();
		
		List<BlogSmallCategoryVO> smallcategoryList = service.getSmallCategoryList();
		
		String searchWord = request.getParameter("headerSearchInput");
		
		String categoryno = request.getParameter("category");
		
		List<BlogBoardVO> blogboardList = null;
		List<BlogBoardVO> blogboardListSearch = null;
		
		if(categoryno == null && searchWord == null) {
			blogboardList = service.getBlogBoardList();

			mav.addObject("blogboardList", blogboardList);
			
		} else if(categoryno == null && searchWord != null) {
			blogboardListSearch = service.getBlogBoardListSearch(searchWord);
			
			mav.addObject("blogboardListSearch", blogboardListSearch);
			
		}
		
		List<BlogBoardVO> blogboardListCategory = null;
		
		if(categoryno != null) {
			
			blogboardListCategory = service.getBlogBoardListCategory(categoryno);

			String categoryname = null;
			
			if(Integer.parseInt(categoryno) < 100 ) {
				categoryname = service.getCategoryNameBig(categoryno);
			} else {
				categoryname = service.getCategoryNameSmall(categoryno);
			}
			
			mav.addObject("blogboardListCategory", blogboardListCategory);
			mav.addObject("categoryName", categoryname);
		}
		
		mav.addObject("bigcategoryList", bigcategoryList);
		mav.addObject("smallcategoryList", smallcategoryList);
		
		mav.setViewName("blog/blogmain.tiles1");
		
		return mav; 
	}
	 
	@RequestMapping(value="/Blog/Board")
	public ModelAndView blogBoard(HttpServletRequest request, ModelAndView mav) {
		
		String viewno = request.getParameter("no");
		
		List<BlogBigCategoryVO> bigcategoryList = service.getBigCategoryList();
		
		List<BlogSmallCategoryVO> smallcategoryList = service.getSmallCategoryList();
		
		HashMap<String,String> boardView = service.getBoardView(viewno);
		
		mav.addObject("bigcategoryList", bigcategoryList);
		mav.addObject("smallcategoryList", smallcategoryList);
		mav.addObject("boardView", boardView);
		
		mav.setViewName("blog/blogboard.tiles1");
		
		return mav;
	}
	
	@RequestMapping(value="/Blog/Login")
	public ModelAndView blogLogin(HttpServletRequest request, ModelAndView mav) {
		
		mav.setViewName("blog/login");
		
		return mav;
	}

}
