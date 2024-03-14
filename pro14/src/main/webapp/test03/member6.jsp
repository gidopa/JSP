<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<%--
    자바코드로 ArrayList배열을 생성해 문자열 저장, <c:froEach>에서 사용할 수 있도록 c:set으로 list 변수 만들고
    ArrayList 배열을 저장시킴.
--%>
<%
    List dataList = new ArrayList();
    dataList.add("Hello");
    dataList.add("World");
    dataList.add("안녕하세요");
%>
<%-- JSTL c:forEach에서 EL ${}을 사용할 수 있도록 list변수에 위 ArrayList를 저장--%>
<c:set var="list" value="<%=dataList%>"/>
<%--변수 i의 값을 1로 초기화하고 시작값으로 사용
    varStatus의 값으로는 원하는 걸 설정해도 된다.
--%>
<c:forEach var="i" begin="1" end="10" step="1" varStatus="loop">
    i변수의 값 : ${i} &nbsp;&nbsp; 반복횟수 : ${loop.count} <br>
</c:forEach>

<c:forEach var="i" begin="1" end="10" step="2" >
    5 * ${i} = ${5*i} <br>
</c:forEach>

<c:forEach var="data" items="${list}" varStatus="loop">
    ${data}  &nbsp;&nbsp; index : ${loop.count} <br>
</c:forEach>

<c:forEach var="data" items="${list}" begin="0" end="${list.size()-1}" step="1">
    ${data}
</c:forEach>




