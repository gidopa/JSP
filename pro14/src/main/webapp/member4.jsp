<%@page import="sec01.ex01.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
    %>
<jsp:useBean id="list" class="java.util.ArrayList"></jsp:useBean>

<%--2,3 요청한 값을 얻어 memberBean에 담는다 --%>
<jsp:useBean id="bean" class="sec01.ex01.MemberVO"></jsp:useBean>
<jsp:setProperty property="*" name="bean"/>
<%
MemberVO mb2 = new MemberVO("son", "1234", "손흥민", "son@test.com");
	list.add(mb2);
	list.add(bean);
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
			</tr>
	<%-- EL로 MemberBean 객체의 각 변수에 저장된 값을 얻어 출력 --%>
	<tr>
		<td>${list[0].id }</td>
		<td>${list[0].pwd }</td>
		<td>${list[0].name }</td>
		<td>${list[0].email }</td>
	</tr>	
	<tr>
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