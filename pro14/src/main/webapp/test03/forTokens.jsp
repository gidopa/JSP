<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<%--
    StringTokenizer처럼 구분자기호를 기준으로 문자여을 나눠 토큰 갯수만큼 반복
    <c:forTokens var="변수명" items="나눌 문자열" delims="구분자">
    <c:forTokens/>
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    String rgba ="red,green,blue,black";
%>
<c:forTokens var="col" items="<%=rgba%>" delims=",">
    <span style="color:${col}">
        ${col}
    </span>
    <br>
</c:forTokens>
</body>
</html>