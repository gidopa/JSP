package sec05.ex01;

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
 	먼저 로그인 된 상태를 나타내기 위해 loginServlet내부에서 만들었던 세션을 얻고
 	내부에 바인딩된 isLogOn에 대응값 true를 가져와 로그인 상태의 화면을 보여줌
 	세션에 메모리가 존재하지 않거나 isLogOn key에 해당하는 값이 false이면 다시 로그인 요청할 수 있도록 리다이렉션
*/
@WebServlet("/show")
public class ShowMember extends HttpServlet {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String id = "", pwd = "";
		Boolean isLogon = false;
		// 이미 loginServlet에서 세션을 생성하고 바인딩 했으므로 기존의 세션 객체 메모리 주소를 얻음
		HttpSession session = request.getSession(false);
		if (session != null) {
			isLogon = (Boolean) session.getAttribute("isLogon");
			if(isLogon) {//isLogon이 true이면 로그인된 상태
				id = (String)session.getAttribute("login.id");
				pwd = (String)session.getAttribute("login.pw");
				out.print("id :" + id +"<br>pwd : " + pwd);
			}else {
				response.sendRedirect("login4.html");
			}
		}else {
			response.sendRedirect("login4.html");
		}
	}
}
