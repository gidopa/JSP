<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CarList.jsp</title>
</head>
<body>
	<center>
		<img alt="" src="${contextPath}/img/cis.jpg">
		<form action="${contextPath}/Car/carCategory.do">
			<table width="1000" height="470">
			<c:set var="j" value="0"/>
			<!-- CarController로 부터 재요청받은 request 영역에서 Vector 배열을 꺼내와 
			CarListrVO객체의 갯수만큼 반복해 얻어 정보를 출력 -->
			<c:out value="${requestScope.vector.size()}"/>

			<c:forEach var="vo" items="${requestScope.vector}">
			<!-- 한 행에 4열의 정보를 보여주기 위해 조건 -->
				<c:if test="${j%4 == 0 }">
					<tr align="center">
					</c:if>
						<td>
							<a href="${contextPath}/Car/CarInfo.do?carNo=${vo.carNo}">
								<img alt="" src="${contextPath}/img/${vo.carImg}" width="220" height="180"><br>
								차량명 : ${vo.carName} <br>
								렌트비 : ${vo.carPrice} <br>
							</a>
						</td>
						<c:set var="j" value="${j+1}"/>
					
				
			</c:forEach>
			<tr height="70">
				<td colspan="4" align="center">
					차량검색 :
					<select name="carCategory">
						<option value="Small">소형</option>
						<option value="Mid">중형</option>
						<option value="Big">대형</option>
					</select>
					&nbsp;&nbsp;&nbsp;
					<input type="submit" value="차량검색">
				</td>
			</tr>
			</table>
		</form>
	</center>
</body>
</html>