<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%-- <fmt:setLocale value="en_US"/> --%>
<fmt:setLocale value="ko_KR"/>
    <h1>회원정보 <br><br>
    <fmt:bundle basename="resource.member">
        <fmt:message key="mem.name"/><br>
        <fmt:message key="mem.address"/><br>
        <fmt:message key="mem.job"/><br>

    </fmt:bundle>
    </h1>
</body>
</html>