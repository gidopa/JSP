<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	.cls1{
		font-size:40px;
		text-align: center;
	}
	.cls2{
		font-size:20px;
		text-align: center;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${list.size()}
	<p class="cls1">회원 정보</p>
	<table align="center" border="1">
		<tr align="center" bgcolor="lightgreen">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>이메일</b></td>
			<td width="7%"><b>가입날짜</b></td>
		</tr>
		<c:choose>
			<c:when test="${empty list}">
				<tr align="center">
					<td colspan="5">등록된 회원이 없음
				</tr>
			</c:when>
			<c:otherwise>
			<c:forEach items="${list}" var="mem">
				<tr align="center">
			<td width="7%"><b>${mem.id}</b></td>
			<td width="7%"><b>${mem.pwd}</b></td>
			<td width="7%"><b>${mem.name}</b></td>
			<td width="7%"><b>${mem.email}</b></td>
			<td width="7%"><b><c:out value="${mem.joinDate}"/></b></td>
		</tr>
		</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	<a href="${pageContext.request.contextPath}/member/memberForm.do">
		<p class="cls2">회원가입하러가기</p>
	</a>
	

</body>
</html>