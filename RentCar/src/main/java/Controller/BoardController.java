package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import DAO.CarDAO;
import Service.BoardService;
import Service.MemberService;
import VO.BoardVO;
import VO.CarConfirmVO;
import VO.CarListVO;
import VO.CarOrderVO;
import VO.MemberVO;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
	private BoardService boardService;
	
	public void init(ServletConfig config) throws ServletException {
//		memberService = new MemberService();
		memberService = new MemberService();
		boardService = new BoardService();
	}		
		

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 재요청할 VIEW 또는 서블릿 주소를 저장할 변수
		String nextPage = null;
		// 요청한 중앙화면 주소를 저장할 변수
		String center = null;
		String main = "/CarMain.jsp";
		String action = request.getPathInfo();
		HttpSession session = request.getSession();
		BoardVO boardVO = null;
		ArrayList list = null;
		String loginId = (String)session.getAttribute("id");
		int count = 0; // 조회한 글 갯수
		PrintWriter out = response.getWriter();
		System.out.println("action : " + action);
		switch(action) {
			case "/list.bo":
				list = boardService.serviceBoardListALl();
				count = boardService.getTotalRecord(); // board테이블에 저장된 모든 글 갯수 조회
				//list.jsp페이징 처리 부분에서 이전 또는 다음 또는 각 페이지 번호를 눌렀을때 요청 받는 값 얻기
				center = "/boarders/list.jsp";
				//조회된 글 목록을 보여줄 주소
				request.setAttribute("center", center);
				request.setAttribute("list", list);
				request.setAttribute("count", count);
				// 로그인한 회원의 아이디 request에 바인딩
				request.setAttribute("id", loginId);
				nextPage = main;
				break;
			case "/write.bo":
				MemberVO memberVO = boardService.serviceMemberOne(loginId);
				// 새글 입력하는 중앙 view 주소 request에 바인딩
				request.setAttribute("center", "boarders/write.jsp");
				request.setAttribute("memberVO", memberVO);
				nextPage = main;
				break;
				default:
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
