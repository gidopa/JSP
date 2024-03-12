<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%-- useBean 액션태그 MemberBean클래스의 객체 생성 --%>
<jsp:useBean id="m1" class="sec01.ex01.MemberBean" scope="page"></jsp:useBean>
<%-- setProperty 액션태그 값 설정 --%>
<jsp:setProperty name="m1" property="name" value="이순신"/>
<%-- useBean액션태그로 ArrayList에 넣는다 --%>
<jsp:useBean id="m2" class="java.util.ArrayList" scope="page"></jsp:useBean>
empty연산자를 이용해 El표현언어 내부에 출력 <br>
<h2>
	<%-- MemberBean 객체의 모든 변수 값이 비어있다면 true 하나라도 저장되어 있으면 false --%>
	${empty m1}	<br>
	${!empty m1} <br>
	${empty m2} <br>
	${!empty m2} <br><br>
	
	${empty "hello"} <br>
	${empty null} <br>
	${empty ""} <br>
</h2>