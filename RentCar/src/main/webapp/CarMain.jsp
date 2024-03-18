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
<!-- 센터 중앙화면은 상위 메뉴를 선택했을때 해당 페이지로 변화
request로부터 중앙화면의 view주소를 얻어와 변수에 저장 해야함 -->
<c:set var="center" value="${requestScope.center}"></c:set>
<c:out value="${center}"/>
<c:if test="${center == null }">
<c:set var="center" value="Center.jsp"/>
</c:if>
<center>
	<table width="100%" height="100%">
		<tr align="left">
			<td height="25%"><jsp:include page="Top.jsp"/></td>
		</tr>
		<tr>
			<td height="50%"><jsp:include page="${center}"/></td>
		</tr>
		<tr>
			<td height="25%"><jsp:include page="Bottom.jsp"/></td>
		</tr>
	</table>
</center>
</body>
</html>