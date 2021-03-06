package com.project.Blog.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.project.Blog.model.BlogBigCategoryVO;
import com.project.Blog.model.BlogBoardVO;
import com.project.Blog.model.BlogGuestVO;
import com.project.Blog.model.BlogSmallCategoryVO;
import com.project.Blog.model.BoardVO;
import com.project.Blog.service.InterBlogService;
import com.project.common.FileManager;
import com.project.common.MyUtil;
import com.project.common.SHA256;

@Controller
public class BlogMainController {
	
	@Autowired
	private InterBlogService service;
	
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/Blog")
	public ModelAndView BlogMain(HttpServletRequest request, ModelAndView mav) {
		
		List<BlogBigCategoryVO> bigcategoryList = service.getBigCategoryList();
		
		List<BlogSmallCategoryVO> smallcategoryList = service.getSmallCategoryList();
		
		String searchWord = request.getParameter("searchWord");
		
		String categoryno = request.getParameter("category");
		
		List<BlogBoardVO> blogboardList = null;	
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		String str_currentShowPageNo = request.getParameter("currentShowPageNo"); 
		
		int totalCount = 0;         // 총게시물 건수
		int sizePerPage = 5;       // 한 페이지당 보여줄 게시물 수 
		int currentShowPageNo = 0;  // 현재 보여주는 페이지번호로서, 초기치로는 1페이지로 설정함.
		int totalPage = 0;          // 총 페이지수(웹브라우저상에 보여줄 총 페이지 갯수, 페이지바) 
		
		int startRno = 0;           // 시작 행번호
		int endRno = 0;             // 끝 행번호
		
		HashMap<String,String> paraMap = new HashMap<String,String>(); 
		
		if(searchWord == null) {
			searchWord = "";
		}
		
		if(categoryno == null) {
			categoryno = "";
		}
		
		if(!"".equals(categoryno)) {

			String categoryname = null;
			
			categoryname = service.getCategoryName(categoryno);
			
			mav.addObject("categoryName", categoryname);
		}
		
		paraMap.put("searchWord", searchWord);
		paraMap.put("categoryno", categoryno);
		
		 // 검색조건이 없을 경우의 총 게시물 건수(totalCount)
		if("".equals(searchWord) && "".equals(categoryno)) {
			totalCount = service.getTotalCountWithNOsearch();
		}
		
		// 검색조건이 있을 경우의 총 게시물 건수(totalCount)
		else if(!"".equals(searchWord) && "".equals(categoryno)){
			totalCount = service.getTotalCountWithSearch(paraMap);
		} 
		
		else if(!"".equals(categoryno) && "".equals(searchWord)) {
			totalCount = service.getTotalCountWithCategory(paraMap);
		}
		
		totalPage = (int) Math.ceil( (double)totalCount/sizePerPage );  
		
		if(str_currentShowPageNo == null) {
			// 게시판에 보여지는 초기화면
			
			currentShowPageNo = 1;
			// 즉, 초기화면은  /list.action?currentShowPageNo=1 로 한다는 말이다.
		}
		else {
			
			try {
				  currentShowPageNo = Integer.parseInt(str_currentShowPageNo);
				
				  if(currentShowPageNo < 1 || currentShowPageNo > totalPage) {
					  currentShowPageNo = 1;
				  }
			} catch (NumberFormatException e) {
				  currentShowPageNo = 1;
			}
		}
	
		// **** 가져올 게시글의 범위를 구한다.(공식임!!!) **** // 
		startRno = ((currentShowPageNo - 1) * sizePerPage) + 1;
		endRno = startRno + sizePerPage - 1;
		
		paraMap.put("startRno", String.valueOf(startRno));
		paraMap.put("endRno", String.valueOf(endRno));
	
		blogboardList = service.BlogBoardListWithPaging(paraMap);
		
		// ==== #125. 페이지바 만들기 ==== // 
				String pageBar = "<ul>";
				
				int blockSize = 5;
				// blockSize 는 1개 블럭(토막)당 보여지는 페이지번호의 갯수 이다.
				/*
				    1 2 3 4 5 6 7 8 9 10           -- 1개 블럭 
				    11 12 13 14 15 16 17 18 19 20  -- 1개 블럭  
				*/
				
				int loop = 1;
				/*
				    loop 는 1부터 증가하여 1개 블럭을 이루는 페이지번호의 갯수(위의 설명상 지금은  10개(==blockSize))까지만 증가하는 용도이다. 
				*/
				
				int pageNo = ((currentShowPageNo - 1)/blockSize) * blockSize + 1;
				
				String url = "Blog.com";
			    
				String lastStr = url.substring(url.length()-1);
				if(!"?".equals(lastStr)) 
					url += "?"; 
				
				// *** [이전] 만들기 *** //    
					pageBar += "<li class='prev'><a href='"+url+"currentShowPageNo=1&sizePerPage="+sizePerPage+"&searchWord="+searchWord+"&category="+categoryno+"'><<</a></li>";
					pageBar += "<li class='prev prev1'><a href='"+url+"currentShowPageNo="+(currentShowPageNo-1)+"&sizePerPage="+sizePerPage+"&searchWord="+searchWord+"&category="+categoryno+"'><</a></li>";
				while( !(loop>blockSize || pageNo>totalPage) ) {
					
					if(pageNo == currentShowPageNo) {
						pageBar += "<li class='current'>"+pageNo+"</li>";
					}
					else {
						pageBar += "<li class='nocurrent'><a href='"+url+"currentShowPageNo="+pageNo+"&sizePerPage="+sizePerPage+"&searchWord="+searchWord+"&category="+categoryno+"'>"+pageNo+"</a></li>"; 
						       // ""+1+"&nbsp;"+2+"&nbsp;"+3+"&nbsp;"+......+10+"&nbsp;"
					}
					
					loop++;
					pageNo++;
				}// end of while---------------------------------
				
				pageBar += "<li class='next next1'><a href='"+url+"currentShowPageNo="+(currentShowPageNo+1)+"&sizePerPage="+sizePerPage+"&searchWord="+searchWord+"&category="+categoryno+"'>></a></li>";
				
				// *** [다음] 만들기 *** //
			
				pageBar += "<li class='next'><a href='"+url+"currentShowPageNo="+totalPage+"&sizePerPage="+sizePerPage+"&searchWord="+searchWord+"&category="+categoryno+"'>>></a></li>"; 
				
				
				pageBar += "</ul>";
		
		mav.addObject("pageBar", pageBar);
		mav.addObject("lastPageNo", totalPage);
		mav.addObject("currentShowPageNo", currentShowPageNo);		
		mav.addObject("blogboardList", blogboardList);
		mav.addObject("bigcategoryList", bigcategoryList);
		mav.addObject("smallcategoryList", smallcategoryList);
		
		mav.setViewName("blog/blogmain.tiles1");
		
		return mav; 
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
	}
	 
