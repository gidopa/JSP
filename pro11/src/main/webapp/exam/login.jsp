<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="frmLogin" method="post" action="login.jsp" encType="utf-8">
		아이디 : <input type="text" name="user_id"><br> 
		비밀번호 : <input type="password" name="user_pw"><br>
		<input type="submit" value="로그인"> 
		<input type="reset" value="다시입력">
	</form>
	<%
	System.out.println(request.getMethod());
	// form태그의 요청 방식이 post일때 
	if (request.getMethod().equals("POST")) {
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");

		//DB 테이블에 저장되어 있는 값이 id "master"와 비밀번호 "1111"이라고 가정
		//DB의 id, pw와 입력된 값 비교
		if ("master".equals(id)) {
			if ("1111".equals(pw)) {
		//session 메모리에 입력한 아이디 바인딩
		session.setAttribute("id", id);
		response.sendRedirect("index.jsp");
			} else {
	%>
	<script>
		alert("비밀번호가 틀립니다.");
	</script>
	<%
	}
	} else {
	if ("1111".equals(pw)) {
	%>
	<script>
		alert("아이디가 틀립니다")
	</script>
	<%
	} else {
		%>
		<script>
			alert("아이디,비밀번호가 틀립니다")
		</script>
		<%
	}
	}
	}
	%>
</body>
</html>