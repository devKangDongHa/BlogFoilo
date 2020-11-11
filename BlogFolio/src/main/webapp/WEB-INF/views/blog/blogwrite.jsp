<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String ctxPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



<link rel="stylesheet" type="text/css" href="<%=ctxPath%>/resources/css/blogwrite.css" />

<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.0.min.js" ></script>
<script type="text/javascript">

	$(function(){

		$(".categorynum_View").each(function(){
			
			if($(this).val() == 301 ) {
				$(this).parent().parent().parent().css('display','none');
			}
		});
		
		$(".goCategory_Write").click(function(){
			
			var categoryNo = $(this).children().first().val();

			location.href = "<%=ctxPath%>/Blog.com?category="+categoryNo;
			
		});
		
		$("#category_number").change(function(){
			var bigval = $(this).val();
			if(bigval == 1) {
				$(".option1").css('display', 'block');
				$(".option2").css('display', 'none');
				$(".option3").css('display', 'none');
			} else if(bigval == 2) {
				$(".option1").css('display', 'none');
				$(".option2").css('display', 'block');
				$(".option3").css('display', 'none');
			} else if(bigval == 3) {
				$(".option1").css('display', 'none');
				$(".option2").css('display', 'none');
				$(".option3").css('display', 'block');
			} else {
				$(".option1").css('display', 'none');
				$(".option2").css('display', 'none');
				$(".option3").css('display', 'none');
			}
		});
		
		$("#btnWrite").click(function(){
			
			var writetitle = $("#title").val().trim();
			
			var writecategory_big = $("#category_number").val().trim();
			
			var writecategory_small = $("#category_snumber").val().trim();
			
			var writeheadcontent = $("#subcontent").val().trim();
			
			var writecontent = $("#content").val().trim();
			
			if(writetitle == "") {
				alert("제목을 입력하세요.");
				return;
			}
			
			if(writecategory_big == "") {
				alert("카테고리를 선택하세요.");
				return
			}
			
			if(writecategory_small == "") {
				alert("카테고리를 선택하세요.");
				return;
			}
			
			if(writeheadcontent == "") {
				alert("머리글을 입력하세요.");
				return
			}
			
			if(writecontent == "") {
				alert("내용을 입력하세요.");
				return
			}
			
			var frm = document.writeForm;
			frm.method = "POST";
			frm.action = "<%=ctxPath%>/Blog/WriteEnd.com";
			
			frm.submit();
			
		});
		
	});
	
</script>

</head>
<body>

	<div id="BlogContainer_Write">

		<div id="BlogMainContainer_Write">

			<div id="BlogLeftContainer_Write">
				
				<div id="BlogWriteContainer">
					<form name="writeForm" class="writeForm" enctype="multipart/form-data">
						<table class="writeTBL">
						<colgroup>
							<col style="width: 12%;">
							<col style="width: 38%;">
							<col style="width: 12%;">
							<col style="width: 38%">
						</colgroup>
							<tbody>
								<tr>
									<th scope="row">제 목</th>
									<td colspan="3"><input type="text" id="title" class="writeinput" name="title"/></td>
								</tr>
								<tr class="categorySelect">
									<th scope="row"><label for="category_number" class="writelabel">대 분 류</label></th>
									<td>
										<span class="select_category">
											<select id="category_number" class="writeselect" name="category_number">
												<option class="option" value="">-</option>
												<c:forEach var="bigcategoryListVO" items="${bigcategoryList }">
												<option class="option" value="${bigcategoryListVO.category_number }">${bigcategoryListVO.category_name }</option>
												</c:forEach>
											</select>
										</span>
									</td>
									<th><label for="category_snumber" class="writelabel">소 분 류</label></th>
									<td>
										<span class="select_category">
											<select id="category_snumber" class="writeselect" name="category_snumber">
												<option class="option" value="">-</option>
													<c:forEach var="smallcategoryListVO" items="${smallcategoryList }">
															<option style="display: none;" class="option option_small option${smallcategoryListVO.category_number }" value="${smallcategoryListVO.category_snumber }">${smallcategoryListVO.category_name }</option>
													</c:forEach>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<th scope="row">머<br>리<br>글</th>
									<td colspan="3">
										<textarea name="subcontent" id="subcontent" class="textarea" maxlength="300"></textarea>
									</td>
								</tr>
								<tr>
									<th scope="row">내<br><br><br>용</th>
									<td colspan="3">
										<textarea name="content" id="content" class="textarea"></textarea>
									</td>
								</tr>
								<tr>
									<th>썸네일</th>
									<td><input type="file" name="writethumbnail" id="writethumbnail" class="file"/></td>
								</tr> 
							</tbody>
						</table>
					</form>
					
					<div id="endbtnArea">
						<button type="button" id="btnWrite" class="btn">작성</button>
						<button type="button" onclick="javascript:history.back();" class="btn">취소</button>
					</div>
				</div>
				
			</div>




			<div id="BlogRightContainer_Write">
				<!-- // 카테고리 // -->
				<span class="cateTitle_Write">- 카테고리</span>
				<ul class="categoryParent_Write">
					<li><a href="<%=ctxPath%>/Blog.com">전체 글</a></li>
					<c:forEach var="bigcategoryListVO" items="${bigcategoryList }">
						<li><a class="goCategory_Write">${bigcategoryListVO.category_name }
								<input type="hidden" class="categorynum_Write"
								value="${bigcategoryListVO.category_number }" />
						</a><span class="cnt_Write">(${bigcategoryListVO.cnt })</span>
							<ul class="categoryChildren_Write">
								<c:forEach var="smallcategoryListVO"
									items="${smallcategoryList }">
									<c:if
										test="${bigcategoryListVO.category_number eq smallcategoryListVO.category_number}">
										<li><a class="goCategory_Write">${smallcategoryListVO.category_name }
												<input type="hidden" class="categorynum_Write"
												value="${smallcategoryListVO.category_snumber }" />
										</a><span class="cnt_Write">(${smallcategoryListVO.cnt })</span></li>

									</c:if>
								</c:forEach>
							</ul></li>
					</c:forEach>
				</ul>
			</div>

		</div>
	</div>

</body>
</html>