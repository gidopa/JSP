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
import DAO.MemberDAO;
import VO.CarConfirmVO;
import VO.CarListVO;
import VO.CarOrderVO;
import VO.MemberVO;

// <a href="<%=contextPath%>/Car/bb?center=CarResevation.jsp"> 예약을 위한 검색 옵션을 보여주는 화면 띄워주기

@WebServlet("/Car/*")
public class CarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarDAO carDAO;
	private MemberDAO memberDAO;

	public void init(ServletConfig config) throws ServletException {
		carDAO = new CarDAO();
		memberDAO = new MemberDAO();
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
		String main = "/CarMain.jsp";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 재요청할 VIEW 또는 서블릿 주소를 저장할 변수
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println(action);
		// Car/Main 전체 요청 주소중에서 /Main <- 메인화면 요청
		if (action.equals("/Main")) {
			nextPage = "/CarMain.jsp";
		} else if (action.equals("/bb")) {
			String center = request.getParameter("center");
			request.setAttribute("center", center);
			nextPage = "/CarMain.jsp";
		} else if (action.equals("/CarList.do")) { // CarReservation.jsp에서 전체검색을 했을떄
			Vector<CarListVO> vector = carDAO.getAllCarList();
			request.setAttribute("vector", vector);
			request.setAttribute("center", "CarList.jsp");
			nextPage = "/CarMain.jsp";
		} else if (action.equals("/carCategory.do")) {
			String category = request.getParameter("carCategory");
			Vector vector = carDAO.getCategoryList(category);
			request.setAttribute("vector", vector);
			request.setAttribute("center", "CarList.jsp");
			nextPage = "/CarMain.jsp";
		} else if (action.equals("/CarInfo.do")) {
			int carno = Integer.parseInt(request.getParameter("carNo"));
			CarListVO vo = carDAO.getOneCar(carno);
			request.setAttribute("vo", vo);
			request.setAttribute("center", "CarInfo.jsp");
			nextPage = "/CarMain.jsp";
		} else if (action.equals("/CarOption.do")) {
//			String carqty = request.getParameter("carqty");
//			int carno = Integer.parseInt(request.getParameter("carNO"));
//			int carprice = Integer.parseInt(request.getParameter("carPrice"));
//			String carimg = request.getParameter("carImg");
//			
			request.setAttribute("center", "CarOption.jsp");
			nextPage = "/CarMain.jsp";
		} else if (action.equals("/CarOptionResult.do")) {
			int carno = Integer.parseInt(request.getParameter("carno"));
			String carbegindate = request.getParameter("carbegindate");
			int carqty = Integer.parseInt(request.getParameter("carqty"));
			int carprice = Integer.parseInt(request.getParameter("carprice"));
			int carreserveday = Integer.parseInt(request.getParameter("carreserveday"));
			int carins = Integer.parseInt(request.getParameter("carins"));
			int carwifi = Integer.parseInt(request.getParameter("carwifi"));
			int carnavi = Integer.parseInt(request.getParameter("carnavi"));
			int carseat = Integer.parseInt(request.getParameter("carseat"));

			int totalreserve = carqty * carprice * carreserveday;
			int totaloption = carqty * carreserveday * 10000 * (carins + carwifi + carseat);

			CarOrderVO vo = new CarOrderVO();
			vo.setCarno(carno);
			vo.setCarbegindate(carbegindate);
			vo.setCarqty(carqty);
			vo.setCarreserveday(carreserveday);

			vo.setCarins(carins);
			vo.setCarwifi(carwifi);
			vo.setCarnavi(carnavi);
			vo.setCarseat(carseat);

			request.setAttribute("vo", vo);
			request.setAttribute("totalreserve", totalreserve);
			request.setAttribute("totaloption", totaloption);
			// 로그인 상태 확인
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			if (id == null) {
				request.setAttribute("center", "CarOrder.jsp");
			} else {
				// 회원정보 조회, reqeust에 회원 정보 바인딩,
				MemberVO memberVO = memberDAO.memberOneIdPass(id);
				request.setAttribute("membervo", memberVO);
				request.setAttribute("center", "LoginCarOrder.jsp");
			}

			nextPage = "/CarMain.jsp";
		} else if (action.equals("/CarOrder.do")) {
			int carno = Integer.parseInt(request.getParameter("carno"));
			String carbegindate = request.getParameter("carbegindate");
			int carqty = Integer.parseInt(request.getParameter("carqty"));
			int carreserveday = Integer.parseInt(request.getParameter("carreserveday"));
			int carins = Integer.parseInt(request.getParameter("carins"));
			int carwifi = Integer.parseInt(request.getParameter("carwifi"));
			int carnavi = Integer.parseInt(request.getParameter("carnavi"));
			int carseat = Integer.parseInt(request.getParameter("carseat"));

			CarOrderVO vo = new CarOrderVO();
			vo.setCarno(carno);
			vo.setCarbegindate(carbegindate);
			vo.setCarqty(carqty);
			vo.setCarreserveday(carreserveday);

			vo.setCarins(carins);
			vo.setCarwifi(carwifi);
			vo.setCarnavi(carnavi);
			vo.setCarseat(carseat);

			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			if (id == null) {
				String memberphone = request.getParameter("memberphone");
				String memberpass = request.getParameter("memberpass");

				vo.setMemberphone(memberphone);
				vo.setMemberpass(memberpass);
			} else {
				String memberId = request.getParameter("memberid");
				String memberpass = request.getParameter("memberpass");

				vo.setId(memberId);
				vo.setMemberpass(memberpass);

			}
			carDAO.insertCarOrder(vo, session);
			PrintWriter pw = response.getWriter();
			pw.print("<script>");
			pw.print("alert('예약 되었습니다.');");
			pw.print("location.href='" + request.getContextPath() + "/Car/CarList.do';");
			pw.print("</script>");
			// doHandle을 빠져나가므로 dispatcher 부분 안탐
			return;

		} else if (action.equals("/cc")) {
			String center = request.getParameter("center");
			request.setAttribute("center", center);
			nextPage = "/CarMain.jsp";
		} else if (action.equals("/CarReserveConfirm.do")) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			if (id == null) {
				// 요청한값 얻고, db에서 브라우저로 응답할값 생성, 바인딩하고 재요청
				String memberphone = request.getParameter("memberphone");
				String memberpass = request.getParameter("memberpass");
				// 입력한 정보로 값을 조회하는데 carlist테이블과 carorder테이블을 natural join하여 가져옴
				Vector<CarConfirmVO> vector = carDAO.getAllCarOrder(memberphone, memberpass);
				request.setAttribute("vector", vector);
				request.setAttribute("memberphone", memberphone);
				request.setAttribute("memberpass", memberpass);
			} else {
				String memberid = request.getParameter("memberid");
				String memberpass = request.getParameter("memberpass");
				// 입력한 정보로 값을 조회하는데 carlist테이블과 carorder테이블을 natural join하여 가져옴
				Vector<CarConfirmVO> vector = carDAO.getAllCarOrderMember(memberid, memberpass);
				request.setAttribute("vector", vector);
				request.setAttribute("memberid", memberid);
				request.setAttribute("memberpass", memberpass);
			}
			request.setAttribute("center", "CarReserveResult.jsp");
			nextPage = "/CarMain.jsp";
		} else if (action.equals("/update.do")) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			if (id == null) {
				int orderid = Integer.parseInt(request.getParameter("orderid"));
				String carimg = request.getParameter("carimg");
				String memberphone = request.getParameter("memberphone");
				String memberpass = request.getParameter("memberpass");
				CarConfirmVO vo = carDAO.getOneOrder(orderid);
				vo.setCarimg(carimg);
				request.setAttribute("vo", vo);
				request.setAttribute("memberphone", memberphone);
				request.setAttribute("memberpass", memberpass);
			} else {
				int orderid = Integer.parseInt(request.getParameter("orderid"));
				String carimg = request.getParameter("carimg");
				String memberid = request.getParameter("memberid");
				String memberpass = request.getParameter("memberpass");
				CarConfirmVO vo = carDAO.getOneMemberOrder(orderid);
				vo.setCarimg(carimg);
				request.setAttribute("vo", vo);
				request.setAttribute("memberphone", memberid);
				request.setAttribute("memberpass", memberpass);
			}
			request.setAttribute("center", "CarConfirmUpdate.jsp");
			nextPage = "/CarMain.jsp";
		} else if (action.equals("/updatePro.do")) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			if (id == null) {
				int result = carDAO.carOrderUpdate(request);
				PrintWriter pw = response.getWriter();
				if (result == 1) {
					pw.print("<script>");
					pw.print("alert('예약 수정 성공.');");
					pw.print("location.href='" + request.getContextPath() + "/Car/update.do?orderid="
							+ request.getParameter("orderid") + "&carimg=" + request.getParameter("carimg")
							+ "&memberpass=" + request.getParameter("memberpass") + "&memberphone="
							+ request.getParameter("memberphone") + "';");
					pw.print("</script>");
					return;
				} else {
					pw.print("<script>");
					pw.print("alert('예약 수정 실패.');");
					pw.print("history.back();");
					pw.print("</script>");
					return;
				}
			} else {
				int result = carDAO.carMemberOrderUpdate(request);
				PrintWriter pw = response.getWriter();
				if (result == 1) {
					pw.print("<script>");
					pw.print("alert('예약 수정 성공.');");
					pw.print("location.href='" + request.getContextPath() + "/Car/update.do?orderid="
							+ request.getParameter("orderid") + "&carimg=" + request.getParameter("carimg")
							+ "&memberpass=" + request.getParameter("memberpass") + "&memberid="
							+ request.getParameter("memberid") + "';");
					pw.print("</script>");
					return;
				} else {
					pw.print("<script>");
					pw.print("alert('예약 수정 실패.');");
					pw.print("history.back();");
					pw.print("</script>");
					return;
				}

			}

		} else if (action.equals("/delete.do")) {
			// 예약 취소를 위해 비밀번호를 입력할 화면의 경로
			String center = request.getParameter("center");
			request.setAttribute("center", center);
			nextPage = "/CarMain.jsp";
		} else if (action.equals("/deletePro.do")) {
			HttpSession session = request.getSession();
			String id = (String) session.getAttribute("id");
			if (id == null) {
				String memberphone = request.getParameter("memberphone");
				String memberpass = request.getParameter("memberpass");
				int orderid = Integer.parseInt(request.getParameter("orderid"));
				PrintWriter pw = response.getWriter();

				int res = carDAO.deleteOrder(orderid, memberpass);
				System.out.println(res);
				if (res == 1) {
					pw.print("<script>");
					pw.print("alert('예약 삭제 성공.');");
					pw.print("location.href='" + request.getContextPath() + "/Car/CarReserveConfirm.do?memberphone="
							+ memberphone + "&memberpass=" + memberpass + "'");
					pw.print("</script>");
					return;

				} else if (res == 0) {
					pw.print("<script>");
					pw.print("alert('예약 삭제 실패.');");
					pw.print("history.back();");
					pw.print("</script>");
					return;
				}
			} else {
				String memberid = request.getParameter("memberid");
				String memberpass = request.getParameter("memberpass");
				int orderid = Integer.parseInt(request.getParameter("orderid"));

				PrintWriter pw = response.getWriter();

				int res = carDAO.deleteMemberOrder(orderid, memberpass);
				if (res == 1) {
					pw.print("<script>");
					pw.print("alert('예약 삭제 성공.');");
					pw.print("location.href='" + request.getContextPath() + "/Car/CarReserveConfirm.do?memberid="
							+ memberid + "&memberpass=" + memberpass + "'");
					pw.print("</script>");
					return;

				} else if (res == 0) {
					pw.print("<script>");
					pw.print("alert('예약 삭제 실패.');");
					pw.print("history.back();");
					pw.print("</script>");
					return;
				}
			}
		}else if(action.equals("/NaverSearchAPI.do")) {
			
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
