package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
	동작 흐름 >> 클라이언트가 login3.html을 요청하면 아파치가 띄워주고 클라이언트가 로그인을 하는데 
	요청때릴때 get이 아닌 post로 요청을 때린다. 매핑 주소는 action 속성값. 요청 데이터는 doPost의 매개변수로 전달됨
*/
@WebServlet("/login3")
public class loginServlet3 extends HttpServlet{
	@Override
	public void init() throws ServletException {
		System.out.println("init메소드 호출"); 
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("doPost 호출");
		String userID = request.getParameter("user_id");
		String userPW = request.getParameter("user_pw");
		System.out.println("입력한 ID : " + userID);
		System.out.println("입력한 PW : " + userPW);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 호출");
	}
	@Override
	public void destroy() {
		System.out.println("destory메소드 호출");
	}

}
