<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Member" %>
<%@ page import="sec01.ex01.MemberBean" %>
<%@ page import="java.util.HashMap" %>
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
    <h1>List에 값 저장 후 c:forEach 돌리기</h1>
    <%
        List<MemberBean> lists = new ArrayList<>();
        lists.add(new MemberBean("son", "1234", "손흥민", "son@test.com"));
        lists.add(new MemberBean("park", "1234", "박지성", "park@test.com"));
        lists.add(new MemberBean("ki", "1234", "기성용", "ki@test.com"));
    %>
<c:set var="list" value="<%=lists%>" scope="page"/>
<ul>
    <c:forEach var="bean" items="${list}" varStatus="loop">
        <li>
            ${loop.count}번쨰 MemberBean 회원 -> 이름 : ${bean.name}  아이디 : ${bean.id}
        </li>
    </c:forEach>
</ul>
    <h1>Map에 돌리기</h1>
<%
    HashMap<String,MemberBean> maps = new HashMap<>();
    maps.put("1",new MemberBean("son", "1234", "손흥민", "son@test.com"));
    maps.put("2",new MemberBean("park", "1234", "박지성", "park@test.com"));
    maps.put("3",new MemberBean("ki", "1234", "기성용", "ki@test.com"));
%>
<c:set var="map" value="<%=maps%>"/>
    <ul>
<c:forEach var="bean" items="${map}">
   <li>
       key => ${bean.key},  이름 = ${bean.value.name}, 아이디 = ${bean.value.id}
   </li>
</c:forEach>
    </ul>
<h4>1에서 100까지 정수 중 홀수의 합</h4>
<c:forEach var="i" begin="1" end="100" varStatus="loop">
    <c:if test="${i % 2 != 0}">
        <c:set var="sum" value="${sum+i}"/>
    </c:if>
</c:forEach>
${sum}
</body>
</html>