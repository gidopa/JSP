<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("UTF-8");%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
// /Rentcar
	String contextPath = request.getContextPath();
%>
<a href="<%=contextPath%>/Car/Main">
	<img src="<%=contextPath%>/img/RENT.jpg" width="300" height="80">
</a>
<table width="100%" background="<%=contextPath%>/img/aa.jpg" height="5" >
<!-- <table width="100%" background="contextPath/img/aa.jpg" height="5" > -->
	<tr>
		<td align="center" bgcolor="red">
		<!-- 예약을 위해 옵션을 선택해 검색 요청하는 화면 요청 -->
			<a href="<%=contextPath%>/Car/bb?center=CarReservation.jsp">
				<img alt="" src="<%=contextPath%>/img/bb.jpg" border="0"> <!-- 예약하기 메뉴 -->
			</a>
		</td>
		<td align="center" bgcolor="red">
			<a href="<%=contextPath%>/Car/cc?center=CarReserveConfirm.jsp">
				<img alt="" src="<%=contextPath%>/img/cc.jpg" border="0"> <!-- 예약 확인 메뉴 -->
			</a>
		</td>
		<td align="center" bgcolor="red">
			<a href="#">
				<img alt="" src="<%=contextPath%>/img/dd.jpg" border="0"> <!-- 자유게시판 메뉴 -->
			</a>
		</td>
		<td align="center" bgcolor="red">
			<a href="#">
				<img alt="" src="<%=contextPath%>/img/even.jpg" border="0"> <!-- 이벤트 메뉴 -->
			</a>
		</td>
		<td align="center" bgcolor="red">
			<a href="#">
				<img alt="" src="<%=contextPath%>/img/ee.jpg" border="0"> <!-- 고객센터 게시판 메뉴 -->
			</a>
		</td>
		
	</tr>
</table>
</body>
</html>