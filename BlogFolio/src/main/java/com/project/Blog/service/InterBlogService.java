package com.project.Blog.service;

import java.util.HashMap;
import java.util.List;

import com.project.Blog.model.BlogBigCategoryVO;
import com.project.Blog.model.BlogBoardVO;
import com.project.Blog.model.BlogGuestVO;
import com.project.Blog.model.BlogSmallCategoryVO;

public interface InterBlogService {

	// 대분류 카테고리
	List<BlogBigCategoryVO> getBigCategoryList();

	// 소분류 카테고리
	List<BlogSmallCategoryVO> getSmallCategoryList();
	
	// 카테고리 이름
	String getCategoryName(String categoryno);

	// 글 보기
	HashMap<String, String> getBoardView(String viewno);

	// 랜덤 게스트 계정 생성
	int editGuest(HashMap<String, String> map);

	// 게스트 계정 가져오기
	BlogGuestVO getLoginGuest(String userid);

	// 로그인 검사
	HashMap<String, String> isExistUser(HashMap<String, String> map);

	// 검색 조건, 카테고리 없을 때 게시글 수
	int getTotalCountWithNOsearch();
	
	// 검색 조건 있을 때 게시글 수
	int getTotalCountWithSearch(HashMap<String, String> paraMap);
	
	// 카테고리 있을 때 게시글 수
	int getTotalCountWithCategory(HashMap<String, String> paraMap);
	
	// 페이징 게시글 가져오기
	List<BlogBoardVO> BlogBoardListWithPaging(HashMap<String, String> paraMap);

	// 카테고리 최신 글 목록
	List<BlogBoardVO> getCateRecentList(String categoryno);

}