	@RequestMapping(value="/Blog/Board")
	public ModelAndView blogBoard(HttpServletRequest request, ModelAndView mav) {
		
		HttpSession session = request.getSession();
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		BlogGuestVO guestvo = new BlogGuestVO();
		
		
		
		String viewno = request.getParameter("no");
		String url = request.getParameter("url");
		
		List<BlogBigCategoryVO> bigcategoryList = service.getBigCategoryList();
		
		List<BlogSmallCategoryVO> smallcategoryList = service.getSmallCategoryList();
		
		HashMap<String,String> boardView = service.getBoardView(viewno);
		
		String like_count = service.getLike_count(viewno);
		
		String categoryname;
		
		String categoryno = boardView.get("category_snumber");
		categoryname = service.getCategoryName(categoryno);
		
		boardView.put("categoryname", categoryname);
		
		List<BlogBoardVO> cateRecentList = service.getCateRecentList(categoryno);
		
		BlogGuestVO loginuser = (BlogGuestVO)session.getAttribute("loginuser");
		
		if(loginuser != null) {
				
				HashMap<String, String> checkmap = new HashMap<String, String>();
				checkmap.put("userno", loginuser.getUserno());
				checkmap.put("viewno", viewno);
				
			String n = service.CheckLike(checkmap);
			
			mav.addObject("n", n);
			
		}
		
		mav.addObject("cateRecentList", cateRecentList);
		
		mav.addObject("url", url);
		
		mav.addObject("like_count", like_count);
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
	
	@RequestMapping(value="/Blog/loginGUEST")
	public ModelAndView blogLoginGuest(HttpServletRequest request, ModelAndView mav) {
		
		String userid = "";
		
		String username = "GUEST";
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		int idno;
		
		Random rd = new Random();
		
		idno = rd.nextInt((9900)+100);
		
		userid = "GUEST"+String.valueOf(idno);
		
		map.put("username", username);
		map.put("userid", userid);
		
		int n = service.editGuest(map);
		
		HttpSession session = request.getSession();
		
		if(n==1) {
			BlogGuestVO loginguest = service.getLoginGuest(userid);
			
			session.setAttribute("loginuser", loginguest);
			
			String msg = userid+"님 환영합니다.";
			String loc = request.getContextPath()+"Blog.com";
			
			mav.addObject("msg", msg);
			mav.addObject("loc",loc);
			mav.setViewName("msg");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/Blog/login", method= {RequestMethod.POST})
	public ModelAndView blogLoginUser(HttpServletRequest request, ModelAndView mav) {
		
		String userid = request.getParameter("inputid");
		String userpw = request.getParameter("inputpw");
		
		String loc="";
		
		if(userid != null && userpw != null) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("userpw", SHA256.encrypt(userpw));
		
		BlogGuestVO loginuser = service.isExistUser(map);
		
		
		if(loginuser == null) {
			String msg = "아이디 혹은 비밀번호가 잘못되었습니다.";
			loc = request.getContextPath()+"/Blog/Login.com";
			mav.addObject("msg", msg);
			mav.addObject("loc", loc);
			mav.setViewName("msg2");
			
		} else {
			
			HttpSession session = request.getSession();
			
			session.setAttribute("loginuser", loginuser);
			
			loc = request.getContextPath()+"Blog.com";
			
			mav.addObject("loc", loc);
			mav.setViewName("msg");
		}
		
		} else {
			String msg = "\"아이디 혹은 비밀번호가 잘못되었습니다.";
			loc = request.getContextPath()+"/Blog/Login.com";
			mav.addObject("msg", msg);
			mav.addObject("loc", loc);
			mav.setViewName("msg2");
		}
		
		
		return mav;
	}
	
	@RequestMapping(value="/Blog/logout")
	public ModelAndView blogLogout(HttpServletRequest request, ModelAndView mav) {
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		String msg = "로그아웃 되었습니다.";
		String loc = request.getContextPath()+"/Blog.com";
		
		mav.addObject("msg", msg);
		mav.addObject("loc", loc);
		mav.setViewName("msg");
		
		return mav;
	}
	
	@RequestMapping(value="/Blog/Write")
	public ModelAndView BlogWrite(HttpServletRequest request, ModelAndView mav) {
		
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")	// 경고 무시 선언
		HashMap<String, String> usermap = (HashMap<String, String>) session.getAttribute("loginuser");
		
		if(usermap == null) {
			String msg = "비정상적인 접근입니다.";
			String loc = request.getContextPath()+"/Blog.com";
			
			mav.addObject("msg", msg);
			mav.addObject("loc", loc);
			
			mav.setViewName("msg2");
		} else {
		
		List<BlogBigCategoryVO> bigcategoryList = service.getBigCategoryList();
		
		List<BlogSmallCategoryVO> smallcategoryList = service.getSmallCategoryList();
		
		mav.addObject("bigcategoryList", bigcategoryList);
		mav.addObject("smallcategoryList", smallcategoryList);
		
		mav.setViewName("blog/blogwrite.tiles1");
		
		}
		
		return mav;
	}
	
	@RequestMapping(value="/Blog/WriteEnd")
	public ModelAndView BlogWriteEnd(BoardVO boardvo, ModelAndView mav, MultipartHttpServletRequest mrequest) {
		
		MultipartFile writethumbnail = boardvo.getWritethumbnail();
		
		if(!writethumbnail.isEmpty()) {
			HttpSession session = mrequest.getSession();
 		 	String root = session.getServletContext().getRealPath("/");
 		 	String path = root + "resources" + File.separator + "files";
 		 	String path2 = "C:\\Git\\BlogFoilo\\BlogFolio\\src\\main\\webapp\\resources\\image";
 		 	String newFileName = "";
 		 	
 		 	byte[] bytes = null;
 		 	
 		 	long fileSize = 0;
 		 	
 		 try {
 			 
 		 	bytes = writethumbnail.getBytes();
 		 	
 		 	newFileName = fileManager.doFileUpload(bytes, writethumbnail.getOriginalFilename(), path2);
 		 	
 		 	/*boardvo.setThumbnail(newFileName);*/
 		 	boardvo.setThumbnail(newFileName);
 		 	
 		 	/*boardvo.setOrgFilename(writethumbnail.getOriginalFilename());*/
 		 	boardvo.setOrgFilename(writethumbnail.getOriginalFilename());
 		 	
 		 	fileSize = writethumbnail.getSize();
			
 		 	boardvo.setFileSize(String.valueOf(fileSize));
 		 	
			} catch(Exception e) {
				e.printStackTrace();
			}	
		
		}
		
		   boardvo.setTitle(MyUtil.replaceParameter(boardvo.getTitle()));
	 	   boardvo.setContent(MyUtil.replaceParameter(boardvo.getContent()));
	 	   boardvo.setContent(boardvo.getContent().replaceAll("\r\n", "<br/>"));
	 	   
	 	  int n = 0;
	 		if(writethumbnail.isEmpty()) {
	 			// 첨부파일이 없는 경우이라면
	 			n = service.writeEnd(boardvo); 
	 		}
	 		else {
	 			// 첨부파일이 있는 경우이라면
	 			n = service.writeEnd_withFile(boardvo);
	 		}
	 		
	 		String msg = "";
	 		String loc = "";
	 		
	 		if(n>0) {
	 			msg = "작성 완료";
	 			loc = mrequest.getContextPath()+"/Blog.com";
	 		} else {
	 			msg = "작성 실패";
	 			loc = "javascript:history.back()";
	 		}
	 		
	 		
	 		
	 		mav.addObject("msg", msg);
	 		mav.addObject("loc", loc);
	 		
	 		mav.setViewName("msg2");
		
		return mav;
	}
	
	@RequestMapping("/Blog/BoardDelete")
	public ModelAndView BlogWriteDel(HttpServletRequest request, ModelAndView mav) {
		
		String viewno = request.getParameter("no");
		
		int n = service.DelWrite(viewno);
		
		String loc="";
		String msg="";
		
		if(n == 1) {
			msg="삭제되었습니다.";
			loc=request.getContextPath()+"/Blog.com";
		} else {
			msg="다시 시도해주십시오.";
			loc="javascript:history.back()";
		}
		
		mav.addObject("msg", msg);
		mav.addObject("loc", loc);
		
		mav.setViewName("msg2");
		
		return mav;
		
	}
	
	@RequestMapping("/Blog/BoardEdit")
	public ModelAndView BlogWriteEdit(HttpServletRequest request, ModelAndView mav) {
		
		HttpSession session = request.getSession();
		
		String viewno = request.getParameter("no");
		
		@SuppressWarnings("unchecked")	// 경고 무시 선언
		HashMap<String, String> usermap = (HashMap<String, String>) session.getAttribute("loginuser");
		
		if(usermap == null) {
			String msg = "비정상적인 접근입니다.";
			String loc = request.getContextPath()+"/Blog.com";
			
			mav.addObject("msg", msg);
			mav.addObject("loc", loc);
			
			mav.setViewName("msg2");
			
		} else {
		
			BoardVO boardview = service.getBoardDetail(viewno);
			
			List<BlogBigCategoryVO> bigcategoryList = service.getBigCategoryList();
			
			List<BlogSmallCategoryVO> smallcategoryList = service.getSmallCategoryList();
			
			mav.addObject("bigcategoryList", bigcategoryList);
			mav.addObject("smallcategoryList", smallcategoryList);
			mav.addObject("boardview", boardview);
			
			mav.setViewName("blog/blogedit.tiles1");
		}
		
		return mav;
		
	}
	
	@RequestMapping("/Blog/WriteEditEnd")
	public ModelAndView BlogWriteEditEnd(BoardVO boardvo, ModelAndView mav, MultipartHttpServletRequest mrequest) {
		
		MultipartFile writethumbnail = boardvo.getWritethumbnail();
		
		if(!writethumbnail.isEmpty()) {
			HttpSession session = mrequest.getSession();
 		 	String root = session.getServletContext().getRealPath("/");
 		 	String path = root + "resources" + File.separator + "files";
 		 	String path2 = "C:\\Git\\BlogFoilo\\BlogFolio\\src\\main\\webapp\\resources\\image";
 		 	String newFileName = "";
 		 	
 		 	byte[] bytes = null;
 		 	
 		 	long fileSize = 0;
 		 	
 		 try {
 			 
 		 	bytes = writethumbnail.getBytes();
 		 	
 		 	newFileName = fileManager.doFileUpload(bytes, writethumbnail.getOriginalFilename(), path2);
 		 	
 		 	/*boardvo.setThumbnail(newFileName);*/
 		 	boardvo.setThumbnail(newFileName);
 		 	
 		 	/*boardvo.setOrgFilename(writethumbnail.getOriginalFilename());*/
 		 	boardvo.setOrgFilename(writethumbnail.getOriginalFilename());
 		 	
 		 	fileSize = writethumbnail.getSize();
			
 		 	boardvo.setFileSize(String.valueOf(fileSize));
 		 	
			} catch(Exception e) {
				e.printStackTrace();
			}	
		
		}
		
		   boardvo.setTitle(MyUtil.replaceParameter(boardvo.getTitle()));
	 	   boardvo.setContent(MyUtil.replaceParameter(boardvo.getContent()));
	 	   boardvo.setContent(boardvo.getContent().replaceAll("\r\n", "<br/>"));
	 	   
	 	  int n = 0;
	 		if(writethumbnail.isEmpty()) {
	 			// 첨부파일이 없는 경우이라면
	 			n = service.EditEnd(boardvo); 
	 		}
	 		else {
	 			// 첨부파일이 있는 경우이라면
	 			n = service.EditEnd_withFile(boardvo);
	 		}
	 		
	 		String msg = "";
	 		String loc = "";
	 		
	 		if(n>0) {
	 			msg = "수정 완료";
	 			loc = mrequest.getContextPath()+"/Blog/Board.com?no="+boardvo.getNum();
	 		} else {
	 			msg = "수정 실패";
	 			loc = "javascript:history.back()";
	 		}
	 		
	 		
	 		
	 		mav.addObject("msg", msg);
	 		mav.addObject("loc", loc);
	 		
	 		mav.setViewName("msg2");
		
		return mav;
	}
	
	@RequestMapping("/Blog/Like")
	public ModelAndView Like(HttpServletRequest request, ModelAndView mav) {
		
		HttpSession session = request.getSession();
		
		String viewno = request.getParameter("no");
		
		BlogGuestVO loginuser = (BlogGuestVO) session.getAttribute("loginuser");
		
		HashMap<String, String> paramap = new HashMap<String, String>();
		
		paramap.put("userno", loginuser.getUserno());
		paramap.put("userid", loginuser.getUserid());
		paramap.put("viewno", viewno);
		
		HashMap<String, String> checkmap = new HashMap<String, String>();
		checkmap.put("userno", loginuser.getUserno());
		checkmap.put("viewno", viewno);
		
		String msg = "";
		String loc = "";
		
		String check = service.CheckLike(checkmap);
		
		if("0".equals(check)) {
			int n = service.addLike(paramap);

			if(n==1) {
				msg = "감사합니다.";
				loc = request.getContextPath()+"/Blog/Board.com?no="+viewno;
			}
			
		} else {
			int n = service.cancelLike(paramap);
			
			if(n==1) {
				msg = "힝";
				loc = request.getContextPath()+"/Blog/Board.com?no="+viewno;
			}
		}
		
		mav.addObject("msg",msg);
		mav.addObject("loc", loc);
		
		mav.setViewName("msg2");
		
		return mav;
	}
	
}
