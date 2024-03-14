<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <c:out value="안녕하세요"/> <br>
<c:out value="${2*3}"/>
<c:out value="${requestScope.memberbean.id}" default="빈공백대신 출력해줄 기본값"/>
<abc>는 abc입니다. <br> <%--<abc>는 html태그로 인식해 출력안됨 --%>
&lt;abc>는 abc입니다
    <c:out value="<abc>는 abc"/>
</body>
</html>