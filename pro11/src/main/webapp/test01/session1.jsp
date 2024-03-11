<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%
		String name = (String)session.getAttribute("name");
		session.setAttribute("address", "서울시 강남구");
	%>
	이름은 <%=name %>임.<br>
	<a href="/pro11/test01/session2.jsp">session2.jsp</a>

