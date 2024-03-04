

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		System.out.println("init called");
	}

	public void destroy() {
		System.out.println("destory called");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + pw);
		
		if(id != null && (id.length() != 0)) {
			writer.print("<html>");
			writer.print("<body>");
			writer.print(id +" 로그인");
			writer.print("</body>");
			writer.print("</html>");
		}else {
			writer.print("<html>");
			writer.print("<body>");
			writer.print("아이디를 입력하세요");
			writer.print("<br>");
			writer.print("<a href='http://localhost:8081/pro06/test01/login.html'>로그인창으로 이동</a>");
			writer.print("</body>");
			writer.print("</html>");
		}
		
	}

}
