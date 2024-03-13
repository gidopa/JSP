<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>

<c:set var="number" value="100" scope="page"/>
<h4>choose 태그로 홀짝 판단하기</h4>
<c:choose>
    <c:when test="${number %2 == 0}">
        ${number}는 짝수
    </c:when>
    <c:otherwise>
        ${number}는 홀수
    </c:otherwise>
</c:choose>

<h1>국어, 영어, 수학 점수 입력하여 평균 계산해서 학점 출력</h1>
<%--form의 기본값으로 action="자기자신" method="get"

--%>
<form >
    <input type="text" placeholder="국어" name="kor"> <br><br>
    <input type="text" placeholder="영어" name="eng"> <br><br>
    <input type="text" placeholder="수학" name="math"> <br><br>
    <input type="submit" value="학점 요청">
</form>
<c:set var="kor" value="${param.kor}"/>
<c:set var="eng" value="${param.eng}"/>
<c:set var="math" value="${param.math}"/>

<c:if test="${!(empty kor) && !(empty eng) && !(empty math)}">
    <c:set var="avg" value="${(kor + eng + math) /3 }"/>
<c:choose>
    <c:when test="${avg >= 90}">
        A
    </c:when>
    <c:when test="${avg >= 80}">
        B
    </c:when>
    <c:when test="${avg >= 70}">
        C
    </c:when>
    <c:when test="${avg >= 60}">
        D
    </c:when>
    <c:otherwise>
        F
    </c:otherwise>
</c:choose>

</c:if>



