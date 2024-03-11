<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// Application 내장객체 (Servlet Context)
	session.setAttribute("name", "이순신");
	application.setAttribute("address", "서울시 강남구");
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이름과 주소를 각각 내장객체에 바인딩 후 다른 서버페이지 재요청</h1>
	<a href="appTest2.jsp">두번 째 웹 페이지로 이동</a>
	<!--  
		결론
		application 내장객체는 톰캣서버에 올라가있어서 다른 브라우저에도 변수값등이 저장되지만 
		session의 경우 브라우저 내에서만 동작하기 떄문에 다른 브라우저로 열게 되면 session의 값은 넘어가지 않는다.
	 -->
	
</body>
</html>