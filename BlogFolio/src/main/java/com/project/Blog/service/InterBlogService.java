package com.project.Blog.service;

import java.util.HashMap;
import java.util.List;

import com.project.Blog.model.BlogBigCategoryVO;
import com.project.Blog.model.BlogBoardVO;
import com.project.Blog.model.BlogSmallCategoryVO;

public interface InterBlogService {

	// 대분류 카테고리
	List<BlogBigCategoryVO> getBigCategoryList();

	// 소분류 카테고리
	List<BlogSmallCategoryVO> getSmallCategoryList();

	// 전체 글 목록
	List<BlogBoardVO> getBlogBoardList();
	
	// 전체 글 검색
	List<BlogBoardVO> getBlogBoardListSearch(String searchWord);

	// 카테고리 대분류 검색
	List<BlogBoardVO> getBlogBoardListCategory(String categoryno);

/*	// 카테고리 소분류 검색
	List<BlogBoardVO> getBlogBoardListCategorySmall(String categoryno);*/

	// 카테고리 대분류일 때 이름
	String getCategoryNameBig(String categoryno);

	// 카테고리 소분류일 때 이름
	String getCategoryNameSmall(String categoryno);

	// 글 보기
	HashMap<String, String> getBoardView(String viewno);

}
