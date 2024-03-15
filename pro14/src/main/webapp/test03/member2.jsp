<%@ page import="sec01.ex01.MemberVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    request.setCharacterEncoding("utf-8");
    %>
<jsp:useBean id="membersList" class="java.util.ArrayList"/>
<jsp:useBean id="membersMap" class="java.util.HashMap"/>
<%
membersMap.put("id","park");
	membersMap.put("pwd","1234");
	membersMap.put("name","박지성");
	membersMap.put("email","park@test.com");

	MemberVO m1 = new MemberVO("son","1234","손흥민","son@test.com");
	MemberVO m2 = new MemberVO("ki","1234","기성용","ki@test.com");

	membersList.add(m1);
	membersList.add(m2);
	membersMap.put("list", membersList);
%>
<c:set var="list" value="${membersMap.list}"/>
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
			</tr>
	<%-- EL로 MemberBean 객체의 각 변수에 저장된 값을 얻어 출력 --%>
	<tr align="center">
		<td>${membersMap.id }</td>
		<td>${membersMap.pwd }</td>
		<td>${membersMap.name }</td>
		<td>${membersMap.email }</td>
	</tr>
		<tr align="center">
			<td>${list[0].id }</td>
			<td>${list[0].pwd }</td>
			<td>${list[0].name }</td>
			<td>${list[0].email }</td>
		</tr>
		<tr align="center">
			<td>${list[1].id }</td>
			<td>${list[1].pwd }</td>
			<td>${list[1].name }</td>
			<td>${list[1].email }</td>
		</tr>
		    <tr height="1" bgcolor="green">
		   		<td colspan="5"></td>
		    </tr>
	</table>		
</body>
</html>