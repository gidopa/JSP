<%@ page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<%
    String contextPath = request.getContextPath();
    String file1 = URLEncoder.encode(request.getParameter("param1"), "utf-8");
    String file2 = URLEncoder.encode(request.getParameter("param2"), "utf-8");
%>
파일 내려받기 1 :
<a href="<%=contextPath%>/download.do?fileName=<%=file1%>">파일다운로드요청</a> <br>
파일 내려받기 2 :
<a href="<%=contextPath%>/download.do?fileName=<%=file2%>">파일다운로드요청</a> <br>