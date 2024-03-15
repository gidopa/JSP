<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sec01.ex01.MemberVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("utf-8");
    List memberList = new ArrayList();
    MemberVO m1 = new MemberVO("son","1234", "손흥민","son");
    MemberVO m2 = new MemberVO("ki","1234", "기성용","ki");
    MemberVO m3 = new MemberVO("park","1234", "박지성","park");
    memberList.add(m1);
    memberList.add(m2);
    memberList.add(m3);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<c:set var="list" value="<%=memberList%>"/>
<table align="center" width="100%">
    <tr align="center" bgcolor="green">

        <td width="7%">아이디</td>
        <td width="7%">비밀번호</td>
        <td width="5%">이름</td>
        <td width="11%">이메일</td>
    </tr>
    <c:forEach var="i" begin="0" end="${list.size()-1}" >
    <tr align="center">
        <td width="7%">${list[i].id}</td>
        <td width="7%">${list[i].pwd}</td>
        <td width="5%">${list[i].name}</td>
        <td width="11%">${list[i].email}</td>
    </tr>
    </c:forEach>
    <tr align="center">
        <td colspan="4"> 다른 방법 </td>
    </tr>
    <c:forEach var="data" items="${list}">
    <tr align="center">
       <td>${data.id}</td>
       <td>${data.pwd}</td>
       <td>${data.name}</td>
       <td>${data.email}</td>
    </tr>
    </c:forEach>

    <c:forEach var="i" begin="0" end="${list.size()-1}">
        <%--위 list의 0~2 index위치에 저장된 MemberBean 객체를 차례로 얻어 저장--%>
        <c:set var="bean" value="${list[i]}"/>
        <ul>
            <li>아이디 : ${bean.id}, </li>
            <li>비밀번호 : ${bean.pwd}, </li>
            <li>이름 : ${bean.name}, </li>
            <li>이메일 : ${bean.email}, </li>
        </ul>
    </c:forEach>
</body>
</html>