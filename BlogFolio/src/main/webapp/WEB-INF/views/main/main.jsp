<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>

<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/main.css" />

<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.0.min.js" ></script>
<script type="text/javascript">

$(function(){
	
	$("#logoB").click(function(){
		openBlogAreaHead();
	});
	
	$("#logoP").click(function(){
		openPortFolioAreaHead();
	});
	
	$("#main_BlogFolio").hover(function(){
		$(this).css("background-color", "rgba(0,0,0,0.1)");
	}, function(){
		$(this).css("background-color", "rgba(0,0,0,0.5)");	
	});
	
	$("#main_Blog").hover(function(){
		$(this).css("background-color", "rgba(0,0,0,0.1)");
	}, function(){
		$(this).css("background-color", "rgba(0,0,0,0.5)");
	});
	
	$("#main_PortFolio").hover(function(){
		$(this).css("background-color", "rgba(0,0,0,0.1)");
	}, function(){
		$(this).css("background-color", "rgba(0,0,0,0.5)");
	});
	
});


var logo = document.getElementsByClassName("main_logo");
var i;

function openBlogAreaHead(){
	document.getElementById("BlogAreaHead").style.width = "100%";
	for(i=0; i < logo.length; i++){
		logo[i].style.display = "none";
	}
}

function closeBlogAreaHead(){
	document.getElementById("BlogAreaHead").style.width = "0%";
	for(i=0; i < logo.length; i++){
		logo[i].style.display = "block";
	}
}

function openPortFolioAreaHead(){
	document.getElementById("PortFolioAreaHead").style.width = "100%";
	for(i=0; i < logo.length; i++){
		logo[i].style.display = "none";
	}
}

function closePortFolioAreaHead(){
	document.getElementById("PortFolioAreaHead").style.width = "0%";
	for(i=0; i < logo.length; i++){
		logo[i].style.display = "block";
	}
}

</script>

</head>

<body>

<!-- // 메인 화면 // -->
<div class="mainAreaHead" id="mainAreaHead">
<div class="mainArea">
<div class="main_logo" id="main_Blog" align="left"><a class="logo" id="logoB" href="javascript:void(0)"><span class="BlogSpan">Blog</span></a></div>
<div class="main_logo" id="main_BlogFolio" align="center"><a class="logo" href="<%=ctxPath%>/main.com"><span class="BlogFolioSpan">BlogFolio</span></a></div>
<div class="main_logo" id="main_PortFolio" align="right"><a class="logo" id="logoP" href="javascript:void(0)"><span class="PortFolioSpan">PortFolio</span></a></div>
</div>
</div>

<!-- // 블로그 hover // -->
<div class="BlogAreaHead" id="BlogAreaHead">
	<a href="javascript:void(0)" class="closeBtn" onclick="closeBlogAreaHead()">&times;</a>
<div class="BlogArea">
<div class="Blog_logo" id="Blog_Blog" align="left"><a class="goBlog" href="<%=ctxPath %>/Blog.com"><span class="eng">Blog </span><span class="kor">바로 가기</span></a></div>
</div>
</div>

<!-- // 포트폴리오 hover // -->
<div class="PortFolioAreaHead" id="PortFolioAreaHead">
	<a href="javascript:void(0)" class="closeBtn" onclick="closePortFolioAreaHead()">&times;</a>
<div class="PortFolioArea">
<div class="PortFolio_logo" id="PortFolio_Blog" align="left"><a class="goPortFolio" href="<%=ctxPath %>/PortFolio.com"><span class="eng">PortFolio </span><span class="kor">바로 가기</span></a></div>
</div>
</div>

</body>
</html>