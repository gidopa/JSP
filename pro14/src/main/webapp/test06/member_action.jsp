<%@ page import="sec02.ex01.MemberDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<jsp:useBean id="member" class="sec02.ex01.MemberBean"/>
<jsp:setProperty name="member" property="*"/>
<%
    MemberDAO dao = new MemberDAO();
    dao.addMember(member); // 회원 정보 추가
    List list = dao.listMembers();
    request.setAttribute("list",list);
%>
<c:set var="list" value="<%=list%>" scope="request"/>
<jsp:forward page="membersList.jsp"/>
</body>
</html>
