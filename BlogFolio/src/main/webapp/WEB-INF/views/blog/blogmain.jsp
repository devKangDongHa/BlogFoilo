<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/blogmain.css" />

<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.0.min.js" ></script>
<script type="text/javascript">
	
	$(function(){
		
		$(".goCategory").click(function(){
			
			var categoryNo = $(this).children().first().val();

			location.href = "<%=ctxPath%>/Blog.com?category="+categoryNo;
			
		});

		$(".BlogContentTitle").click(function(){
			
			var viewno = $(this).children().first().val();
			
			location.href = "<%=ctxPath%>/Blog/Board.com?no="+viewno;
			
		});
		
	});
	
</script>

</head>
<body>

	<div id="BlogContainer">

	<div id="categoryname">
		<c:if test="${not empty categoryName }">
			<span>${categoryName }</span>
		</c:if>
	</div>
	
	<div id="BlogMainContainer">
		
		<div id="BlogLeftContainer">
			
			<!-- // 글 목록 // -->
			<div id="BlogContentList">
				<table>
				<tbody>		
				<!-- // 페이지 로딩 시, 홈 화면 // -->			
					<c:if test="${not empty blogboardList}">
					<c:forEach var="blogboardListVO" items="${blogboardList }">
						<tr class="BlogList">
							<td class="BlogThumbNail">
								<a class="BlogContentTitle">
								<input type="hidden" class="viewno" value="${blogboardListVO.num}" />
								<img src="<%=ctxPath %>/resources/image/${blogboardListVO.thumbnail}" width="100%" height="100%" alt="${blogboardListVO.thumbnail }"/>
								</a>
							</td>
							<td class="BlogHeadLine">
								<a class="BlogContentTitle">${blogboardListVO.title }
									<input type="hidden" class="viewno" value="${blogboardListVO.num}" />
								</a><br><br>
								<span class="BlogCategory BlogCateDay">
									<a class="goCategory" href="#">${blogboardListVO.category }</a>
								</span><span>/</span><span class="BlogWriteDay BlogCateDay">${blogboardListVO.writeday }</span><br><br><br>
								<span class="BlogHeadContent">${blogboardListVO.subcontent }</span>
							</td>
						</tr>
					</c:forEach>
					</c:if>
					
					<!-- // 검색 시 // -->
					<c:if test="${not empty blogboardListSearch}">
					<c:forEach var="blogboardListSearchVO" items="${blogboardListSearch }">
						<tr class="BlogList">
							<td class="BlogThumbNail">
								<a class="BlogContentTitle">
								<input type="hidden" class="viewno" value="${blogboardListSearchVO.num}" />
								<img src="<%=ctxPath %>/resources/image/${blogboardListSearchVO.thumbnail}" width="100%" height="100%" alt="${blogboardListSearchVO.thumbnail }"/>
								</a>
							</td>
							<td class="BlogHeadLine">
								<a class="BlogContentTitle">${blogboardListSearchVO.title }
									<input type="hidden" class="viewno" value="${blogboardListSearchVO.num}" />
								</a><br><br>
								<span class="BlogCategory BlogCateDay">
									<a class="goCategory" href="#">${blogboardListSearchVO.category }</a>
								</span><span>/</span><span class="BlogWriteDay BlogCateDay">${blogboardListSearchVO.writeday }</span><br><br><br>
								<span class="BlogHeadContent">${blogboardListSearchVO.subcontent }</span>
							</td>
						</tr>
					</c:forEach>
					</c:if>
					
					<!-- // 카테고리 선택 시 // -->
					<c:if test="${not empty blogboardListCategory}">
					<c:forEach var="blogboardListCategoryVO" items="${blogboardListCategory }">
						<tr class="BlogList">
							<td class="BlogThumbNail">
								<a class="BlogContentTitle">
								<input type="hidden" class="viewno" value="${blogboardListCategoryVO.num}" />
								<img src="<%=ctxPath %>/resources/image/${blogboardListCategoryVO.thumbnail}" width="100%" height="100%" alt="${blogboardListCategoryVO.thumbnail }"/>
								</a>
							</td>
							<td class="BlogHeadLine">
								<a class="BlogContentTitle">${blogboardListCategoryVO.title }
									<input type="hidden" class="viewno" value="${blogboardListCategoryVO.num}" />
								</a><br><br>
								<span class="BlogCategory BlogCateDay">
									<a class="goCategory" href="#">${blogboardListCategoryVO.category }</a>
								</span><span>/</span><span class="BlogWriteDay BlogCateDay">${blogboardListCategoryVO.writeday }</span><br><br><br>
								<span class="BlogHeadContent">${blogboardListCategoryVO.subcontent }</span>
							</td>
						</tr>
					</c:forEach>
					</c:if>
					<c:if test="${empty blogboardListSearch and empty blogboardList and empty blogboardListCategory }">
						<tr class="BlogList">
							<td>
								<span>검색하신 내용이 없습니다.</span>
							</td>
						</tr>
					</c:if>
				</tbody>
				</table>
			</div>
		
		</div>
		
		<div id="BlogRightContainer">
			<!-- // 카테고리 // -->
			<span class="cateTitle">- 카테고리</span>
			<ul class="categoryParent">
				<li><a href="<%=ctxPath%>/Blog.com">전체 글</a></li>
				<c:forEach var="bigcategoryListVO" items="${bigcategoryList }">
					<li><a class="goCategory">${bigcategoryListVO.category_name }
						<input type="hidden" class="categorynum" value="${bigcategoryListVO.category_number }" />
						</a><span class="cnt">(${bigcategoryListVO.cnt })</span>
						<ul class="categoryChildren">
							<c:forEach var="smallcategoryListVO" items="${smallcategoryList }">
								<c:if test="${bigcategoryListVO.category_number eq smallcategoryListVO.category_number}">
									<li><a class="goCategory">${smallcategoryListVO.category_name }
									<input type="hidden" class="categorynum" value="${smallcategoryListVO.category_snumber }" />
									</a><span class="cnt">(${smallcategoryListVO.cnt })</span></li>
									
								</c:if>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</ul>
		</div>
		
	</div>

	<div id="paging">
		<span>1 2 3 4 5 ...</span>
	</div>

	</div>

</body>
</html>