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
import Service.FileBoardService;
import Service.MemberService;
import VO.BoardVO;
import VO.CarConfirmVO;
import VO.CarListVO;
import VO.CarOrderVO;
import VO.FileBoardVO;
import VO.MemberVO;

@WebServlet("/FileBoard/*")
public class FileBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileBoardService fileBoardService;
	private static String ARTICLE_REPO = "C:\\file_repo";
	
	public void init(ServletConfig config) throws ServletException {
		fileBoardService = new FileBoardService();
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
		PrintWriter out = response.getWriter();
		// 재요청할 VIEW 또는 서블릿 주소를 저장할 변수
		String nextPage = null;
		// 요청한 중앙화면 주소를 저장할 변수
		String center = null;
		String main = "/CarMain.jsp";
		String action = request.getPathInfo();
		FileBoardVO vo = null;
		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("id");
		List<FileBoardVO> list = new ArrayList<>();
		MemberVO memberVO = null;
		int count = 0;
		System.out.println("action : " + action);
		switch (action) {
			case "/list.bo": 
				list = fileBoardService.serviceBoardListAll();
				// 조회된 글의 갯수
				count = list.size();
				//조회한 글을 보여줄 중앙 VIEW 주소 요청
				center = fileBoardService.serviceFileBoardCenterView();
				request.setAttribute("center", center); // boarders/FileBoardList.jsp
				request.setAttribute("list", list);
				request.setAttribute("count", count);
				request.setAttribute("nowPage", request.getParameter("nowPage")); // 조회 후 보여질 글 목록의 페이지
				request.setAttribute("nowBlock", request.getParameter("nowBlock")); // 조회 후 보여질 글 목록의 블럭
				request.setAttribute("id", loginId);
				nextPage = main;
				break;
			case "/write.bo":
				memberVO = fileBoardService.serviceMemberOne(loginId);
				// 새글 입력하는 중앙 view 주소 request에 바인딩
				center = fileBoardService.serviceFileBoardWriteView();
				request.setAttribute("center", center);
				request.setAttribute("memberVO", memberVO);
				request.setAttribute("nowPage", request.getParameter("nowPage")); // 조회 후 보여질 글 목록의 페이지
				request.setAttribute("nowBlock", request.getParameter("nowBlock")); // 조회 후 보여질 글 목록의 블럭
				nextPage = main;
				break;
			case "/writePro.bo":
				//업로드한 파일은 c:file_repo에 있고 실제파일명과 원본파일명은 DB에 저장
				//파일을 업로드하고 업로드한 파일명을 DB에 저장하고  업로드시킨 파일의 글번호를 조회해서 가져오기 
				int num =0 ; // 조회된 글 번호가 저장된 변수
				try {
				num = fileBoardService.serviceInsertBoard(request,response);
				out.print("<script>");
				out.print("alert('"+num +" 글 추가 성공 ')");
				out.print("location.href='http://localhost:8083/RentCar/FileBoard/list.bo'");
				out.print("</script>");
				}catch (Exception e) {
					System.out.println("serviceInsertBoard : " + e);
				}
				return;
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
