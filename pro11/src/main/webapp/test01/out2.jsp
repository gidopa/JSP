<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//첫번째 out1.jsp에서 입력한 이름과 나이를 request에 저장한 후 
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	if(name != null || name.length() != 0){
		out.print("이름은 "+name);
		out.print("나이는 "+age);
	}else{
		out.print("<h1>이름을 입력하세요.</h1>");
	}
%>

