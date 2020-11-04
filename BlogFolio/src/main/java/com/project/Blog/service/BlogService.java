package com.project.Blog.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Blog.model.BlogBigCategoryVO;
import com.project.Blog.model.BlogBoardVO;
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
	public List<BlogBoardVO> getBlogBoardList() {
		List<BlogBoardVO> blogboardList = dao.getBlogBoardList();
		return blogboardList;
	}

	@Override
	public List<BlogBoardVO> getBlogBoardListSearch(String searchWord) {
		List<BlogBoardVO> blogboardListSearch = dao.getBlogBoardListSearch(searchWord);
		return blogboardListSearch;
	}

	@Override
	public List<BlogBoardVO> getBlogBoardListCategory(String categoryno) {
		List<BlogBoardVO> blogboardListCategory = dao.getBlogBoardListCategory(categoryno);
		return blogboardListCategory;
	}

/*	@Override
	public List<BlogBoardVO> getBlogBoardListCategorySmall(String categoryno) {
		List<BlogBoardVO> blogboardListCategory = dao.getBlogBoardListCategorySmall(categoryno);
		return blogboardListCategory;
	}*/

	@Override
	public String getCategoryNameBig(String categoryno) {
		String categoryName = dao.getCategoryNameBig(categoryno);
		return categoryName;
	}

	@Override
	public String getCategoryNameSmall(String categoryno) {
		String categoryName = dao.getCategoryNameSmall(categoryno);
		return categoryName;
	}

	@Override
	public HashMap<String, String> getBoardView(String viewno) {
		HashMap<String, String> boardView = dao.getBoardView(viewno);
		return boardView;
	}

}
