package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login2")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_address = request.getParameter("user_address");
		
		String user_email = request.getParameter("user_email");
		String user_hp = request.getParameter("user_hp");
		
		String data = "안녕하세요!<br> 로그인하셨습니다<br>";
			   data += "<html><body>";
			   data += "입력한 아이디 : " + user_id+"<br>";
			   data += "입력한 비밀번호 : " + user_pw+"<br>";
			   data += "입력한 주소 : " + user_address +"<br>";
			   data += "입력한 이메일 : " + user_email+"<br>";
			   data += "입력한 휴대폰 : " + user_hp+"<br>";
			   data += "<a href='/pro09/second?user_id="+user_id
					   +"&user_pw="+user_pw+"&user_address="+URLEncoder.encode(user_address,"UTF-8")+"'>두 번째 서블릿으로 보내기</a>";
			   data += "</body></html>";
			 //Get요청 때리면 한글이 깨지기 때문에 인코딩 해줘야함
				//user_address = URLEncoder.encode(user_address,"UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(data);
	}
}
