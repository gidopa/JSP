package sec01.ex01;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	httpServletRequest의 getParameterNames()메소드
	- getParameterNames()메소드를 호출하면 요청한 값들에 대한 name 속성의 값들을 뱉어준다
*/
@WebServlet("/input2")
public class inputServlet2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Enumeration<String> enu = request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = enu.nextElement();
			String[] values = request.getParameterValues(name);
			for(String value :values) {
				System.out.println("name : " + name + ", value : " + value);
			}
		}
	}


}
