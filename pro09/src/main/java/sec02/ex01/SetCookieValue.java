package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
	SetCookieValue 서블릿 내부에서 Cookie클래스의 객체 생성 후 쿠키이름을 cookieTest로 쿠키값 저장
	setMaxAge()메소드로 유효시간 설정, response객체의 addCookie()메소드로 생성된 쿠키를 브라우저로 전송
*/
@WebServlet("/set")
public class SetCookieValue extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		// cookieTest라는 이름으로 한글정보를 인코딩해서 생성한 쿠키객체 메모리에 저장
		Cookie cookie = new Cookie("cookieTest", URLEncoder.encode("JSP프로그래밍","utf-8"));
		//cookie.setMaxAge(60*60*24); // 초단위 -> 24시간
		// 유효시간을 음수로 지정하면 쿠키파일에 저장하는 것이 아니라
		// 클라이언트의 브라우저가 사용하는 Cookie저장소에 session쿠키를 생성
		// 브라우저가 닫힐떄 같이 소멸, 세션동안만 필요할때 사용
		cookie.setMaxAge(-1);
		response.addCookie(cookie);
		out.println("현재 시간 : "+ date);
		out.println("음료는 없어 목말라도 "+ cookie.getName());
	}
}
