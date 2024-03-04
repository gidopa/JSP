package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 	login.html(아이디,비밀번호를 입력하고 로그인버튼을 눌러 서블릿을 요청하는 화면)에서
 	로그인 버튼을 눌러 요청주소를 톰캣 서버에 전달하여 요청하면 요청을 받는 LoginServlet클래스.
 	1. 클라이언트가 login.html 화면 요청
 	2. 웹서버(아파치)는 login.html파일을 찾아 클라이언트의 브라우저에 띄워줌
 	3. login.html 화면을 본 클라이언트는 아이디와 비밀번호를 입력하고 로그인(submit)버튼을 눌러 
 	   폼태그의 action 속성에 설정된 login 요청주소로 loginServelt을 요청
 	4. loginServlet은 웹브라우저를 통해 전송한 요청한 정보를 톰캣 서버가 httpServletRequest 객체메모리를 
 	   생성한 후 여기에 담아서 doGet메소드의 매개변수로 전달
 	5. 백엔드 개발자가 doGet메소드의 매개변수 request로 요청한 데이터를 받아 이클립스의 콘솔창에 입력한 아이디, 비번을 찍어줌
*/
@WebServlet("/login")
public class loginServlet extends HttpServlet{
	// 클라이언트가 get요청 때리면 호출되는 콜백 메소드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 클라이언트가 요청한 데이터들은 모두 request 객체 내에 저장되어 있음, 요청한 데이터중에서 한글이 하나라도 존재하면 
		//   UTF-8로 인코딩해 한글깨짐 방지.
		request.setCharacterEncoding("UTF-8");
		//2. 요청한 값들을 request에서 꺼내옴
		//input 태그의 name 속성값을 getParameter 메소드 호출 시 매개변수로 전달
		String userID = request.getParameter("user_id");
		String userPW = request.getParameter("user_pw");
		//3. 요청한 값 확인
		System.out.println("요청한 아이디 : " + userID);
		System.out.println("요청한 비밀번호 : " + userPW);
	}


}
