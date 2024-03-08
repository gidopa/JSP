<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("user_id");
	String pw = request.getParameter("user_pw");
	if (id == null || id.length() == 0) {
	%>
	아이디를 입력하세요.
	<br>
	<a href="login.html">로그인하기</a>
	<%
	} else {
	if (id.equals("admin")) {
	%>
	<h1>관리자로 로그인</h1>
	<form>
		<input type="button" value="회원정보 수정"> <input type="button"
			value="회원정보 삭제">
	</form>
	<%
	} else {
	%>
	<h1>
		환영합니다
		<%=id%>님
	</h1>
	<%
	}
	}
	%>

</body>
</html>