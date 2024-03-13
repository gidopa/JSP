<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${empty param.user_id}">
    아이디를 입력하세요.<br>
    <a href="login.jsp">로그인창</a>
</c:if>
<c:if test="${!(empty param.user_id)}">
    <c:if test="${param.user_id == 'admin'}">
        <h1>관리자 로그인</h1>
    </c:if>
    <c:if test="${param.user_id != 'admin'}">
        <h1>환영합니다 ${param.user_id}님</h1>
    </c:if>
</c:if>
</body>
</html>
