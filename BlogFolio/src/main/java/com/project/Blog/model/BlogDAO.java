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
	public String getCategoryName(String categoryno) {
		String categoryName = sqlsession.selectOne("Blog.getCategoryName", categoryno);
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

	@Override
	public int getTotalCountWithNOsarch() {
		int n = sqlsession.selectOne("Blog.getTotalCountWithNOsearch");
		return n;
	}

	@Override
	public int getTotalCountWithSearch(HashMap<String, String> paraMap) {
		int n = sqlsession.selectOne("Blog.getTotalCountWithSearch", paraMap);
		return n;
	}

	@Override
	public int getTotalCountWithCategory(HashMap<String, String> paraMap) {
		int n = sqlsession.selectOne("Blog.getTotalCountWithCategory", paraMap);
		return n;
	}

	@Override
	public List<BlogBoardVO> BlogBoardListWithPaging(HashMap<String, String> paraMap) {
		List<BlogBoardVO> blogboardList = sqlsession.selectList("Blog.BlogBoardListWithPaging", paraMap);
		return blogboardList;
	}

	@Override
	public List<BlogBoardVO> getCateRecentList(String categoryno) {
		List<BlogBoardVO> cateRecentList = sqlsession.selectList("Blog.getCateRecentList", categoryno);
		return cateRecentList;
	}

	@Override
	public int writeEnd(BoardVO boardvo) {
		int n = sqlsession.insert("Blog.writeEnd", boardvo);
		return n;
	}

	@Override
	public int writeEnd_withFile(BoardVO boardvo) {
		int n = sqlsession.insert("Blog.writeEnd_withFile", boardvo);
		return n;
	}


}
