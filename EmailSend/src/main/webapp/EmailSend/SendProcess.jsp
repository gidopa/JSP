<%@page import="smtp.NaverSMTP"%>
<%@page import="smtp.SMTPServer"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	Map<String, String> emailInfo = new HashMap<>();
	emailInfo.put("from", request.getParameter("from"));
	emailInfo.put("to", request.getParameter("to"));
	emailInfo.put("subject", request.getParameter("subject"));
	String content = request.getParameter("content"); // 보내는 이메일 내용
	String format = request.getParameter("format"); // text or html 
	if (format.equals("text")) {
		emailInfo.put("content", content);
		emailInfo.put("format", "text/plain;charset=utf-8");
	} else {
	 	String htmlContent = "";
	//	HTML포맷일때는 HTML형태로 변환해 저장
		content = content.replace("\r\n", "<br>"); 
		try {
			String path = application.getRealPath("/EmailSend/MailForm.html");
			//C:\Tomcat 9.0\wtpwebapps\EmailSend\EmailSend\MailForm.html
			//MailForm.html의 내용을 읽어들일 입력스트림 생성
			BufferedReader br = new BufferedReader(new FileReader(path));
			String oneLine = null;
			
			while ((oneLine = br.readLine()) != null) {
				htmlContent += oneLine + "\n";
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 템플릿의 "__CONTENT__" 부분을 메일 내용으로 대체 
		htmlContent = htmlContent.replace("__CONTENT__", content);
		emailInfo.put("content", htmlContent);
		emailInfo.put("format", "text/html;charset=UTF-8");
	}
	try{
		SMTPServer smtpServer = new NaverSMTP();
		//이메일 전송을 요청  
		smtpServer.emailSending(emailInfo);
		out.print("이메일 전송 성공");
	}catch(Exception e){
		out.print("이메일 전송 실패");
		e.printStackTrace();
	}
%>
<!-- 이메일 전송 처리 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>