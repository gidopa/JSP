<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
	out 내장객체
	클라이언트의 웹브라우저로 출력할 출력스트림, 서블릿의 PrintWriter역할
	
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="out2.jsp" method="post">
		이름 : <input type="text" name="name"><br>
		나이 : <input type="text" name="age"><br>
		<input type="submit" value="전송">
			
	</form>
</body>
</html>