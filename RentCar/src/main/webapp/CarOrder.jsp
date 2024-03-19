<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");
String contextPath = request.getContextPath();%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=contextPath%>/Car/CarOrder.do" method="post">
		<!-- 예약시 선택했던 정보들 같이 전달 -->
		<input type="hidden" name="carno" value="${requestScope.vo.carno }"> 
		<input type="hidden" name="carqty" value="${requestScope.vo.carqty }"> 
		<input type="hidden" name="carreserveday" value="${requestScope.vo.carreserveday }"> 
		<input type="hidden" name="carbegindate" value="${requestScope.vo.carbegindate }"> 
		<input type="hidden" name="carins" value="${requestScope.vo.carins }"> 
		<input type="hidden" name="carwifi" value="${requestScope.vo.carwifi }"> 
		<input type="hidden" name="carnavi" value="${requestScope.vo.carnavi }"> 
		<input type="hidden" name="carseat" value="${requestScope.vo.carseat }"> 
		<div align="center">
			<img alt="" src="<%=contextPath%>/img/haki.jpg">
			<p>
				<font size="13" color="blue">
					차량 기본 비용 : ￦${requestScope.totalreserve}
				</font>
			</p>
			<p>
				<font size="13" color="blue">
					차량 옵션 비용 : ￦${requestScope.totaloption}
				</font>
			</p>
			<p>
				<font size="13" color="blue">
					총 비용 : ￦${requestScope.totalreserve + requestScope.totaloption}
				</font>
			</p>
			<p>
				비회원 전화번호 입력 : <input type="text" name="memberphone">
				&nbsp;&nbsp;&nbsp;
				비회원 비밀번호 입력 : <input type="password" name="memberpass">
				<input type="submit" value="결제 후 예약 요청">
			</p>
		</div>
	</form>
</body>
</html>