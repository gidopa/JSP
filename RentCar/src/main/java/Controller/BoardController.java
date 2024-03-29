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
				String nowPage = request.getParameter("nowPage");
				String nowBlock = request.getParameter("nowBlock");
				
				center = "/boarders/list.jsp";
				//조회된 글 목록을 보여줄 주소
				request.setAttribute("center", center);
				request.setAttribute("list", list);
				request.setAttribute("count", count);
				// 로그인한 회원의 아이디 request에 바인딩
				request.setAttribute("id", loginId);
				request.setAttribute("nowPage", nowPage);
				request.setAttribute("nowBlock", nowBlock);
				nextPage = main;
				break;
			case "/write.bo":
				MemberVO memberVO = boardService.serviceMemberOne(loginId);
				// 새글 입력하는 중앙 view 주소 request에 바인딩
				request.setAttribute("center", "boarders/write.jsp");
				request.setAttribute("memberVO", memberVO);
				nextPage = main;
				break;
			case "/writePro.bo":
				//응답할 값 마련(DB에 새글 정보를 insert한 후 성공하면 추가메시지)
				//result = 1 -> DB에 새글 정보 추가 성공
				//result = 0 -> 실패
				int result = boardService.serviceInsertBoard(request);
				//1 -> "1"  ,  0 -> "0" 로 변환해서 저장
				String go = String.valueOf(result);
				//write.jsp로 ($.ajax()메소드 내부의 success:function(data)의 data매개변수로 전달)
				if(go.equals("1")) {
					out.print(go);
				}else {
					out.print(go);
				}
				return ;
			case "/seachlist.bo": //검색 기준값과 검색어를 입력해서 검색요청이 들어 왔을때
				
				//부장 호출!
				//검색기준값과 입력한 검색어가 저장된 request를 전달해  글들을 조회 하게 메소드 호출!
				list = boardService.serviceBoardList(request);
				//검색기준열의값과 입력한 검색어를 포함하고 있는 내용의 글들의 갯수 조회하게 메소드 호출!
				count = boardService.serviceGetTotalRecord(request);
				nowPage = request.getParameter("nowPage");
				nowBlock = request.getParameter("nowBlock");
				
				request.setAttribute("nowPage", nowPage);
				request.setAttribute("nowBlock", nowBlock);
				
				//중앙 VIEW화면 주소 request에 바인딩
				request.setAttribute("center", "boarders/list.jsp");
				//조회된 글목록 정보가 저장된 ArrayList배열, 조회된 글 갯수 를 request에 바인딩
				request.setAttribute("list", list);
				request.setAttribute("count", count);
				
				//메인화면 재요청 주소
				nextPage = "/CarMain.jsp";
				break;
			case "/read.bo": // 조회된 글목록에서 특정글을 클릭해 글 번호를 받아 조회 후 보여주는 요청
				String b_idx = request.getParameter("b_idx");
				nowPage = request.getParameter("nowPage");
				nowBlock = request.getParameter("nowBlock");
				boardVO = boardService.serviceBoardRead(b_idx);
				request.setAttribute("center", "boarders/read.jsp");
				request.setAttribute("boardVO", boardVO);
				request.setAttribute("nowPage", nowPage);
				request.setAttribute("nowBlock", nowBlock);
				request.setAttribute("b_idx", b_idx);
				nextPage = "/CarMain.jsp";
				break;
			case "/password.do": //글수정화면에서 패스워드 입력하고 b_idx와 pw를 넘겨줌
				boolean resultPass = boardService.servicePassCheck(request);
				if(resultPass) {
					out.write("비밀번호맞음");
					return;
				}else {
					out.write("비밀번호틀림");
					return;
				}
			case "/updateBoard.do":
				int res = boardService.modBoard(request);
				if(res == 1) {
					out.write("수정성공");
					return;
				}else {
					out.write("수정실패");
					return;
				}
			case "/deleteBoard.bo":
				res = boardService.deleteBoard(request);
				if(res == 1) {
					out.write("삭제성공");
					return;
				}else {
					out.write("삭제실패");
					return;
				}
				
			case "/reply.do": //답변글을 작성하는 화면 요청 				
				//요청한 값 얻기 
					//주글(부모글)의 글번호얻기 
					String b_idx__ = request.getParameter("b_idx");
					//주글(부모글)에 대한 답변글을 작성하는 로그인한 회원의 아이디 얻기
					String reply_id_ = request.getParameter("id");
								
					//부장 BoardService의 메소드를 호출하여 
					//로그인한 회원이 답변글을 작성할수 있도록하기 위해
					//로그인한 회원의 아이디를 전달하여 회원정보를 조회 함
					MemberVO reply_vo = boardService.serviceMemberOne(reply_id_);
					
					//주글(부모글) 번호를 request에 바인딩
					request.setAttribute("b_idx", b_idx__);
					//조회한 답변글을 작성하는 사람 정보 request에 바인딩
					request.setAttribute("vo", reply_vo);
					//중앙 화면(답변글을 작성할수 있는 화면) View 주소 바인딩
					request.setAttribute("center","boarders/reply.jsp");
					
					nextPage = "/CarMain.jsp";
					break;
				
				case "/replyPro.do"://주글에 대한 답변글의 내용을 새롭게 board테이블에 insert추가 요청!
					
					//부장 BoardService의 특정메소드를 호출하면서 request객체 주소를 전달해 
					//답변글 추가 요청합니다
					boardService.serviceReplyInsertBoard(request);
					
					
					//답변글 추가 성공후 
					//다시 전체글을 조회해서 보여주기 위해  조회요청주소를 저장
					nextPage = "/board/list.bo";
					
					break;
				default:
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
