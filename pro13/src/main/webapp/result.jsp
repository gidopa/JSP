<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");	
	String userID = request.getParameter("user_id");
	String userPW = request.getParameter("user_pw");
	
	// id를 입력하지 않았다면 다시 login.jsp를 디스패처방식으로 포워딩
	// RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	// rd.forward(request, response);
	if(userID.length() == 0){
%>
<%-- forward 액션 태그 --%>
<jsp:forward page="login.jsp"/>
<% }%>
<h1>ㅎㅇ</h1>
</body>
</html>