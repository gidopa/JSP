package sec03.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
	login2.html에서 해당 서블릿으로 로그인 요청.
	doPost -> doHandle 매개변수로 request받고 request에는 아이디 비번이 있음
	request를 매개변수로 전달해서 사용
 	
*/
@WebServlet("/login!")
public class SessionTest4 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		if(session.isNew()) {
			//사용자가 로그인 시도시 아이디를입력하면 session에 user_id변수값 저장 , 로그인 상태를 확인하는 a링크 표시
			if(user_id != null && !user_id.trim().isEmpty()) {
				session.setAttribute("user_id", user_id);
				out.println("<a href='login!'>로그인 상태 확인</a>");
			}else {
				out.println("<a href='login2.html'>다시 로그인 하세요</a>");
				session.invalidate();
			}
		}else { // 세션이 이미 존재하는경우 -> 사용자가이미 로그인했다거나, login2 페이지를 방문한 경우
			//session에 저장된 id를 가져와 사용자가 로그인 되어있는지 확인
			user_id = (String)session.getAttribute("user_id");
			if(user_id != null && user_id.length()!=0) {
				out.print("로그인중<br>");
			}else {
				out.print("<a href='login2.html'>다시 로그인 하세요</a>");
				session.invalidate();
			}
			
		}
	}
}
