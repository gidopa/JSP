<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
request.setAttribute("address", "서울시 강남구");
%>
<%-- member2.jsp로 디스패치 방식으로 포워드 --%>
<jsp:forward page="member2.jsp"></jsp:forward>