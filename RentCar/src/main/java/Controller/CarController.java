package Controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CarDAO;
import VO.CarListVO;

// <a href="<%=contextPath%>/Car/bb?center=CarResevation.jsp"> 예약을 위한 검색 옵션을 보여주는 화면 띄워주기

@WebServlet("/Car/*")public class CarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarDAO carDAO;
	
	public void init(ServletConfig config) throws ServletException {
		carDAO = new CarDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 재요청할 VIEW 또는 서블릿 주소를 저장할 변수
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println(action);
		// Car/Main 전체 요청 주소중에서 /Main <- 메인화면 요청
		if(action.equals("/Main")) {
			nextPage = "/CarMain.jsp";
		}else if(action.equals("/bb")) {
			String center = request.getParameter("center");
			request.setAttribute("center", center);
			nextPage = "/CarMain.jsp";
		}else if(action.equals("/CarList.do")) { // CarReservation.jsp에서 전체검색을 했을떄
			Vector<CarListVO> vector = carDAO.getAllCarList();
			request.setAttribute("vector", vector);
			request.setAttribute("center", "CarList.jsp");
			nextPage = "/CarMain.jsp";
		}else if(action.equals("/carCategory.do")) {
			String category = request.getParameter("carCategory");
			Vector vector = carDAO.getCategoryList(category);
			request.setAttribute("vector", vector);
			request.setAttribute("center", "CarList.jsp");
			nextPage = "/CarList.jsp";
		}else if(action.equals("/CarInfo.do")) {
			int carno = Integer.parseInt(request.getParameter("carNo"));
			CarListVO vo = carDAO.getOneCar(carno);
			request.setAttribute("vo", vo);
			request.setAttribute("center", "CarInfo.jsp");
			nextPage = "/CarMain.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
