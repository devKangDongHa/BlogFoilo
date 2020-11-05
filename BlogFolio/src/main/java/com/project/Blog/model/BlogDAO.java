package com.project.Blog.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDAO implements InterBlogDAO {

	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public List<BlogBigCategoryVO> getBigCategoryList() {
		List<BlogBigCategoryVO> bigcategoryList = sqlsession.selectList("Blog.getBigCategoryList");
		return bigcategoryList;
	}

	@Override
	public List<BlogSmallCategoryVO> getSmallCategoryList() {
		List<BlogSmallCategoryVO> smallcategoryList = sqlsession.selectList("Blog.getSmallCategoryList");
		return smallcategoryList;
	}

	@Override
	public List<BlogBoardVO> getBlogBoardList() {
		List<BlogBoardVO> blogboardList = sqlsession.selectList("Blog.getBlogBoardList");
		return blogboardList;
	}

	@Override
	public List<BlogBoardVO> getBlogBoardListSearch(String searchWord) {
		List<BlogBoardVO> blogboardListSearch = sqlsession.selectList("Blog.getBlogBoardListSearch", searchWord);
		return blogboardListSearch;
	}

	@Override
	public List<BlogBoardVO> getBlogBoardListCategory(String categoryno) {
		List<BlogBoardVO> blogboardListCategory = sqlsession.selectList("Blog.getBlogBoardListCategory", categoryno);
		return blogboardListCategory;
	}
/*
	@Override
	public List<BlogBoardVO> getBlogBoardListCategorySmall(String categoryno) {
		List<BlogBoardVO> blogboardListCategory = sqlsession.selectList("Blog.getBlogBoardListCategorySmall", categoryno);
		return blogboardListCategory;
	}*/

	@Override
	public String getCategoryNameBig(String categoryno) {
		String categoryName = sqlsession.selectOne("Blog.getCategoryNameBig", categoryno);
		return categoryName;
	}

	@Override
	public String getCategoryNameSmall(String categoryno) {
		String categoryName = sqlsession.selectOne("Blog.getCategoryNameSmall", categoryno);
		return categoryName;
	}

	@Override
	public HashMap<String, String> getBoardView(String viewno) {
		HashMap<String, String> boardView = sqlsession.selectOne("Blog.getBoardView", viewno);
		return boardView;
	}

	@Override
	public int editGuest(HashMap<String, String> map) {
		int n = sqlsession.insert("Blog.editGuest", map);
		return n;
	}

	@Override
	public BlogGuestVO getLoginGuest(String userid) {
		BlogGuestVO loginguest = sqlsession.selectOne("Blog.getLoginGuest", userid);
		return loginguest;
	}

	@Override
	public HashMap<String, String> isExistUser(HashMap<String, String> map) {
		HashMap<String, String> usermap = sqlsession.selectOne("Blog.isExistUser", map);
		return usermap;
	}

}
