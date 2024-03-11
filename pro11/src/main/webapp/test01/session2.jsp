<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String address = (String)session.getAttribute("address");
String name = (String)session.getAttribute("name");
	
%>
<%=address %> <br><%=name %> 