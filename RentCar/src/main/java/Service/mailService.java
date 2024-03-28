package Service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import smtp.NaverSMTP;
import smtp.SMTPServer;

public class mailService {

    public static boolean send(HttpServletRequest request) throws Exception{
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
            String htmlContent = content.replace("\r\n", "<br>"); // HTML 포맷일 때 HTML 형태로 변환해 저장
            emailInfo.put("content", htmlContent);
            emailInfo.put("format", "text/html;charset=UTF-8");
        }
        try {
            SMTPServer smtpServer = new NaverSMTP();
            // 이메일 전송을 요청
            smtpServer.emailSending(emailInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
}