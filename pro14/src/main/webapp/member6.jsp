<%@page import="sec01.ex02.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
    %>
<jsp:useBean id="m" class="sec01.ex02.MemberBean"></jsp:useBean>
<jsp:setProperty property="*" name="m"/>
<jsp:useBean id="addr" class="sec01.ex02.Address"></jsp:useBean>
<jsp:setProperty property="city" name="addr" value="서울"/>
<jsp:setProperty property="zipcode" name="addr" value="07654"/>
<%
	m.setAddr(addr);
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
			<td width="5%">도시</td>
			<td width="5%">우편번호</td>
			</tr>
			<tr align="center" >
		
	<tr align="center">
		<td width="7%">${m.id}</td>
		<td width="7%">${m.pwd }</td>
		<td width="5%">${m.name }</td>
		<td width="11%">${m.email }</td>
		<td><%=m.getAddr().getCity() %></td>
		<td><%=m.getAddr().getZipcode() %></td>
	</tr>	
	<tr align="center">
			<td width="7%">${m.id}</td>
		<td width="7%">${m.pwd }</td>
		<td width="5%">${m.name }</td>
		<td width="11%">${m.email }</td>
		<td>${m.addr.city }</td>
		<td>${m.addr.zipcode }</td>
	</tr>	
		    <tr height="1" bgcolor="green">
		   		<td colspan="5"></td>
		    </tr>
	</table>		
</body>
</html>