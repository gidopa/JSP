package smtp;
import java.util.Map;

import javax.mail.MessagingException;

public interface SMTPServer {
	
	public void emailSending(Map<String, String> mailInfo) throws Exception;
}
