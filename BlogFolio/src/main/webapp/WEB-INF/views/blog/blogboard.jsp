<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String ctxPath = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/blog/blogboard.css" />

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
		
		$(".goView").click(function(){
			
			var no = $(this).children().children().val();
			
			location.href = "<%=ctxPath%>/Blog/Board.com?no="+no;
			
		});
		
		$(".categorynum_View").each(function(){
			
			if($(this).val() == 301 ) {
				$(this).parent().parent().parent().css('display','none');
			}
			
		});
		
	});
	
		function goLike(num){
			
			var viewno = num;
			
			location.href="<%=ctxPath%>/Blog/Like.com?no="+viewno;
			
		}
		
		function goEdit(num){
			var viewno = num;
			
			location.href="<%=ctxPath%>/Blog/BoardEdit.com?no="+viewno;
			
		}
		
		function goDel(num){
			var viewno = num;
			
			 if (confirm("정말 삭제하시겠습니까??") == true){    //확인

			     location.href="<%=ctxPath%>/Blog/BoardDelete.com?no="+viewno;

			 }else{   //취소

			     return false;

			 }
			 
		}
		
		function loginalert(){
			alert("로그인이 필요합니다.");
		}
	
</script>

</head>
<body>

	<div id="BlogContainer_View">
	
		<div id="BlogMainContainer_View">

			<div id="BlogLeftContainer_View">

			<c:if test="${sessionScope.loginuser.userno == '1' }">
				<div id="editDelArea">
					<div id="editArea">
						<a onclick="goEdit(${boardView.num});">수정</a>
					</div>
					<div id="delArea">
						<a onclick="goDel(${boardView.num});">삭제</a>
					</div>
				</div>
			</c:if>

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
				
				<div id="buttonArea">
					<div id="goodArea">
					<c:if test="${ not empty sessionScope.loginuser }">
					<c:if test="${n eq 0 }">
						<a onclick="goLike('${boardView.num}');">좋아요(${like_count})</a>
					</c:if>
					<c:if test="${n eq 1 }">
						<a onclick="goLike('${boardView.num}');">좋아요 취소(${like_count })</a>
					</c:if>	
					</c:if>
					<c:if test="${ empty sessionScope.loginuser }">
						<a onclick="loginalert();">좋아요(${like_count})</a>
					</c:if>
					</div>
					<div id="commentArea">
						<a>댓글</a>
					</div>
				</div>
				
				<div id="cateList">
					<div id="cateListmenu">
						<div id="thisListArea">
							<a id="categoryname">${boardView.categoryname }</a><a class="thisList"> 카테고리의 최신 글</a>
						</div>
						<div id="allListArea">
							<a class="allList" href="<%=ctxPath%>/Blog.com">전체 글</a>
						</div>
					</div>
					
					<div id="cateListArea">
						<table id="cateListTBL">
							<tbody id="cateListTBD">
							<c:forEach var="cateRecentListVO" items="${cateRecentList }">
							<tr>
									<td class="cateListTitle goView"><a>${cateRecentListVO.title }<input type="hidden" name="no" id="no" value="${cateRecentListVO.num}" /></a></td>
									<td class="cateListDate"><span>${cateRecentListVO.writeday }</span></td>
							</tr>
							</c:forEach>
							</tbody>
						</table>
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