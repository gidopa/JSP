package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 첫번째 서블릿에서 두번째 서블릿 재요청시 첫밴째 서블릿이 지니던 데이터를 공유할 수 있다
//@WebServlet("/first")
public class FirstServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("1에서소환");
		out.print("</body></html>");
		//get요청 하는 주소뒤에 ?를 작성하고 그 뒤에 이름=값을 한쌍으로 전달
		response.sendRedirect("second?name=lee");
	
	}
}
