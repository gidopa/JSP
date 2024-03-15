<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("utf-8");%>
<%-- 톰캣이 프로젝트까지 찾아갈 수 있는 컨텍스트 주소를 얻어 변수에 저장--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%--파일 업로드 요청, action속서에 서블릿을 요청할 매핑 주소
파일 업로드 요청 시 반드시 form태그에는 enctype속성의 값을 multipart/form-data로 설정해줌 --%>
<form action="${contextPath}/upload.do" method="post" enctype="multipart/form-data">
    첨부파일1 : <input type="file" name="file1"> <br>
    첨부파일2 : <input type="file" name="file2"> <br>

    파라미터1 : <input type="text" name="param1"> <br>
    파라미터2 : <input type="text" name="param2"> <br>
    파라미터3 : <input type="text" name="param3"> <br>
    <input type="submit" value="업로드">
</form>

</body>
</html>