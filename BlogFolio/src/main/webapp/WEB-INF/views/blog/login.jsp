<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String ctxPath = request.getContextPath(); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/login.css" />
<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/common.css" />

<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.0.min.js" ></script>
<script type="text/javascript">

</script>

</head>
<body>

<div id="container_login">
<div id="title_login">
	<h1>LOGIN</h1>
</div>
	<div id="button_area">
		<div id="btn_area">
			<div id="mybtnArea">
				<div>
					<a class="loginbtn" id="mybtn">주인장용</a>
				</div>
			</div>
			<div id="guestbtnArea">
				<div>
					<a class="loginbtn" id="guestbtn">GUEST</a>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>