<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 첫번째 웹 페이지에서 각 내장객체에 바인딩한 값들 가져오기
	String name = (String)session.getAttribute("name");
	String address = (String)application.getAttribute("address");
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이름은 <%=name %></h1>
	<h1>주소는 <%=address %></h1>
</body>
</html>