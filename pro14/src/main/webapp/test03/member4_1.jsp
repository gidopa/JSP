<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <%--변수 선언 : 100, 문자열 "JSP"저장--%>
    <c:set var="number" value="100" scope="page"></c:set>
    <c:set var="string" value="JSP" scope="page"></c:set>
<h4>JSTL의 c:if 태그로 짝수/홀수 판단하기</h4>
<c:if test="${number % 2 == 0}" var="result" scope="page">
    <h1>number는 짝수? ${result}</h1><br>
</c:if>
    result : ${pageScope.result}
    <h4>문자열 비교 else문 흉내내기</h4>
    <%--string변수에 문자열 JSP가 java라는 문자열이 같거나 틀리면 결과값을 res에 저장하고
     현재 member4_1 jsp 내부에서 공유해서 사용--%>
    <c:if test="${string == 'java'}" var="res" scope="page">
        <h3>문자열은 java.</h3><br>
    </c:if>

    <c:if test="${!res}" >
            <h3>문자열은 java가 아님</h3> <br>
    </c:if>
<%--조건식에 el이 아닌 일반값이 오면 무조건 false를 반환 , 일반값으로 true가 올때는 예외
    true에 대해 대소문자 구분은 없고 양 끝에는 공백없이 ${true}와 같이 작성
--%>

</body>
</html>