<%@page import="sec02.ex01.MemberDAO"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sec02.ex01.MemberBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
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
			<td width="11%">가입일</td>
		</tr>

		<%
			List<MemberBean> list = (ArrayList) request.getAttribute("list");
			if(list == null){%>

			<tr>
				<td colspan="5">조회되는 결과 없음</td>
			</tr>
			<%}
			else{

				for(MemberBean bean : list){
					%>

				<tr align="center">
					<td width="7%"><%bean.getId();%></td>
					<td width="7%"><%bean.getPwd();%></td>
					<td width="7%"><%bean.getName();%></td>
					<td width="7%"><%bean.getEmail();%></td>
					<td width="7%"><%bean.getJoinDate();%></td>
				</tr>
	</table>
					<%
				}
			}
		%>
	<%--<c:choose>
		 &lt;%&ndash;
			ArrayList list = (ArrayList)request.getAttribute("list");
		&ndash;%&gt;
		<c:when test="${empty requestScope.list }">
			<tr>
				<td colspan="5">조회되는 결과 없음</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}">
				<tr align="center">
					<td width="7%">${member.id}</td>
					<td width="7%">${member.pwd}</td>
					<td width="7%">${member.name}</td>
					<td width="7%">${member.email}</td>
					<td width="7%">${member.joinDate}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
		<tr height="1" bgcolor="green">
		   		<td colspan="5"></td>
		</tr>
	</table>		--%>
</body>
</html>





















