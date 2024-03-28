package smtp;

import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//네이버 SMTP 이메일서버를 통해 이메일을 전송하는 기능의 클래스 
public class NaverSMTP implements SMTPServer{
	//1. 네이버 SMTP 이메일서버 정보 를 저장할 변수
	private final Properties serverInfo;
	//2. 인증 정보를 저장할 변수 
	private final Authenticator auth; 
	
	public NaverSMTP() {
		
	//3.생성자에서 네이버 SMTP 이메일 서버 정보 저장 
	//-> 네이버 SMTP 이메일 서버를 사용하여 안전하고 인증된 방법으로 이메일을 보내기 위한 설정 저장 
		serverInfo = new Properties();
		serverInfo.put("mail.smtp.host", "smtp.naver.com");//SMTP 서버명
		serverInfo.put("mail.smtp.port", "465");//SMTP 네이버이메일서버에 접근할 포트번호 		
		//TLS(Transport Layer Security) 보안 연결을 시작할 것인지 여부를 설정 합니다.
		serverInfo.put("mail.smtp.starttls.enable", "true");		
		//SMTP 인증을 사용할것인지 여부를 설정 합니다.
		serverInfo.put("mail.smtp.auth", "true");	
		//디버그 모드를 활성화하여 SMTP 통신 과정을 자세히 볼수 있도록 설정합니다.
        serverInfo.put("mail.smtp.debug", "true");    
        //SSL 소켓팩토리르 통해 안전한 소켓을 생성할때 사용할 포트번호를 설정 합니다.
        serverInfo.put("mail.smtp.socketFactory.port", "465");    
        //SSL 소켓팩토리의 클래스를 설정합니다. 
        serverInfo.put("mail.smtp.socketFactory.class",
	                   "javax.net.ssl.SSLSocketFactory");    
        //소켓 팩토리가 실패할 경우 다른 소켓 팩토리를 사용하지 않도록 설정 합니다. 
        serverInfo.put("mail.smtp.socketFactory.fallback", "false");
        //SSL 프로토콜 정보를 추가 해줌 
        serverInfo.put("mail.smtp.ssl.protocols", "TLSv1.2");
        //4. 사용자의 네이버 이메일 계정 정보를 인증하기 위한 Authenticator객체를 생성합니다.
        //-> Authenticator클래스를 상속하고 getPasswordAuthentication()메소드를 재정의 하여 
        //  사용자의 이메일 계정정보를 제공합니다. 여기서 "네이버아이디"와 "네이버패스워드"는 
        //  사용자가 자신의 네이버 이메일 계정에 로그인할때 사용하는 아이디와 패스워드를 의미합니다.
        //  이정보를 제공함으로써  STMP네이버이메일서버는 사용자를 인증하고, 이메일을 보내는데 필요한권한을 
        //  부여합니다.
        auth = new Authenticator() {
        	@Override
        	protected PasswordAuthentication getPasswordAuthentication() {
        		//getPasswordAuthentication()메소드를 오버라이딩하여 사용자의 이메일 계정정보를 반환합니다.
        		return new PasswordAuthentication("jdm2131", "qkrrleh123!");
   	
        	}	
        };
	}//생성자 끝
	
	//5. 주어진 메일 내용을  네이버 STMP 이메일 서버를 통해 전송합니다.
	public void emailSending(Map<String, String> mailInfo) throws MessagingException {
		//5.1 세션 메모리 생성
		//네이버 SMTP 서버와의 통신을 위한 세션 메모리를 생성합니다.
		Session session = Session.getInstance(serverInfo, auth);
		//디버그 모드를 활성화 하여 세션 동작을 출력할것인지 여부를 설정 true 또는 false
		session.setDebug(true);
		
		//5.2 메세지 작성
		//보낼 이메일 메세지 작성
		MimeMessage msg = new MimeMessage(session);//네이버 이메일 서버에 보낼 이메일의 정보를
												   //저장할 용도의 객체 생성
													//이떄 통신을 위해 sesion을 저장 
		//MimeMessage객체에 보내는 사람의 이메일 주소 설정 
		msg.setFrom(new InternetAddress( mailInfo.get("from")));
		//MimeMEssage객체에 받는 사람 이메일 주소 설정
		msg.addRecipient(Message.RecipientType.TO, 
						 new InternetAddress( mailInfo.get("to")));
		//MimeMEssage객체에 보낼 이메일의 제목 설정
		msg.setSubject( mailInfo.get("subject") );
		//MimeMessage객체에 보낼 이메일 내용과 포맷 설정
		msg.setContent( mailInfo.get("content"), mailInfo.get("format"));
		
		//5.3 이메일 전송 
		Transport.send(msg);		
	}

}














