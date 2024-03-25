<%@page import="java.util.List"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	// JSON 파일을 String으로 setAttribute 해주고
	String responseStr = (String)request.getAttribute("responseString");
	/*
	GSon 라이브러리 ?
	자바용 JSON 라이브러리		
	JSON 객체를 스트링으로 스트링형태를 JSON 객체로 변호나
	*/		
	Gson gson = new Gson();
	//JSON 문자열을 자바 객체로 변환
	Map<String, Object> responseData = gson.fromJson(responseStr, Map.class);
	List<Map<String,String>> items = (List<Map<String,String>>)responseData.get("items");
	
	%>
</body>
</html>