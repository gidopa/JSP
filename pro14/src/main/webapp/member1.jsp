<%@page import="sec01.ex01.MemberDAO"%>
<%@page import="sec01.ex01.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 입력한 가입 정보중 한글이 하나라도 존재하면 한글이 깨져서 받아와 지므로
	//   문자처리 방식 UTF-8방식으로 request객체 메모리에 설정
	request.setCharacterEncoding("UTF-8");

	//2. 요청한 값 얻기 (가입을 위해 입력한 값들을 request객체 메모리에서 꺼내오기)
	String id = request.getParameter("id");//입력한 아이디 얻기
	String pwd = request.getParameter("pwd");//입력한 비밀번호 얻기 
	String name = request.getParameter("name");//입력한 이름 얻기 
	String email = request.getParameter("email");//입력한 이메일 얻기 
	
	
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
	<%-- 자바코드로 request.getParameter메소드 호출해 가져온 정보들 표현식으로 출력 --%>
	<tr align="center">
		<td><%=id %></td>
		<td><%=pwd %></td>
		<td><%=name %></td>
		<td><%=email %></td>
	</tr>
	<%-- EL에서 제공하는 param을 이용해 request 내장 메모리에 접근 --%>
	<tr align="center">
		<td>${param.id}</td>
		<td>${param.pwd}</td>
		<td>${param.name}</td>
		<td>${param.email}</td>
	</tr>
	
		    <tr height="1" bgcolor="green">
		   		<td colspan="5"></td>
		    </tr>
	</table>		
</body>
</html>





















