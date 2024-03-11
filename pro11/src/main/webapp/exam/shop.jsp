<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to shop</h1>
	<%
		String new_id = (String)session.getAttribute("id");
		if(new_id != null){
			%>
			<%=new_id %>로그인중....   
			<a href="logout.jsp">로그아웃</a>
			<a href="shop.jsp">쇼핑몰</a>
	<%
		}else{
	%>
			<script type="text/javascript">
				alert("로그인 안된 상태이니 로그인 하고 오세요");
				alert("로그인 요청 화면으로 이동");
				location.href="login.jsp";
			</script>
<%} %>		
</body>
</html>