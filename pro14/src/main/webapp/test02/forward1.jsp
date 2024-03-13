<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    //순서1.해당 jsp를 요청하면 forward1.jsp내부에 request, session, application 내장객체 영역에 각각 바인딩
    request.setCharacterEncoding("utf-8");
    //request 내장객체에 바인딩 - 다른 서버페이지를 재요청하지 않으면 자동 소멸
    //다른 서버페이지 요청 시 메모리 영역 유지
    request.setAttribute("id","hong");
    request.setAttribute("pwd","1234");
    //session에 바인딩 - 브라우저창이 닫힐때까지 영역 유지
    session.setAttribute("name","홍길동");
    //application에 바인딩
    application.setAttribute("email","hong@test.com"); // <- 톰캣 서버 중지 되기 전까지 메모리 영역 유지

    RequestDispatcher rd = request.getRequestDispatcher("member7.jsp");
    rd.forward(request,response);
%>




