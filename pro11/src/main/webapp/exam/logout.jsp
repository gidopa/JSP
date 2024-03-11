<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//session 메모리 영역 제거
	session.invalidate();
	response.sendRedirect("index.jsp");
%>