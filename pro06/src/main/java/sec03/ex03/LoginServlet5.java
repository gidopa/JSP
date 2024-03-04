package sec03.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// get / post를 모두 한번에 처리
@WebServlet("/login5")
public class LoginServlet5 extends HttpServlet {
	
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
		String userAddr = request.getParameter("user_address");
		System.out.println("입력한 아이디 : " + userID);
		System.out.println("입력한 비밀번호 : " + userPW);
		System.out.println("주소 : " + userAddr);
		
		//response 객체를 통해 응답할 데이터 종류 MIME type 결정
		response.setContentType("text/html;charset=UTF-8");
		String data = "<html>";
		data += "<body>";
		data += "입력한 아이디 : " + userID + ", 입력한 비밀번호 : " + userPW + "<br>";
		data += "주소 : " + userAddr;
		data += "</body>";
		data += "</html>";
		
		response.getWriter().print(data);
	}
 
}
