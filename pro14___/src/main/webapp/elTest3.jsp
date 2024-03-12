<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
	\${10 == 10 } : ${10 == 10 } <br>
	\${10 eq 10 } : ${10 eq 10 } <br>
	\${"hello" == "hello" } : ${"hello" == "hello" } <br>
	\${"hello" eq "hello" } : ${"hello" eq "hello" } <br>
	\${20 != 10 } : ${20 != 10 } <br>
	\${20 ne 10 } : ${20 ne 10 } <br>
	\${"hello" != "apple" } : ${"hello" != "apple" } <br>
	\${"hello" ne "apple" } : ${"hello" ne "apple" } <br>
	\${10 < 11 } : ${10 < 11 } <br>
	\${10 lt 11 } : ${10 lt 11 } <br>
	\${100 > 10 } : ${100 > 10 } <br>
	\${100 gt 10 } : ${100 gt 10 } <br>
	\${10 >= 10 } : ${10 >= 10 } <br>
	\${10 ge 10 } : ${10 ge 10 } <br>
	</h1>
</body>
</html>