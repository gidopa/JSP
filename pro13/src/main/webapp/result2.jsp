<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%!
	// 아이디를 입력하지 않고 로그인 버튼 눌렀을때 재요청 후 전달할 오류메세지를 변수에 저장
	String msg = "아이디를 입력하지 않았습니다. 아이디를 입력하세요";
%>
<%
	request.setCharacterEncoding("utf-8");	
	String userID = request.getParameter("user_id");
	String userPW = request.getParameter("user_pw");
	
	// id를 입력하지 않았다면 다시 login.jsp를 디스패처방식으로 포워딩
	// RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	// rd.forward(request, response);
	if(userID.length() == 0 || userID == null){
%>
<%-- forward 액션 태그 --%>
<jsp:forward page="login2.jsp">
	<jsp:param value="<%=msg %>" name="msg"/>
</jsp:forward>
<% }%>
<h1>ㅎㅇ</h1>
</body>
</html>