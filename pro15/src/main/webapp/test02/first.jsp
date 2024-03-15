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
    <%--first.jsp에서는 다운로드할 이미지 파일 이름을 hidden 태그에 넣어 result.jsp로 전송--%>
    <form action="result.jsp" method="post">
        <input type="hidden" name="param1" value="duke.png"> <br>
        <input type="hidden" name="param2" value="duke2.png"> <br>
        <input type="submit" value="이미지 다운로드">
    </form>

</body>
</html>