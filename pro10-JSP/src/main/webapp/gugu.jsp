<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//1. 요청한 값중에 문자가 있으면 문자처리방식 UTF-8로 설정
	request.setCharacterEncoding("utf-8");
	//2. 요청한 값 얻기 
	int dan = Integer.parseInt(request.getParameter("dan"));
%>    
                         
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1"  width="800" >
		<tr bgcolor="yellow" align="center">
			<td colspan="2"><%=dan%>단 출력</td>
		</tr>
<%
	for(int i=1; i<10; i++){
		//곱하는 수가 홀수이면?
		if(i%2 == 1){
%>			
			<tr align="center" bgcolor="green">
<%		
		//곱하는 수가 짝수이면?
		}else{
%>
			<tr align="center" bgcolor="pink">	
<%
		}
%>			
			<td width="400"><%=dan%> * <%=i%></td>
			<td width="400"><%=dan*i%></td>
		</tr>
<%	
	}
%>
	</table>
</body>
</html>





