<%@page import="pro13.MemberDAO" %>
<%@page import="java.util.List"%>
<%@ page import="pro13.MemberBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");

	//2. 요청한 값 얻기 (가입을 위해 입력한 값들을 request객체 메모리에서 꺼내오기)
	String id = request.getParameter("id");//입력한 아이디 얻기
	String pwd = request.getParameter("pwd");//입력한 비밀번호 얻기 
	String name = request.getParameter("name");//입력한 이름 얻기 
	String email = request.getParameter("email");//입력한 이메일 얻기 
	%>

	<jsp:useBean id="memberBean" class="pro13.MemberBean" scope="page"/>
	<%-- 
		2. 요청한값 얻기
	 --%>
	<jsp:setProperty name="memberBean" property="id" value='<%=request.getParameter("id") %>'/>
	<jsp:setProperty name="memberBean" property="pwd" value='<%=request.getParameter("pwd") %>'/>
	<jsp:setProperty name="memberBean" property="name" value='<%=request.getParameter("name") %>'/>
	<jsp:setProperty name="memberBean" property="email" value='<%=request.getParameter("email") %>'/>
<%
	//MemberDAO객체를 생성해서 addMember메소드 호출시 addMember메소드의 매개변수로
	//DB에 insert할 정보가 저장된 MemberBean객체의 주소 전달


	MemberDAO memberDAO = new MemberDAO();
			  memberDAO.addMember(memberBean);
	
	//DB에 insert 성공하면 다시 조회해서 가져와 보여주자
	List membersList = memberDAO.listMembers();//SELECT
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
			<td width="5%">가입일</td>
		</tr>
		<tr align="center" ">
			<td width="7%"><jsp:getProperty name="memberBean" property="id"/></td>
			<td width="7%"><jsp:getProperty name="memberBean" property="pwd"/></td>
			<td width="5%"><jsp:getProperty name="memberBean" property="name"/></td>
			<td width="11%"><jsp:getProperty name="memberBean" property="email"/></td>
			<td width="5%"><jsp:getProperty name="memberBean" property="joinDate"/></td>
		</tr>
		    <tr height="1" bgcolor="green">
		   		<td colspan="5"></td>
		    </tr>
	</table>		

</body>
</html>





















