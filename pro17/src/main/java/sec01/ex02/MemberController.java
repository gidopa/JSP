package sec01.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Controller는 request.getPathInfo()메소드를 이용하면 2단계로 이루어진 클라이언트가 요청한 URL을 문자열로 얻을 수 있다
//1. 회원조회 요청을 받음 /member/listMembers.do
//2. 회원가입 입력 디자인 화면 요청 주소 /member/memberForm.do
//3. 회원정보를 DB의 테이블에 추가 해줘 /member/addMember.do
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	MemberDAO memberDAO;
//	String nextPage;
	
	
	@Override
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
//		nextPage = null;
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
		String action = request.getPathInfo();
		System.out.println(action); ///listMembers.do
		String nextPage = null;
		
		if( (action == null) || action.equals("/listMembers.do") ) {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("list", membersList);
			nextPage = "/test02/listMembers.jsp";
//			listMember(request,response);
		}else if(action.equals("/memberForm.do")){
			nextPage = "/test02/membersForm.jsp";
		}else if(action.equals("/addMember.do")) {
			/*
			 * String id = request.getParameter("id"); String pwd =
			 * request.getParameter("pwd"); String name = request.getParameter("name");
			 * String email = request.getParameter("email");
			 */
			createVO(request,response);
//			MemberVO memVo = new MemberVO(id,pwd,name,email);
//			memberDAO.addMember(memVo);
			memberDAO.addMember(createVO(request,response));
			nextPage = "/member/listMembers.do";
		}
		
		System.out.println(nextPage);
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
	}
	//DB와 연동해 조회된 정보들이 저장된 ArrayList 리턴받고 listMembers.jsp로 포워딩
	public void listMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<MemberVO> membersList = memberDAO.listMembers();
		request.setAttribute("list", membersList);
//		nextPage = "/test02/listMembers.jsp";
	}
	
	public MemberVO createVO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		return new MemberVO(id,pwd,name,email);
	}
	
}
