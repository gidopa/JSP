<%@ page import="java.util.Optional" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("utf-8");
    //포워딩받은 member7.jsp 화면, 각 내장객체에 바인딩된 데이터를 꺼내 변수에 저장
    String id = (String) request.getAttribute("id");
    String pwd = (String) request.getAttribute("pwd");
    String name = (String) session.getAttribute("name");
    String email = (String) application.getAttribute("email");
    Optional<String> id_opt = Optional.ofNullable(id);
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
    <%-- EL로 MemberBean 객체의 각 변수에 저장된 값을 얻어 출력 --%>
    <tr>
        <td><%=id%></td>
        <td><%=pwd%></td>
        <td><%=name%></td>
        <td><%=email%></td>
    </tr>
    <tr>
        <td>${requestScope.id}</td>
        <td>${requestScope.pwd}</td>
        <td>${sessionScope.name}</td>
        <td>${applicationScope.email}</td>
    </tr>
    <tr height="1" bgcolor="green">
        <td colspan="5"></td>
    </tr>
</table>
</body>
</html>