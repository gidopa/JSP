package sec01.ex02;

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
@WebServlet("/member2")
public class MemberServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 요청한 주소에 관한 DB의 회원 종보 조회를 위해 DB작업하는 MemberDAO클래스의 객체 생성
		MemberDAO dao = new MemberDAO();
		// 테이블의 모든 회원종보를 조회 하기 위해 dao의 listMembers 메소드 호출
		// listMembers는 모든 회원정보를 행 단위로 조회한 후 하나의 행당 하나의 dao객체를 각각 생성 후 담고
		// dao객체들을 ArrayList 배열에 추가해 저장후 ArrayList 반환
		List list = dao.listMembers();
		// 클라이언트의 웹브라우저로 응답할 데이터 종류 (MIME TYPE)설정 및 데이터 인코딩방식 설정
		response.setContentType("text/html;charset=UTF-8");
		// 클라이언트의 브라우저로 응답할 출력스트림 생성
		PrintWriter out = response.getWriter();
		out.print("<html>");
			out.print("<body>");
				out.print("<table border='1px'>");
					  out.print("<tr align='cneter' bgcolor='lightgreen'>");
				        out.print("<th>아이디</th>");
				        out.print("<th>비밀번호</th>");
				        out.print("<th>이름</th>");
				        out.print("<th>이메일</th>");
				        out.print("<th>가입일</th>");
					out.print("</tr>");
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
							out.print("<td>"+joinDate+"</td>");
						out.print("</tr>");
					
					}
				out.print("</table>");
				
			out.print("</body>");
		out.print("</html>");
	}
}
