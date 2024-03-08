<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 선언문 태그 --%>
<% out.print(name);%>
<%! 
String name = "듀크";
	public String getName(){
	return name;
}
%>
<%-- 표현식 태그영역에 선언문 태그영역에 작성해 놓은 name변수 값을 불러와 출력(웹브라우저로 응답)--%>
안녕하세요. <%=name%>님!!
<hr>
안녕하세요2. <%=getName()%>님!!