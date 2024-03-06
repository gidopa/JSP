package ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	MemberForm.html에서 가입할 정보를 입력하고 가입 요청 누르면 정보들을 모두 request객체에 저장된 후 공유받아 사용하는 서블릿
	타입이 hidden인 input 태그의 addMember값을 request객체에서 얻음.
*/
@WebServlet("/ex4")
public class MemberServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		MemberDAO dao = new MemberDAO();
		List memberList = dao.listMembers() ;
		request.setAttribute("memberList", memberList);
		RequestDispatcher rd = request.getRequestDispatcher("viewmember");
		rd.forward(request, response);
		
	}
}
