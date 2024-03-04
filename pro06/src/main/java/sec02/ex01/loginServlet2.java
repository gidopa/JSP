package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login2")
public class loginServlet2 extends HttpServlet{
	// 클라이언트가 get요청 때리면 호출되는 콜백 메소드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//setContentType으로 클라이언트에게 응답할 데이터 종류 설정, 응답 데이터를 모두 한글처리
		response.setContentType("text/html;charset=UTF-8");
		//response객체의 getWriter()메소드 : 반환 값으로 클라이언트와 연결된 출력스트림 얻음
		PrintWriter pw = response.getWriter();
		//클라이언트에게 응답할 데이터 생성
		String data = "<html>";
			   data +="<body>";
			   data += "입력한 아이디 : " + request.getParameter("user_id") + "<br>";
			   data += "입력한 비밀번호 : " + request.getParameter("user_pw") + "<br>";
			   data += "</body>";		
			   data += "</html>";
		pw.println(data);
	}


}
