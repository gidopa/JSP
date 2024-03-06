package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 첫번째 서블릿에서 두번째 서블릿 재요청시 첫밴째 서블릿이 지니던 데이터를 공유할 수 있다
@WebServlet("/first")
public class FirstServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setAttribute("address", "서울시 성북구");
		RequestDispatcher rd = request.getRequestDispatcher("second");
		//forward 메소드 호출 시 매개변수로 전달해서 재요청시 두번째 서블릿에서 사용
		rd.forward(request, response);
	}
}
