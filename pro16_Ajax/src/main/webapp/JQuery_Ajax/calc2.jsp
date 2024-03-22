<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		response.setContentType("text/html;charset=utf-8");
		int v1 = Integer.parseInt(request.getParameter("v1"));
		int v2 = Integer.parseInt(request.getParameter("v2"));
	%>
	{"v1":<%=v1%>, "v2":<%=v2 %>, "result":<%=v1+v2 %>}
</body>
</html>