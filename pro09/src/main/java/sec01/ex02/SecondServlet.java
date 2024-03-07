package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_address = request.getParameter("user_address");

		// 첫번째 서블릿에서 전달한 입력한 id,pw,address 정보를 이용해 로그인 상태 유지
		if (user_id != null && user_id.length() != 0) {
			out.println("이미 로그인된 상태<br>");
			out.println("첫 번째 서블릿에서 넘겨받은 아이디 : " + user_id+"<br>");
			out.println("첫 번째 서블릿에서 넘겨받은 비밀번호 : " + user_pw+"<br>");
			out.println("첫 번째 서블릿에서 넘겨받은 주소 : " + user_address+"<br>");
			
		}else {
			//첫 번째 서블릿이 전달한 아이디를 받을 수 없으면
			out.println("로그인 안함");
			out.println("<a href='/pro09/login2.html'>로그인 요청 화면 다시 이동</a>");
		}
	}
}
