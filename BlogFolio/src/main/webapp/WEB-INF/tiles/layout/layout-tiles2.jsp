<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<% String ctxPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BlogFolio</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.0.min.js" ></script>

<style type="text/css">
body{
	margin: 0;
}

</style>
 	
</head>
<body>
	<div id="mycontainer">
	
	<div id="myheader">
		<tiles:insertAttribute name="header2" />
	</div>
		
	<div id="mycontent">
		<tiles:insertAttribute name="content2" />
	</div>
		
	<div id="myfooter">
		<tiles:insertAttribute name="footer2" />
	</div>
	
	</div>
</body>
</html>