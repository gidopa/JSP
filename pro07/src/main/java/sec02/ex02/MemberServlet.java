package sec02.ex02;

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
	MemberForm.html에서 가입할 정보를 입력하고 가입 요청 누르면 정보들을 모두 request객체에 저장된 후 공유받아 사용하는 서블릿
	타입이 hidden인 input 태그의 addMember값을 request객체에서 얻음.
*/
@WebServlet("/member4")
public class MemberServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getParameter("command");
		response.setContentType("text/html;charset=UTF-8");
		MemberDAO dao = new MemberDAO();
		if(command != null && command.equals("addMember")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO vo = new MemberVO(id,pwd,name,email);
			dao.addMember(vo);
		}else if(command != null && command.equals("delMember")) {
			String id = request.getParameter("id");
			dao.delMember(id);
		}
		
		List list = dao.listMembers();
		// 클라이언트의 웹브라우저로 응답할 데이터 종류 (MIME TYPE)설정 및 데이터 인코딩방식 설정
		response.setContentType("text/html;charset=UTF-8");
		// 클라이언트의 브라우저로 응답할 출력스트림 생성
		PrintWriter out = response.getWriter();
		out.print("<html>");
			out.print("<body>");
				out.print("<table border='1px'>");
					  out.print("<tr align='cneter' bgcolor='lightgreen'>");
				      out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>");
				      
					for(int i=0;i<list.size();i++) {
						Object obj = list.get(i);
						MemberVO memberVO = (MemberVO)obj;
						String id = memberVO.getId();
						String pwd = memberVO.getPwd();
						String name = memberVO.getName();
						String email = memberVO.getEmail();
						Date joinDate = memberVO.getJoinDate();
						
						out.print("<tr>");
							out.print("<td>"+id+"</td>");
							out.print("<td>"+pwd+"</td>");
							out.print("<td>"+name+"</td>");
							out.print("<td>"+email+"</td>");
							out.print("<td>"+joinDate+"</td><td>");
						out.print("<a href='member4?command=delMember&id="+id+"'>삭제</a><td></tr>");
					
					}
				out.print("</table>");
				
			out.print("</body>");
		out.print("</html>");
		out.print("<a href='/pro07/MemberForm.html'>새회원등록하기</a>");
	}
	}
	
