<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<c:set var="id" value="hong" scope="page"/>
<c:set var="pwd" value="1234" scope="page"/>
<c:set var="name" value="${'홍길동'}" scope="page"/>
<c:set var="age" value="${22}" scope="page"/>
<c:set var="height" value="${'177'}" scope="page"/>

<table border="1" align="center">
    <tr align="center" bgcolor="#90ee90">
        <td width="7%"><b>아이디</b></td>
        <td width="7%"><b>비밀번호</b></td>
        <td width="7%"><b>이름</b></td>
        <td width="7%"><b>나이</b></td>
        <td width="7%"><b>키</b></td>
    </tr>

    <c:choose>
        <%--page에 name변수가 저장되어 있지 않으면--%>
        <c:when test="${empty pageScope.name}">
            <tr align="center">
                <td colspan="5">이름을 입력하세요</td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr align="center">
                <td>${id}</td>
                <td>${pwd}</td>
                <td>${name}</td>
                <td>${age}</td>
                <td>${height}</td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>

<%--
    <c:choose>
        <c:when test="조건식1"> 코드 </c:when>
        <c:when test="조건식2"> 코드 </c:when>
        <c:otherwise> 모든 조건식을 만족하지 않을 경우 코드 </c:otherwise>
    </c:choose>
--%>