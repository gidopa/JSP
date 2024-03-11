<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// result2.jsp에서 jps:forward액션태그로부터 얻어오는 값
	String msg = request.getParameter("msg");
	if(msg != null){
		%>
		<h1><%= msg%></h1>
	<%}%>
	<form name="frmLogin" method="post" action="result2.jsp" encType="utf-8">
		아이디 : <input type="text" name="user_id"><br> 
		비밀번호 : <input type="password" name="user_pw"><br>
		<input type="submit" value="로그인"> 
		<input type="reset" value="다시입력">
	</form>
</body>
</html>