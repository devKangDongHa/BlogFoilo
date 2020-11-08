package com.project.Blog.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Blog.model.BlogBigCategoryVO;
import com.project.Blog.model.BlogBoardVO;
import com.project.Blog.model.BlogGuestVO;
import com.project.Blog.model.BlogSmallCategoryVO;
import com.project.Blog.model.InterBlogDAO;

@Service
public class BlogService implements InterBlogService {

	@Autowired
	private InterBlogDAO dao;
	
	@Override
	public List<BlogBigCategoryVO> getBigCategoryList() {
		List<BlogBigCategoryVO> bigcategoryList = dao.getBigCategoryList();
		return bigcategoryList;
	}

	@Override
	public List<BlogSmallCategoryVO> getSmallCategoryList() {
		List<BlogSmallCategoryVO> smallcategoryList = dao.getSmallCategoryList();
		return smallcategoryList;
	}

	@Override
	public String getCategoryName(String categoryno) {
		String categoryName = dao.getCategoryName(categoryno);
		return categoryName;
	}

	@Override
	public HashMap<String, String> getBoardView(String viewno) {
		HashMap<String, String> boardView = dao.getBoardView(viewno);
		return boardView;
	}

	@Override
	public int editGuest(HashMap<String, String> map) {
		int n = dao.editGuest(map);
		return n;
	}

	@Override
	public BlogGuestVO getLoginGuest(String userid) {
		BlogGuestVO loginguest = dao.getLoginGuest(userid);
		return loginguest;
	}

	@Override
	public HashMap<String, String> isExistUser(HashMap<String, String> map) {
		HashMap<String, String> usermap = dao.isExistUser(map);
		return usermap;
	}

	@Override
	public int getTotalCountWithNOsearch() {
		int n = dao.getTotalCountWithNOsarch();
		return n;
	}

	@Override
	public int getTotalCountWithSearch(HashMap<String, String> paraMap) {
		int n = dao.getTotalCountWithSearch(paraMap);
		return n;
	}

	@Override
	public List<BlogBoardVO> BlogBoardListWithPaging(HashMap<String, String> paraMap) {
		List<BlogBoardVO> blogboardList = dao.BlogBoardListWithPaging(paraMap);
		return blogboardList;
	}

	@Override
	public int getTotalCountWithCategory(HashMap<String, String> paraMap) {
		int n = dao.getTotalCountWithCategory(paraMap);
		return n;
	}

	@Override
	public List<BlogBoardVO> getCateRecentList(String categoryno) {
		List<BlogBoardVO> cateRecentList = dao.getCateRecentList(categoryno);
		return cateRecentList;
	}

}
