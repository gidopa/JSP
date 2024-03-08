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
 	login4.html에서 전송된 id와 비밀번호를 가져와 memberVO 객체 생성 후 id,비밀번호 저장
 	DAO객체의 isExisted()메소드를 호춯하면서 VO객체의 주소를 매개변수로 전달
 	입력한 id, 비밀번호에 해당되는 회원정보가 조회과 되면 true 를 반환받아
 	isLogOn속성 true로 설정
*/
@WebServlet("/login4")
public class LoginServlet extends HttpServlet {
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
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		MemberVO memverVO = new MemberVO();
		memverVO.setId(user_id);
		memverVO.setPwd(user_pw);
		//DB작업을 위한 MemberDAO
		MemberDAO dao = new MemberDAO();
		//입력한 아이디와 비밀번호에 해당하는 회원정보를 조회해 있으면 true 없으면 false
		boolean result = dao.isExisted(memverVO);
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("login.id", user_id);
			session.setAttribute("login.pw", user_pw);
			
			out.print("<html><body>");
			out.print("로그인 되었음" + user_id);
			out.print("<a href='show'>회원 정보 보기</a>");
			out.print("</body></html>");
			
		}else {
			out.print("<html><body><center>");
			out.print("<a href='login4.html'>다시 로그인 하러가기</a>");
			out.print("</body></html>");
		}
		}
	}

