package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import VO.MemberVO;

public class MemberService {
	private MemberDAO memberDAO;

	public MemberService() {
		memberDAO = new MemberDAO();
	}

	public String joinName(HttpServletRequest request) {
		return request.getParameter("center");
	}

	public void serviceInsertMember(HttpServletRequest request) {
		// 요청한 값 얻어 MemberVo로 저장
		MemberVO vo = new MemberVO(request.getParameter("id"), request.getParameter("pass"),
				request.getParameter("name"), Integer.parseInt(request.getParameter("age")),
				request.getParameter("gender"),
				request.getParameter("address1") + request.getParameter("address2") + request.getParameter("address3")
						+ request.getParameter("address4") + request.getParameter("address5"),
				request.getParameter("email"), request.getParameter("tel"), request.getParameter("hp"));

		memberDAO.insertMember(vo);
	}

	public String serviceLoginMember() {
		return "members/login.jsp";
	}

	public int serviceUserCheck(HttpServletRequest request) {
		String loginId = request.getParameter("uid");
		String loginPass = request.getParameter("upw");

		int check = memberDAO.userCheck(loginId, loginPass);
		if (check == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", loginId);
		}
		return check;
	}

	public void serviceLogOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}

	}

	public boolean checkIdDuplicate(HttpServletRequest request) {
		String id = request.getParameter("id");
		boolean result = memberDAO.checkIdDuplicate(id);
		return result;
	}

}
