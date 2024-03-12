<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
    %>
<%--2,3 요청한 값을 얻어 memberBean에 담는다 --%>
<jsp:useBean id="bean" class="sec01.ex01.MemberBean"></jsp:useBean>
<jsp:setProperty property="*" name="bean"/>
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
		<td>${bean.id }</td>
		<td>${bean.pwd }</td>
		<td>${bean.name }</td>
		<td>${bean.email }</td>
	</tr>	
		    <tr height="1" bgcolor="green">
		   		<td colspan="5"></td>
		    </tr>
	</table>		
</body>
</html>