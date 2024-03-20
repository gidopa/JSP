<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- JSTL 전체라이브러리에 속한 core에 속한 태그들 사용을 위해  반드시 작성해야 하는 한줄 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- Customer 스타일 추가 -->

<style type="text/css">
body {
	/* background-image: url('./loginbg.png'); */
	background-size: 100%;
	font-size: 0.75rem;
}

#loginBoxTitle {
	color: #000000;
	font-weight: bold;
	font-size: 1.9rem;
	text-transform: uppercase;
	padding: 5px;
	margin-bottom: 20px;
	background: linear-gradient(to right, #270a09, #8ca6ce);
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
}

input[type="button"] {
	font-size: 0.75rem;
	padding: 5px 10px;
}

.login-box {
	margin: 150px auto;
	background-color: rgba(255, 255, 255, 1);
	border-radius: 10px;
	padding: 40px 30px;
	border: 5px solid #0e0e0e;
	width: 350px;
	filter: drop-shadow(0px 0px 10px rgba(0, 0, 0, .5));
}

.form-group label {
	font-size: 0.75rem;
	margin: 5px 0;
}

#login-btn-box {
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-top: 10px;
}
</style>



</head>
<body class="">
	<div id="container">
		<!-- login Box -->
		<div class="login-box">
		  <%--MemberController서블릿에.. 로그인 처리 요청시! 입력한 id와 패스워드 전달 --%>
      	<form class="form-signin" action="<%=request.getContextPath()%>/member/loginPro.me" id="join"> 
			<div id="loginBoxTitle">CodeZone Login</div>
			<div class="form-group">
				<label>고객 아이디</label> 
				<input type="text" name="uid" id="uid"
					   class="form-control" value=""
					  >
			</div>
			<div class="form-group">
				<label>비밀번호</label> 
				<input type="password" name="upw" id="upw"
						class="form-control" value="" autocomplete="off">
			</div>
			<div id="login-btn-box">
				<div style="">
					<span> 아이디 저장</span> 
					<input type="checkbox" id="workSite"
							name="worksite" style="margin-bottom: 5px">
				</div>
				<div style="">
					<input type="submit" id="btnLogin" value="로그인" class="btn btn-danger">
				</div>
			</div>
		</form>	
		</div>
		<!-- login Box //-->
	</div>

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>
