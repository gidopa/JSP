<%@page import="sec01.ex01.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
    %>
<jsp:useBean id="membersList" class="java.util.ArrayList"></jsp:useBean>
<jsp:useBean id="map" class="java.util.HashMap"></jsp:useBean>
<%
	//HashMap 객체에 key, value 한쌍형태의 데이터 저장
	map.put("id", "park2");
	map.put("pwd", "4321");
	map.put("name", "박지성");
	map.put("email", "park@test.com");
%>
<%--2,3 요청한 값을 얻어 memberBean에 담는다 --%>
<jsp:useBean id="bean" class="sec01.ex01.MemberBean"></jsp:useBean>
<jsp:setProperty property="*" name="bean"/>
<%
	MemberBean mb2 = new MemberBean("son", "1234", "손흥민", "son@test.com");
	membersList.add(mb2);
	membersList.add(bean);
	// HashMap 내부에 ArrayList 배열 추가
	map.put("membersList",membersList);
	
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
			<tr align="center" >
		
			<td width="7%">${map.id}</td>
			<td width="7%">${map.pwd }</td>
			<td width="5%">${map.name }</td>
			<td width="11%">${map.email }</td>
			</tr>
	<%-- EL로 MemberBean 객체의 각 변수에 저장된 값을 얻어 출력 --%>
	<tr align="center">
		<td width="7%">${map.membersList[0].id }</td>
		<td width="7%">${map.membersList[0].pwd }</td>
		<td width="5%">${map.membersList[0].name }</td>
		<td width="11%">${map.membersList[0].email }</td>
	</tr>	
	<tr align="center">
		<td width="7%">${map.membersList[1].id }</td>
		<td width="7%">${map.membersList[1].pwd }</td>
		<td width="5%">${map.membersList[1].name }</td>
		<td width="11%">${map.membersList[1].email }</td>
	</tr>	
		    <tr height="1" bgcolor="green">
		   		<td colspan="5"></td>
		    </tr>
	</table>		
</body>
</html>