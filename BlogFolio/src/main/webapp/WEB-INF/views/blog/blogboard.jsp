<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/blogboard.css" />

<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.0.min.js" ></script>
<script type="text/javascript">
	
	$(function(){
		
		$(".goCategory_View").click(function(){
			
			var categoryNo = $(this).children().first().val();

			location.href = "<%=ctxPath%>/Blog.com?category="+categoryNo;
			
		});
		
		$(".goCategory").click(function(){
			
			var categoryNo = $(this).children().first().val();

			location.href = "<%=ctxPath%>/Blog.com?category="+categoryNo;
			
		});
		
	});
	
</script>

</head>
<body>

	<div id="BlogContainer_View">
	
		<div id="BlogMainContainer_View">

			<div id="BlogLeftContainer_View">

				<div id="BlogContentList_View">
					<div id="BlogContentHead_View">
						<h1>${boardView.title }</h1>
						<span class="BlogCategory BlogCateDay">
							<a class="goCategory" href="#">${boardView.categoryname}
								<input type="hidden" value="${boardView.category_snumber}" />
							</a>
							</span><span>/</span><span class="BlogWriteDay BlogCateDay">${boardView.writeday }</span>
					</div>
					<div id="BlogContentBody_View">
						<div id="Content_View">
							${boardView.content }
						</div>
					</div>
				</div>
				
				<div id="BackBtnArea">
					<div id="BackBtn">
						<a href="${url }">목 록</a>
					</div>
					<div id="MainBtn">
						<a href="<%=ctxPath %>/Blog.com">전체 글</a>
					</div>
				</div>

			</div>
			
			<div id="BlogRightContainer_View">
				<!-- // 카테고리 // -->
				<span class="cateTitle_View">- 카테고리</span>
				<ul class="categoryParent_View">
					<li><a href="<%=ctxPath%>/Blog.com">전체 글</a></li>
					<c:forEach var="bigcategoryListVO" items="${bigcategoryList }">
						<li><a class="goCategory_View">${bigcategoryListVO.category_name }
							<input type="hidden" class="categorynum_View" value="${bigcategoryListVO.category_number }" />
							</a><span class="cnt_View">(${bigcategoryListVO.cnt })</span>
							<ul class="categoryChildren_View">
								<c:forEach var="smallcategoryListVO" items="${smallcategoryList }">
									<c:if test="${bigcategoryListVO.category_number eq smallcategoryListVO.category_number}">
										<li><a class="goCategory_View">${smallcategoryListVO.category_name }
										<input type="hidden" class="categorynum_View" value="${smallcategoryListVO.category_snumber }" />
										</a><span class="cnt_View">(${smallcategoryListVO.cnt })</span></li>
										
									</c:if>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
				</ul>
			</div>
			
		</div>
	
	</div>

</body>
</html>