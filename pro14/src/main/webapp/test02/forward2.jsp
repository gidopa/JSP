<%@ page import="sec01.ex02.MemberBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("utf-8");
    //MemberBean 객체를 생성하고 각 인스턴스 변수에 회원정보를 수동으로 저장
    MemberBean memberBean = new MemberBean("lee","1234","이순신","lee@test.com");
    //request에 member라는 이름으로 MemberBean객체의 주소를 같이 바인딩
    request.setAttribute("memberBean",memberBean);
    //member8.jsp 재요청
%>
<jsp:forward page="member8.jsp"></jsp:forward>




