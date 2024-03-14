<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 중에서 core태그들을 사용하기 위해 주소를 import --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
    
<%-- JSTL 중에서 fomatting태그들을 사용하기 위해 주소를 import --%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>       
    
<% request.setCharacterEncoding("UTF-8"); %>   
  
<%--
	 <fmt:setLocale>태그
	 - 국가별로  다른 통화 기호나  날짜를 표현할때 사용하는 태그 
	
 --%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>로케일 설정</h4>
	
	<c:set var="today"  value="<%= new Date() %>"  scope="page" />
	
	한글로 설정 : <fmt:setLocale value="ko_kr"/>
	<fmt:formatNumber value="10000" type="currency" /> /
	<fmt:formatDate value="${today}"  />
	<br>
	
	일어로 설정 : <fmt:setLocale value="ja_JP"/>
	<fmt:formatNumber value="10000" type="currency" /> /
	<fmt:formatDate value="${today}"  />
	<br>
	
	
	영어로 설정 : <fmt:setLocale value="en_US"/>
	<fmt:formatNumber value="10000" type="currency" /> /
	<fmt:formatDate value="${today}"  />
	<br>
		
</body>
</html>










