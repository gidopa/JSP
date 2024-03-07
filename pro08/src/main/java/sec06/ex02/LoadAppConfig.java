package sec06.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/* 
 loadOnStartup 
 	클라이언트가 서블릿을 요청했을때 톰캣 서버는 아래의 LoadAppConfig 클래스를 톰캣 서버 메모리에 로드시킴
 	클라이언트가 요청을 하지 않고 서버만 실행하면 미리 아래의 서블릿을 서버에 로드시킬 수 있도록
 	하나의 프로젝트 내부의 모든 서블릿이 톰캣 메모리에 로드되는 우선순위를 설정 
 	
 	-지정한 값이 0보다 크면 톰켓컨테이너가 실행되면서 서블릿이 초기화 
*/
@WebServlet(name = "loadAppConfig", urlPatterns = { "/loadConf" }, loadOnStartup = 1)
public class LoadAppConfig extends HttpServlet {
	private ServletContext context; 
	
	// 톰캣 서버의 메모리에 로드되는 시점에 호출
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init called");
		context = config.getServletContext();
		String menu_member = context.getInitParameter("menu_member");
		String menu_order = context.getInitParameter("menu_order");
		String menu_goods = context.getInitParameter("menu_goods");
		context.setAttribute("menu_member", menu_member);
		context.setAttribute("menu_order", menu_order);
		context.setAttribute("menu_goods", menu_goods);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String menu_member = (String)context.getAttribute("menu_member");
		String menu_order = (String)context.getAttribute("menu_order");
		String menu_goods = (String)context.getAttribute("menu_goods");
		
		out.print(menu_member + "<br>");
		out.print(menu_order + "<br>");
		out.print(menu_goods + "<br>");
		
	}

}
