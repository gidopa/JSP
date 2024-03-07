package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//getServletContext메소드를 이용해 ServletContext객체에 접근해 ArrayList배열에 데이터 바인딩
//이름,나이 저장 후 ServletContext에 ArrayList 저장
@WebServlet("/cset")
public class SetServletContext extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 톰캣서버가 생성해 놓은 ServletContext 객체의 주소를 얻어 저장
		// 이 객체는 웹 애플리케이션(pro08)전체에 공유되는 데이터들을 제공하고 저장하는 메모리
		ServletContext context = getServletContext();
		List member = new ArrayList();
		member.add("이순신");
		member.add(30);
		// context에 바인딩해놓으면 다른 서블릿에서도 member 에 접근 가능하다
		context.setAttribute("member", member);
		out.print("<html><body>");
		out.print("이순신, 30을 member 리스트로 바인딩");
		out.print("</body></html>");
		
	}
}
