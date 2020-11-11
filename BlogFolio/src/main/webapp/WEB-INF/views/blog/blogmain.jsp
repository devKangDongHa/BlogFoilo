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
		 
		var currentShowPageNo = "${currentShowPageNo}";
		var lastPageNo = "${lastPageNo}";
		
		if(currentShowPageNo == 1){
			$(".prev > a").prop('href', 'javascript:void(0)');
		}
		
		
		if(currentShowPageNo == lastPageNo){
			$(".next > a").prop('href', 'javascript:void(0)');
		}
		
		var prevurl = $(location).attr('href');
		
		$("#url").val(prevurl);
		
		$(".goCategory").click(function(){
			
			var categoryNo = $(this).children().first().val();

			location.href = "<%=ctxPath%>/Blog.com?category="+categoryNo;
			
		});

		$(".BlogContentTitle").click(function(){
			
			var viewno = $(this).children().first().val();
			
			$("#viewFrm").submit();
			
		});
		
		$(".categorynum").each(function(){
			
			if($(this).val() == 301 ) {
				$(this).parent().parent().parent().css('display','none');
			}
			
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
								<img src="<%=ctxPath %>/resources/image/${blogboardListVO.thumbnail}" width="250px" height="250px" alt=""/>
								</a>
							</td>
							<td class="BlogHeadLine">
								<a class="BlogContentTitle">${blogboardListVO.title }
								</a>
								<form name="viewFrm" id="viewFrm" action="<%=ctxPath%>/Blog/Board.com?no=${blogboardListVO.num }" method="POST" style="display: none;">
									<input type="hidden" class="url" id="url" name="url" />
								</form>
								<br><br>
								<span class="BlogCategory BlogCateDay">
									<a class="goCategory">${blogboardListVO.category }
										<input type="hidden" value="${blogboardListVO.category_snumber}" />
									</a>
								</span><span>/</span><span class="BlogWriteDay BlogCateDay">${blogboardListVO.writeday }</span><br><br><br>
								<span class="BlogHeadContent">${blogboardListVO.subcontent }</span>
							</td>
						</tr>
					</c:forEach>
					</c:if>
					
					<c:if test="${empty blogboardList}">
						<tr class="BlogList">
							<td>
								<span>검색하신 내용이 없습니다.</span>
							</td>
						</tr>
					</c:if>
				</tbody>
				</table>
			</div>
		
			<div id="paging" align="center">
				${pageBar}
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

	</div>

</body>
</html>