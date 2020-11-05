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
	
	$(function(){
		
		$("#loginContainer").hide();
		
		$("#mybtn").click(function(){
			
			$("#mybtnArea").hide();
			$("#loginContainer").show();
			
		});
		
		$("#loginbtn").click(function(){
			goLogin();
		});
		$("#inputpw").keydown(function(key) {
	        if (key.keyCode == 13) {
	            goLogin();
	        }
	    });
		
	});

	function loginGUEST(){
		location.href="<%=ctxPath%>/Blog/loginGUEST.com";
	}
	
	function goLogin(){
		var inputid = $("#inputid").val().trim();
		var inputpw = $("#inputpw").val().trim();
		
		if(inputid == ""){
			alert("아이디를 입력하세요.");
			$("#inputid").focus();
			return;
		}
		if(inputpw == ""){
			alert("비밀번호를 입력하세요.");
			$("#inputpw").focus();
			return;
		}
		
		var frm = document.loginFrm;
		frm.method = "POST";
		frm.action = "<%=ctxPath%>/Blog/login.com";
		frm.submit();
	}


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
			
			<div id="loginContainer">
				<form name="loginFrm">
					<div id="idbox">
					<label for="inputid" class="label">ID : </label>
					<input type="text" class="input" name="inputid" id="inputid" />
					</div>
					<div id="pwbox">
					<label for="inputpw" class="label">PW : </label>
					<input type="password" class="input" name="inputpw" id="inputpw" />
					</div>
					<div id="loginbtn">
					<span id="loginBtn">로그인</span>
					</div>
				</form>			
			</div>
			
			<div id="guestbtnArea">
				<div>
					<a class="loginbtn" id="guestbtn" onclick="loginGUEST();">GUEST</a>
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>