<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sec01.ex02.MemberBean" %>
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
			</tr>
		<%
			//request내장객체 영역에 접근해 ArrayList를 얻고 ArrayList배열에 저장된 MemberBean 객체의 갯수만큼
			//반복하여 인스턴스변수 값들을 출력
			List list = (ArrayList) request.getAttribute("list");
			for(int i=0;i<list.size();i++){
			MemberBean member = (MemberBean) list.get(i);
		%>
			<tr align="center">
			<td><%=member.getId()%></td>
			<td><%=member.getPwd()%></td>
			<td><%=member.getName()%></td>
			<td><%=member.getEmail()%></td>
			</tr>
		<tr align="center">
			<td><%=((MemberBean)list.get(i)).getId()%></td>
			<td><%=((MemberBean)list.get(i)).getPwd()%></td>
			<td><%=((MemberBean)list.get(i)).getName()%></td>
			<td><%=((MemberBean)list.get(i)).getEmail()%></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td>${requestScope.list[0].id}</td>
			<td>${requestScope.list[0].pwd}</td>
			<td>${list[0].name}</td>
			<td>${list[0].email}</td>

		</tr>
		<tr>
			<td>${list[1].id}</td>
			<td>${list[1].pwd}</td>
			<td>${requestScope.list[1].name}</td>
			<td>${requestScope.list[1].email}</td>

		</tr>
		    <tr height="1" >
		   		<td colspan="5"></td>
		    </tr>
	</table>		
</body>
</html>