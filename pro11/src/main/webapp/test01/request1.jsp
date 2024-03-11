<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 저장소 역할 내장 객체
1.pageContext
2.session
3.request
4.application
 --%>
<%
	//request1.jsp를 요청하면 request 내장객체에 데이터 바인딩
	request.setAttribute("name", "이순신");
	request.setAttribute("address", "서울시 강남구");
	
	RequestDispatcher rd = request.getRequestDispatcher("request2.jsp");
	rd.forward(request, response);
	// sendRedirect는 해당 주소로 리다이렉션을 하는거라 여기서 request는 소멸됨
	response.sendRedirect("request2.jsp");
%>
