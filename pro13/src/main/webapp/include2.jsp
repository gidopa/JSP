<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% request.setCharacterEncoding("utf-8"); %>
	ㅎㅇ, 쇼핑몰 중심 JSP 시작
	<br>
	<%-- page속성의 값으로 동적으로 포워딩할 jsp주소 작성 --%>
	<jsp:include page="duke_image.jsp">
		<jsp:param value="듀크2" name="name"/>
		<jsp:param value="duke2.png" name="img_name"/>
		
	</jsp:include>
	<%-- jsp:param 액션 태그 
		 모든 액션 태그 사이에 작성. 동적으로 값을 만들어서 request에 담고 include 액션태그를 통해 
		 재요청시 request를 공유할 목적의 액션태그
	 --%>
	
	<br>
	gd 하단 영역	
</body>
</html>