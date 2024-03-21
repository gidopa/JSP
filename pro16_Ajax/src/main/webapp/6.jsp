<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userid = request.getParameter("userid");
	String passwd = request.getParameter("passwd");
	//브라우저로 응답하면 브라우저를 거쳐 AJAX로 요청한 6.html로 데이터를 전달해서 보냅니다.
	out.print(userid + "\t" + passwd);
%>