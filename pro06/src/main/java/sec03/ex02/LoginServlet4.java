package sec03.ex02;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// get / post를 모두 한번에 처리
@WebServlet("/login4")
public class LoginServlet4 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet메소드 호출 후 doHandle에서 처리");
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost메소드 호출 후 doHandle에서 처리");
		doHandle(request, response);
	}
	//post / get 요청하면 모든 처리를 하는 메소드
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userID = request.getParameter("user_id");
		String userPW = request.getParameter("user_pw");
		System.out.println("입력한 아이디 : " + userID);
		System.out.println("입력한 비밀번호 : " + userPW);
	}
 
}
