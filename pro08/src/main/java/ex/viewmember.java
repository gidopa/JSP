package ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/viewmember")
public class viewmember extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List memberList = (List)request.getAttribute("memberList");
		out.print("<html>");
		out.print("<body>");
			out.print("<table border='1px'>");
				  out.print("<tr align='cneter' bgcolor='lightgreen'>");
			      out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td><td>삭제</td></tr>");
			      
				for(int i=0;i<memberList.size();i++) {
					MemberVO memberVO = (MemberVO)memberList.get(i);
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
					out.print("<a href='pro07/member4?command=delMember&id="+id+"'>삭제</a><td></tr>");
				
				}
			out.print("</table>");
			
		out.print("</body>");
	out.print("</html>");
	out.print("<a href='/pro07/MemberForm.html'>새회원등록하기</a>");
}
	}

