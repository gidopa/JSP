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
		<%-- request 메모리에 requestScope로 접근해 MemberBean 객체를 얻고
			MemberBean 객체의 각 인스턴스변수에 저장된 값 불러와 EL로 출력
		--%>
		<tr>
			<td>${requestScope.memberBean.id}</td>
			<td>${requestScope.memberBean.pwd}</td>
			<td>${requestScope.memberBean.name}</td>
			<td>${requestScope.memberBean.email}</td>

		</tr>
		    <tr height="1" >
		   		<td colspan="5"></td>
		    </tr>
	</table>		
</body>
</html>