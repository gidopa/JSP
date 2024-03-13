<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL 라이브러리에 속한 core 태그 사용을 위해 작성해야하는 코드 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    request.setCharacterEncoding("utf-8");
%>
<c:set var="id" value="hong" scope="page"/>
<c:set var="pwd" value="1234" scope="page"/>
<c:set var="name" value="${'홍길동'}" scope="page"/>
<c:set var="age" value="${22}" scope="page"/>
<c:set var="height" value="${'177'}" scope="page"/>

<%--
    c:remove 태그
    c:set을 통해 만든 변수를 삭제할때 사용. jsp에서 내장객체에 저장된 변수를 제거하는 removeAttribute()와 같은 역할
    -문법
        <c:remove var="변수이름" scope="변수가 저장될 내장객체 영역명"/>
     scope 속성의 값을 쓰지 않으면 모든 영역에서 해당 변수를 찾음

     여기서 변수를 삭제하더라도 el태그는 예외처리를 빈공백으로 해주기 떄문에 NullPointerException이 발생하지 않고 빈 공백으로 출력
--%>
<c:remove var="age" scope="page"/>
<c:remove var="height"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table align="center" width="100%">
    <tr align="center" bgcolor="green">

        <td width="7%">아이디</td>
        <td width="7%">비밀번호</td>
        <td width="5%">이름</td>
        <td width="5%">나이</td>
        <td width="5%">키</td>
    </tr>
    <tr>
        <td>${id}</td>
        <td>${pwd}</td>
        <td>${name}</td>
        <td>${age}</td>
        <td>${height}</td>

    </tr>
    <tr height="1" >
        <td colspan="5"></td>
    </tr>
</table>
</body>
</html>