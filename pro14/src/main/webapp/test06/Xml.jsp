<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%request.setCharacterEncoding("utf-8");%>
<%--
      JSTL의 XML 태그
      out - select 속성에 지정한 XPath표현식의 결과를 출력
      parse - XML을 파싱할때 사용
      forEach - select 속성에 지정한 반복되는 노드를 파싱
      if - select 속성에 지정한 XPath 표현식의 값을 하나의 조건으로 결정
      choose - select 속성에 지정한 XPath 표현식의 값을 다중 조건으로 결정
      파싱 - 데이터를 분석해 원하는 데이터를 특정 패턴이나 순서로 추출
  --%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <%--BookList.xml 파일의 데이터를 jsp에서 사용하기 위해 파일의 경로를 임포트--%>
<c:import url="BookList.xml" var="bookList" charEncoding="utf-8"/>
<%--
bookList 변수에 저장된 xml데이터 문자열을 파싱해줄 x:parse 태그 이용
--%>
<x:parse xml="${bookList}" var="blist" />
<h1>파싱 1</h1>
제목 : <x:out select="$blist/booklist/book[1]/name"/> <br>
저자 : <x:out select="$blist/booklist/book[1]/author"/> <br>
가격 : <x:out select="$blist/booklist/book[1]/price"/> <br>
    <table border="1">
    <tr>
        <th>제목</th>
        <th>저자</th>
        <th>가격</th>
    </tr>
<x:forEach select="$blist/booklist/book" var="item">
    <tr>
        <td><x:out select="$item/name"/></td>
        <td><x:out select="$item/author"/></td>
        <td><x:out select="$item/price"/></td>
    </tr>
</x:forEach>
    </table>

<h3>파싱 3</h3>
    <table border="1">
        <tr>
            <th>제목</th>
            <th>저자</th>
            <th>가격</th>
            <th>구매여부</th>
        </tr>
        <x:forEach select="$blist/booklist/book" var="item">
        <tr>
            <td><x:out select="$item/name"/></td>
            <td><x:out select="$item/author"/></td>
            <td>
                <x:choose>
                    <x:when select="$item/price > 20000">
                        2만원 이상
                    </x:when>
                    <x:otherwise>
                        2만원이하
                    </x:otherwise>
                </x:choose>
            </td>
            <td>
                <%--책 제목이 총균쇠면 구매함 출력--%>
                <x:if select="$item/name = '총균쇠'">
                    구매함
                </x:if>
            </td>
        </tr>
        </x:forEach>
</body>
</html>