<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>아이디를 입력해주세요.</h1>
	<form name="frmLogin" method="post" action="result.jsp" encType="utf-8">
		아이디 : <input type="text" name="user_id"><br> 
		비밀번호 : <input type="password" name="user_pw"><br>
		<input type="submit" value="로그인"> 
		<input type="reset" value="다시입력">
	</form>
	<br><br>
	<%--
		EL 표현언어에서 제공해주는 pageContext내장객체 
		- pageContext내장객체는 javax.servlet.jsp.pageContext클래스를 상속해
		  톰캣 컨테이너가 JSP 파일 실행 시 자동으로 생성해서 제공해 주는 내장객체입니다.
		  
		  컨텍스트 주소를 직접 a의 href 속성에 작성해서 사용
		  <a href="/pro14/test01/memberForm.jsp">회원가입</a>
		  
		  자바의 request객체의 getContextPath 메소드를 호출해 클라이언트가 요청한 전체 URL 중에서 컨텍스트 주소 /pro14만얻어
		  <a>태그의 href속성에 작성해서 사용하는 방법
		  <a href="<%=request.getContextPath()%>"></a>
	 --%>
</body>
</html>