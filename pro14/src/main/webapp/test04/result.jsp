<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 605
  Date: 2024-03-13
  Time: 오후 5:57
  To change this template use File | Settings | File Templates.
--%>
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
        <h1>환영합니다 ${param.user_id}님</h1>
    </c:if>
</body>
</html>
