<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%--
	다른 jsp페이지에서 예외발생 시 예외를 처리하는 예외처리 전용페이지로 설정해 처리하는 페이지
	add.jsp에서 예외 발생시 현재 addException.jsp로 오기전 톰캣 서버는 exception 내장객체 메모리를 생성해
	발생한 예외메세지를 저장해 두고 공유 해줌.
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>숫자를 입력하세요 !! 말 좀 들어라</h1>
	
	<%= exception.getMessage() %>
	<%-- <pre><%= exception.toString() %></pre>
	<%= exception.printStackTrace() %> --%>
</body>
</html>