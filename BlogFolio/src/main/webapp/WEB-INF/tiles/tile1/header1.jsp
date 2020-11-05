<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String ctxPath = request.getContextPath(); %>

<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/header1.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.0.min.js" ></script>
<script type="text/javascript">
	
function goSearch(){
	var frm = document.searchFrm;
	frm.method="GET";
	frm.action="<%=ctxPath%>/Blog.com";
	frm.submit();
}

function loginPopUp(){
	window.open("<%=ctxPath%>/Blog/Login.com", "로그인", "width=500, height=500, left=700, top=300");
}

$(function(){
	
	$("#loginMenuContainer").css('display', 'none');
	
	$("#loginMenu").click(function(){
		
		$("#loginMenuContainer").toggle();
		
	});
	
	
});
	
	function logout(){
		location.href = "<%=ctxPath%>/Blog/logout.com";
	}
</script>

<div id="BlogHeader">   
   <div id="BlogHeaderArea">
   	
   	<!-- // header 메뉴 바 // -->
   		<div id="BlogHeadMenuArea">
   			<div id="BlogMenuBox">
		   		<ul id="BloaHeadMenuBar">
		   		<c:if test="${empty sessionScope.loginuser}">
		   			<li class="BlogHeadMenu"><a onclick="loginPopUp();">Login</a><span>ㅣ</span></li>
		   		</c:if>
		   		<c:if test="${sessionScope.loginuser.username == 'GUEST'}">
		   			<li class="BlogHeadMenu"><a href="javascript:void(0);">${sessionScope.loginuser.userid }</a><span>ㅣ</span></li>
		   		</c:if>
		   		<c:if test="${sessionScope.loginuser.userno == '1' }">
		   			<li class="BlogHeadMenu" id="loginMenu">
		   			<a>${sessionScope.loginuser.userid }</a><span>ㅣ</span>
		   			<div id="loginMenuContainer">
		 			<ul>
		 				<li><a>write</a></li>
		 				<li><a onclick="logout();">logout</a></li>
		 			</ul>
		 			</div>
		   			</li>
		   		</c:if>
		   			<li class="BlogHeadMenu"><a href="<%=ctxPath%>/main.com">Main</a><span>ㅣ</span></li>
		   			<li class="BlogHeadMenu"><a onclick="showContact()">Contact</a><span>ㅣ</span></li>
		   			<li class="BlogHeadMenu" style="width:85px;"><a href="https://www.instagram.com/hack2___" target="_blank"><img src="<%=ctxPath %>/resources/image/instagram.png" width="15px" height="15px" /> Instagram</a></li>
		   		</ul>
   			</div>
   		</div>
 
 		<div id="image_">
 			<img src="<%=ctxPath %>/resources/image/guideline.png" width="100%" height="100%" />
 		</div>
 		
 
   	<!-- // header 메인 메뉴 // -->
   		<div id="BlogHeadMainMenuArea">
   				<ul id="BlogHeadMainMenuBar">
   					<li class="BlogHeadMainMenu"><a href="<%=ctxPath %>/Blog.com">HOME</a></li>
   					<li class="BlogHeadMainMenu"><a href="<%=ctxPath%>/Blog/Comments.com">COMMENTS</a></li>
   					<li class="BlogHeadMainMenu"><a href="<%=ctxPath%>/Blog/About.com">ABOUT</a></li>
   				</ul>	
   		</div>
   		
   		<!-- // 제목 // -->
   		<div id="headerTitle">
   			<span><a href="<%=ctxPath%>/Blog.com">여백의 미</a></span>
   		</div>
   		
   		<!-- // 검색 창 // -->
   		<div id="headerSearch">
   			<form id="headerSearchForm" name="searchFrm">
   			    <i class="fa fa-search" style="font-size: 25px;"></i>
   				<input id="headerSearchInput" name="headerSearchInput" type="text" placeholder="Search.." autocomplete="off" />
   			</form>
   		</div>
   		
   </div>
</div>