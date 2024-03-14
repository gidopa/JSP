<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <c:set var="title1" value="hello world!" />
    <c:set var="title2" value="쇼핑몰 중심 JSP"/>
    <c:set var="str1" value="중심"/>
    title1 = "hello world!" <br>
    title2 = "쇼핑물 중심 JSP입니다!" <br>
    str1 = "중심" <br><br>
    fn:length(title1) -> ${fn:length(title1)} <br>
    fn:toUpperCase(title1) -> ${fn:toUpperCase(title1)} <br>
    fn:toLowerCase(title1) -> ${fn:toLowerCase(title1)} <br>
    <%--hello world!
        01234567891011
        subString 시작인덱스는 포함 끝나는 인덱스는 미포함--%>
    fn:subString(title1,3,6) -> ${fn:substring(title1, 3, 6)} <br>
    fn:trim(" 안 녕 하 세 요 ") -> ${fn:trim(" 안 녕 하 세 요 ")} <br>
    fn:replace(title1, " ", /) -> ${fn:replace(title1, " ", "/")} <br>
    fn:indexOf(title2,str1) -> ${fn:indexOf(title2,str1)} <br>
    fn:contains(title1,str1) -> ${fn:contains(title1,str1)} <br>
    fn:contains(title2,str1) -> ${fn:contains(title2,str1)} <br>
    <c:set var="v1" value="${fn:contains(title2,str1)}" />
    ${v1} <br>

</body>
</html>