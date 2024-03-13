<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<c:set var="id" value="hong" scope="page"/>
<c:set var="pwd" value="1234" scope="page"/>
<c:set var="name" value="${'홍길동'}" scope="page"/>
<c:set var="age" value="${22}" scope="page"/>
<c:set var="height" value="${'177'}" scope="page"/>

<c:if test="${false}">
    <h1>항상 참입니다.</h1>
</c:if>

<c:if test="${(id == 'hong') && (name == '홍길동')}" >
    <h1>아이디는 ${id}이고, 이름은 ${name}이다.</h1>
</c:if>

<c:if test="${age==22}" >
    <h1>${name}의 나이는 ${age}입니다.</h1>
</c:if>

<c:if test="${height >170}" var="result">
    ${result}
    <h1>${name}의 키는 ${height}이고 170보다 크다</h1>
</c:if>
<%--
c:if태그는 자바의 if문을 동일하게 제어 구문을 작성할 때 사용되는 태그
하지만 else가 없기 떄문에 일련의 조건을 나열하는 형태로 작성.

<c:if test="${조건식}" var="조건식의 결과를 저장할 변수" scope="영역">
    test속성의 조건식이 true일때 출력할 코드
</c:if>
--%>






