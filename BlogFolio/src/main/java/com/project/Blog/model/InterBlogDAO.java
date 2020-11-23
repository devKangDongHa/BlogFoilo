package com.project.Blog.model;

import java.util.HashMap;
import java.util.List;

public interface InterBlogDAO {

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
	BlogGuestVO isExistUser(HashMap<String, String> map);

	// 검색 조건, 카테고리 없을 때 게시글 수
	int getTotalCountWithNOsarch();

	// 검색 조건 있을 때 게시글 수
	int getTotalCountWithSearch(HashMap<String, String> paraMap);
	
	// 카테고리 있을 때 게시글 수
	int getTotalCountWithCategory(HashMap<String, String> paraMap);
	
	// 페이징 게시글 목록
	List<BlogBoardVO> BlogBoardListWithPaging(HashMap<String, String> paraMap);

	// 카테고리 최신 글 목록
	List<BlogBoardVO> getCateRecentList(String categoryno);

	// 첨부 파일 없는 글 작성
	int writeEnd(BoardVO boardvo);

	// 첨부 파일 있는 글 작성
	int writeEnd_withFile(BoardVO boardvo);

	// 좋아요 갯수
	String getLike_count(String viewno);

	// 글 삭제
	int DelWrite(String viewno);

	// 수정 내용 가져오기
	BoardVO getBoardDetail(String viewno);

	// 글 수정
	int EditEnd(BoardVO boardvo);

	// 글 수정 파일 첨부
	int EidtEnd_withFile(BoardVO boardvo);

	// 로그인 중인 게스트 유저의 해당 글 좋아요 여부 조회
	String CheckLike(HashMap<String, String> checkmap);

	// 좋아요
	int addLike(HashMap<String, String> paramap);

	// 좋아요 취소
	int cancelLike(HashMap<String, String> paramap);


}
