package sec05.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	getServletContext로 ServletContext 얻고 getInitParameter메소드로 매개변수로 초기화 파라미터 이름을 전달한 후 
 	메뉴 텍스트 항목을 가져와 브라우저로 출력
*/
@WebServlet("/initMenu")
public class ContextParamServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 톰캣서버가 생성해 놓은 ServletContext 객체의 주소를 얻어 저장
		// 이 객체는 웹 애플리케이션(pro08)전체에 공유되는 데이터들을 제공하고 저장하는 메모리
		ServletContext context = getServletContext();
		//web.xml에 설정된 초기화 파라미터의 값을 ServletContext객체 메모리부터 얻고
		//웹 애플리케이션 전체에서 모든 서블릿 또는 JSP에서 사용할 수 있는 공통 파라미터
		String menu_member = context.getInitParameter("menu_member");
		String menu_order = context.getInitParameter("menu_order");
		String menu_goods = context.getInitParameter("menu_goods");
		out.print("<html><body>");
		out.print(menu_member);
		out.print("<br>"+menu_order);
		out.print("<br>"+menu_goods);
		
		out.print("<table border='1' cellspacing='0'>");
		out.print("<tr><td>메뉴이름들</td></tr>");
		out.print("<tr><td>"+menu_member+"</td></tr>");
		out.print("<tr><td>"+menu_order+"</td></tr>");
		out.print("<tr><td>"+menu_goods+"</td></tr>");
		out.print("</table>");
		out.print("</body></html>");
		
	}
}
