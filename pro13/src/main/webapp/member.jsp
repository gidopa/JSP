<%@page import="java.util.List"%>
<%@page import="pro13.MemberDAO"%>
<%@page import="pro13.MemberBean"%>
<%@page import="java.util.Date"%>
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
<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	
	MemberBean m = new MemberBean(id,pwd,name,email);
	
	MemberDAO memberDAO = new MemberDAO();
	memberDAO.addMember(m);
	
	List membersList = memberDAO.listMembers();
%>

<table align="center" width="100%">
	<tr align="center" bgcolor="#99ccff">
		<td width='7%'>아이디</td>
		<td width='7%'>비밀번호</td>
		<td width='5%'>이름</td>
		<td width='11%'>이메일</td>
		<td width='5%'>가입일</td>
	</tr>	
<%
	if(membersList.size() == 0 ){
%>
<tr>
	<td colspan="5">
		<p align="center"></p><b><span style="font-size:9pt;">등록된 회우너이 없습니다</span></b> 
	</td>
</tr>
<%
	}else{
		for(int i=0;i<membersList.size();i++){
			MemberBean bean = (MemberBean) membersList.get(i);
			%>
		<tr align="center">
			<td><%=bean.getId() %></td>
			<td><%=bean.getPwd() %></td>
			<td><%=bean.getName() %></td>
			<td><%=bean.getEmail() %></td>
			<td><%=bean.getJoinDate() %></td>
		</tr>	
		<%
		}
	}
%>	
<tr height="1" bgcolor="#99ccff">
	<td colspan='5'></td>
</tr>
</table>
</body>
</html>