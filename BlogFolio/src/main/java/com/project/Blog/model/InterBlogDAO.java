package com.project.Blog.model;

import java.util.HashMap;
import java.util.List;

public interface InterBlogDAO {

	// 대분류 카테고리
	List<BlogBigCategoryVO> getBigCategoryList();
 
	// 소분류 카테고리
	List<BlogSmallCategoryVO> getSmallCategoryList();

	// 블로그 글 목록
	List<BlogBoardVO> getBlogBoardList();
	
	// 전체 글 검색
	List<BlogBoardVO> getBlogBoardListSearch(String SearchWord);

	// 카테고리 대분류 검색
	List<BlogBoardVO> getBlogBoardListCategory(String categoryno);
/*
	// 카테고리 소분류 검색
	List<BlogBoardVO> getBlogBoardListCategorySmall(String categoryno);*/

	// 카테고리 대분류일 때 이름
	String getCategoryNameBig(String categoryno);

	// 카테고리 소분류일 때 이름
	String getCategoryNameSmall(String categoryno);

	// 글 보기
	HashMap<String, String> getBoardView(String viewno);

	// 랜덤 게스트 계정 생성
	int editGuest(HashMap<String, String> map);
	
	// 게스트 계정 가져오기
	BlogGuestVO getLoginGuest(String userid);

	// 로그인 검사
	HashMap<String, String> isExistUser(HashMap<String, String> map);

}
