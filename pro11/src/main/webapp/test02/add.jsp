<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage="addException.jsp"
    %>
<%--
	errorPage 속성은 현재 페이지에서 예외 발생 시 예외를 처리할 서버페이지 주소를 지정
--%>
<%
	//add.html에서 입력받은 자연수 값을 얻어 합을 구해준다 
	//숫자가 아닌 문자열을 입력받았다면 NumberFomratException이 발생하고 그 예외처리페이지는 addException
	int num = Integer.parseInt(request.getParameter("num"));
	int sum = 0;
	for(int i = 1 ; i<=num ; i++){
		sum += i;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>1부터 <%=num%>까지의 합은 <%=sum %></h1>
</body>
</html>