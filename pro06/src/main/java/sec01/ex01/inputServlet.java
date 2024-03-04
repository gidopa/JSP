package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//input.html에서 이름, 비밀번호, 과목들을 체크하여 전송 버튼을 눌렀을떄 요청한 값 받는 서블릿
@WebServlet("/input")
public class inputServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userID = request.getParameter("user_id");
		String userPW = request.getParameter("user_pw");
		System.out.println("요청한 아이디 : " + userID);
		System.out.println("요청한 비밀번호 : " + userPW);
		//같은 name 속성값으로 여러개를 받을 경우 getParameterValues 사용
		String[] userSubject = request.getParameterValues("subject");
		for(String subject : userSubject) {
			System.out.println("체크한 과목 명 : " + subject);
		}
		//인텔리제이 맞나
	}


}
