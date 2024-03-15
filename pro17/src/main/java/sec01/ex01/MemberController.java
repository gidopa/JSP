package sec01.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	디자인 패턴 : 개발 편의를 위해 개발방법을 패턴화 시켜 놓은 것
	MVC - 모델뷰컨트롤러 , 일반 앱개발에 사용된 디자인 패턴을 웹에도 적용
	- 화면부분, 요청처리 부분, 로직처리 부분으로 나눠서 개발
	Model : 클라이언트의 브라우저로 응답할 데이터, Model을 만들기 위해 DAO, VO 클래스를 사용 (로직처리 및 데이터)
	View : 사용자의 화면에 띄워지는 템플릿. 클라이언트가 서블릿을 요청하는 화면 , 응답을 받는 화면
	Controller : 서블릿으로 클라이언트의 요청을 받아 제어. 요청에 따라 DAO 및 JSP에서 작업
*/
//MVC에서 컨트롤러 역할을 하는 Servlet
//클라이언트의 요청을 처음받아 로직 처리 후 응답하는 클래스

//순서1. 클라이언트가 브라우저에 /mem.do로 서블릿을 요청 
//순서2. doHandle로 MemberDAO(Model)의 listMembers 호출해 모든 회원 정보를 DB의 테이블에서 꺼내옴

@WebServlet("/mem.do")
public class MemberController extends HttpServlet {
	MemberDAO memberDAO;
	
	@Override
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}
	
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
		//DB와 연동해 조회된 정보들이 저장된 ArrayList 리턴받고 listMembers.jsp로 포워딩
		List<MemberVO> membersList = memberDAO.listMembers();
		request.setAttribute("list", membersList);
		RequestDispatcher rd = request.getRequestDispatcher("/test01/listMembers.jsp");
		rd.forward(request, response);
		
	}
	
	
}
