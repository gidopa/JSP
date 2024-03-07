package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
	두번째 서블릿 request의 getCookies() 메소드를 호출해 브라우저로부터 쿠키를 전달 받음
	전달된 쿠키에서 저장할 때 사용한 이름인 "cookieTest"로 검색해 쿠키값을 가져옴
*/
@WebServlet("/get")
public class GetCookieValue extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//getCookies : Cookie 객체의 정보를 배열로 반환
		Cookie[] cookies = request.getCookies();
		for(int i=0;i<cookies.length;i++) {
			if(cookies[i].getName().equals("cookieTest")) {
				String value = URLDecoder.decode(cookies[i].getValue(),"utf-8");    
				out.println(value);
			}
		}
	}
}
