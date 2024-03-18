<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("UTF-8");%> 
    <%
// /Rentcar
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr width="100%" color="red" >
	<img alt="" src="<%=contextPath%>/img/bo.jpg" width="500" height="50" border="0" >
	<font size="2">
		<b>
			<img alt="" src="<%=contextPath%>/img/sodog.jpg" border="0"> |
			<img alt="" src="<%=contextPath%>/img/info.jpg" border="0">> | 이용약관 | 인재채용
		</b>
		<br><br>
		<small>
			(주)SM렌탈 사업자 등록번호 214-98754-4315 통신 판매업신고 번호 : 제2010-충남--5호 <br>
			
			서울시 강남구 역삼동 역삼빌딩 2층 21호<br><br>
			
			대표전화 : 02-3546-6547 <br>
			FAX : 01-33432-32432
		</small>
	</font>
</body>
</html>