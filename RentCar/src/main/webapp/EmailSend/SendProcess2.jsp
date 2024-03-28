<%@page import="smtp.NaverSMTP"%>
<%@page import="smtp.SMTPServer"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 이메일 전송 처리 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
    <c:when test="${success}">
        <p>이메일 전송 성공</p>
    </c:when>
    <c:otherwise>
        <p>이메일 전송 실패</p>
    </c:otherwise>
</c:choose>
</body>
</html>