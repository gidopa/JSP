<%@ page import="sec01.ex02.MemberBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("utf-8");
    List<MemberBean> list = new ArrayList<>();
    //MemberBean 객체를 생성하고 각 인스턴스 변수에 회원정보를 수동으로 저장
    MemberBean m1 = new MemberBean("lee","1234","이순신","lee@test.com");
    MemberBean m2 = new MemberBean("son", "1234", "손흥민", "son@test.com");
    list.add(m1);
    list.add(m2);
    request.setAttribute("list",list);
%>
<jsp:forward page="member9.jsp"></jsp:forward>




