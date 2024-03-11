<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 요청한 값 문자처리 방식 utf-8
	request.setCharacterEncoding("utf-8");
	int score = Integer.parseInt( request.getParameter("score"));
%>
<%--요청한 값을 이용해 조건에 따라 응답할 값 마련 후  클라이언트 웹브라우저로 응답 --%>
	<h1>입력한 시험 점수 : <%=score %></h1>
<%
	if(score>=90){
		%>
		<h1>A학점</h1>
		<%
	}else if(score>=80){
		%>
		<h1>B학점</h1>
	<%
	}else if(score>=70){
		%>
		<h1>C학점</h1>
		<%
	}else if(score >= 60){
		%>
		<h1>D학점</h1>
	<%
	}else{
		%>
		<h1>F학점</h1>
		<%
	}
%>