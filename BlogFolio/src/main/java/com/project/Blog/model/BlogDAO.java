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
	public BlogGuestVO isExistUser(HashMap<String, String> map) {
		BlogGuestVO loginuser = sqlsession.selectOne("Blog.isExistUser", map);
		return loginuser;
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

	@Override
	public String getLike_count(String viewno) {
		String like_count = sqlsession.selectOne("Blog.getLike_count", viewno);
		return like_count;
	}

	@Override
	public int DelWrite(String viewno) {
		int n = sqlsession.delete("Blog.DelWrite", viewno);
		return n;
	}

	@Override
	public BoardVO getBoardDetail(String viewno) {
		BoardVO boardview = sqlsession.selectOne("Blog.getBoardDetail", viewno);
		return boardview;
	}

	@Override
	public int EditEnd(BoardVO boardvo) {
		int n = sqlsession.update("Blog.EditEnd", boardvo);
		return n;
	}

	@Override
	public int EidtEnd_withFile(BoardVO boardvo) {
		int n = sqlsession.update("Blog.EditEnd_withFile", boardvo);
		return n;
	}

	@Override
	public String CheckLike(HashMap<String, String> checkmap) {
		String n = sqlsession.selectOne("Blog.CheckLike", checkmap);
		return n;
	}

	@Override
	public int addLike(HashMap<String, String> paramap) {
		int n = sqlsession.insert("Blog.addLike", paramap);
		return n;
	}

	@Override
	public int cancelLike(HashMap<String, String> paramap) {
		int n = sqlsession.update("Blog.cancelLike", paramap);
		return n;
	}


}
