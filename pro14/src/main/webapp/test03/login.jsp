<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${'pageContext.request.contextPath'}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>아이디를 입력해주세요.</h1>
	<form name="frmLogin" method="post" action="memberForm.jsp" encType="utf-8">
		아이디 : <input type="text" name="user_id"><br> 
		비밀번호 : <input type="password" name="user_pw"><br>
		<input type="submit" value="로그인"> 
		<input type="reset" value="다시입력">
	</form>
<br><br>
<a href="${contextPath}/src/main/webapp/test03/membersForm.jsp"></a>
</body>
</html>