<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("UTF-8");%> 
    <%
// /Rentcar
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="CarMain.jsp?center=NoticeListController.do">
	<img alt="" src="<%=contextPath%>/img/Main.jpg" width="100%">
</a>	
</body>
</html>