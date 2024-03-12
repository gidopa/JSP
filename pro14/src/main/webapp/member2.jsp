<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
    %>
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
			<td width="11%">이메일</td>
			<td width="20%">주소</td>
			</tr>
	<%-- EL에서 제공하는 param을 이용해 request 내장 메모리에 접근 --%>
	<tr align="center">
		<td>${param.id}</td>
		<td>${param.pwd}</td>
		<td>${param.name}</td>
		<td>${param.email}</td>
		<%-- forward.jsp에서 request.setAttribute("address","서울시 강남구")로 바인딩한 값을 가져옴 --%>
		<td>${requestScope.address}</td>
	</tr>
	
		    <tr height="1" bgcolor="green">
		   		<td colspan="5"></td>
		    </tr>
	</table>		
</body>
</html>