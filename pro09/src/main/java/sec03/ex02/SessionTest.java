package sec03.ex02;

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
	클라이언트가 서블릿페이지를 요청하면 서블릿 서버페이지는 새로운 HttpSession 메모리를 만든다.
	만들어진 HttpSession 객체 메모리의 정보를 브라우저로 응답
*/
@WebServlet("/sess2")
public class SessionTest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String id = session.getId();
		out.print("아이디 : " + id+"<br>");
		//최초 세션을 생성한 시각 얻기
		Date date = new Date(session.getCreationTime());
		out.print("생성 시간 : " + date+"<br>");
		out.print("마지막 접속 시간  : "+ new Date(session.getLastAccessedTime())+"<br>") ;
		// 톰캣 서버 메모리에 HttpsSession 객체 메모리가 올라가있는 기본 유효시간
		out.print("기본 세션 유효시간 : " + session.getMaxInactiveInterval()+"<br>");
		session.setMaxInactiveInterval(5);
		out.print("세션 유효시간 : " + session.getMaxInactiveInterval()+"<br>");
		out.print("최초로 생성된 세션 ? : " + session.isNew());
	}
}
