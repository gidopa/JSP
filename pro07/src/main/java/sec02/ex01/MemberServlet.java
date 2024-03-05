package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
	역할 : 브라우저로부터 DB의 회원종보 조회 요청을 받아 클라이언트의 브라우저에 응답  
*/
@WebServlet("/member4")
public class MemberServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		MemberDAO dao = new MemberDAO();
		PrintWriter out = response.getWriter();
		
		String command = request.getParameter("command");
		
		if(command != null && command.equals("addMember")) {
			String _id = request.getParameter("id");
			String _pw = request.getParameter("pwd");
			String _name = request.getParameter("name");
			String _email = request.getParameter("email");
			
			MemberVO vo = new MemberVO();
			vo.setId(_id);
			vo.setPwd(_pw);
			vo.setName(_name);
			vo.setEmail(_email);
			dao.addMember(vo);
		}
		List list = dao.listMembers();
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td>");
		
		for(int i=0;i<list.size();i++) {
			MemberVO memberVo =(MemberVO) list.get(i);
			String id = memberVo.getId();
			String pwd = memberVo.getPwd();
			String name = memberVo.getName();
			String email = memberVo.getEmail();
			Date joinDate = memberVo.getJoinDate();
			out.print("<tr><td>" + id + "</td><td>"+pwd+
					"</td><td>"+name+"</td><td>"+email+"</td><td>"+
					joinDate + "</td><td>" + 
					"<a href='pro07/member4?command=delMember&id="
					+id+"'>삭제 </a></td></tr>'");
		}
			out.print("</table></body></html>");
			out.print("<a href='MemberForm.html'>새 회원 등록하기</a>");
	}
	
	
	
	
}
