<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 클라이언트가 이상한 주소로 입력하면 예외 발생
// 자바 코드 이상하게 써놓으면 예외 발생 -> ?num= 안붙히거나 이상한거 붙히면 발생 
	Integer.parseInt(request.getParameter("num"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>