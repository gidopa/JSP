<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h2>포멧팅태그ㅇ의 formatNumber태그를 이용한 숫자 포맷팅 예제</h2>
<c:set var="price" value="100000000"/>
<fmt:formatNumber value="${price}" type="number" var="priceNumber"/>
<%--
    type 속성 : 출력될 타입을 지정
    1. percent : %형태로 변환해 출력
    2. number : 세자리마다 ,를 찍은 금액 형태의 숫자
    3. currency : 통화 형식으로 변환해 출력
--%>
<br>
<fmt:formatNumber value="${price}" type="currency" currencySymbol="$"/>
${priceNumber}
<c:out value="${priceNumber}"/>
<fmt:formatNumber type="percent" value="${price}" groupingUsed="false"/>
<fmt:formatNumber type="percent" value="0.1" groupingUsed="false"/>

<c:set var="date" value="<%=new Date()%>"/>
<fmt:formatDate value="${date}" type="both" dateStyle="short"/>
<%--
    type속성의 값
    1. date : 날짜만 출력
    2. time : 시간만 출력
    3. both : 날짜 시간 모두 출력
    dateStyle(날짜 변환 형식) 속성의 값
    1. full : YYYY년 MM월 DD일 목요일 오후 hh : mm : ss
    2. long : YYYY년 MM월 DD일 오후 hh: mm : ss
    3. medium : YYYY.MM.DD 오후 hh : mm : ss
    4. short : yy.MM.DD 오후 hh : mm : ss
--%>
<%-- pattern 속성에 Date 객체의 정보를 개발자가 원하는 날짜와 시간 형식으로 반환--%>
<fmt:formatDate value="${date}" pattern="YYYY-MM-dd hh:mm:ss" var="newVar"/>
${newVar}
</body>
</html>