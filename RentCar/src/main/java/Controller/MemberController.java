package Controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import Service.MemberService;
import VO.CarConfirmVO;
import VO.CarListVO;
import VO.CarOrderVO;

// <a href="<%=contextPath%>/Car/bb?center=CarResevation.jsp"> 예약을 위한 검색 옵션을 보여주는 화면 띄워주기

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
	
	public void init(ServletConfig config) throws ServletException {
//		memberService = new MemberService();
		memberService = new MemberService();
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
		PrintWriter out = response.getWriter();
		System.out.println(action);
		switch (action) {
			case "/join.me":
				center = memberService.joinName(request);
				request.setAttribute("center", center);
				nextPage = "/CarMain.jsp";
				break;
			case "/joinPro.me":
				//가입할 회원정보가 저장된 request를 MemberService에게 전달
				memberService.serviceInsertMember(request);
				nextPage = "/CarMain.jsp";
				break;
			case "/login.me":
				memberService.serviceLoginMember();
				center = memberService.serviceLoginMember();
				request.setAttribute("center", center);
				nextPage = "/CarMain.jsp";
				break;
			case "/loginPro.me":
				int check = memberService.serviceUserCheck(request);
				if(check == 0) {
					out.print("<script>");
					out.print("alert('비밀번호가 일치하지 않습니다.')");
					out.print("history.back();");
					out.print("</script>");
					return;
				}else if(check == -1) {
					out.print("<script>");
					out.print("alert('아이디가 일치하지 않습니다.')");
					out.print("history.back();");
					out.print("</script>");
					return;
				}
				
				nextPage= "/CarMain.jsp";
				break;
			
			case "/logout.me":
				memberService.serviceLogOut(request);
				nextPage= "/CarMain.jsp";
				break;
				
			default:
				break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
