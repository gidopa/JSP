package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
	해당 서블릿을 요청하면 서블릿은 Session객체메모리를 만들어서 세션값("name","이순신")을 바인딩
	그 후 만들어 놓은 <a>를 클릭해 session1.jsp를 요청. 이때 
*/
@WebServlet("/sess")
public class SessionTest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("name", "이순신");
		out.print("<html><body>");
		out.print("<h1>Session에 바인딩</h1>");
		out.print("<a href=/pro11/test01/session1.jsp>session1.jsp 요청</a>");
		out.print("</bdoy></html>");
	}

}
