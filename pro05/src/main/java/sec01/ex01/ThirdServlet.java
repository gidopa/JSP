package sec01.ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	어노테이션 기호란?
	web.xml에서 설정할 경우 요청주소들이 복잡해 진다는 단점이 있음.
	따라서 각 서블릿 클래스에 @ 기호를 붙여서 서블릿을 요청하면 코드의 가독성이 좋아진다.
	각 용도별로 어노테이션 기호를 제공받아 사용
	@WebServlet  -> 서블릿을 요청할 가상 주소(매핑) 설정 용도
*/
@WebServlet("/third")
public class ThirdServlet extends HttpServlet {
	//서블릿 클래스 직렬화를 위해 이클립스에서 지정한 상수
	// private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("클라이언트가 ThirdServlet 처음 요청했을떄 " 
							+ "ThirdServlet.class가 톰캣 메모리에 처음 로드되는 init메소드" );
	}
	
	@Override
	public void destroy() {
		System.out.println("톰캣 서버가 중지 되는 시점에 호출되는 destroy 메소드");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get요청 날리면 실행되는 doGet메소드");
	}

}
