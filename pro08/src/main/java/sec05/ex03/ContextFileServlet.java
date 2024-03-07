package sec05.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ServletContext 객체를 얻어와 getResourceAsStream()으로 읽어들일 데이터가 저장된 파일 위치 경로를 지정한 후
//InputStream 입력스트림 통로를 반환받아 파일에서 데이터를 읽어온 후 브라우저로 응답 
@WebServlet("/cfile")
public class ContextFileServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ServletContext context  = getServletContext();
		PrintWriter out = response.getWriter();
		//init.txt의 내용을 읽어들이기 위한 InputStream 입력 스트림 통로 얻기
		InputStream is = context.getResourceAsStream("/WEB-INF/bin/init.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String contents = br.readLine();
		StringTokenizer st = new StringTokenizer(contents,",");
		String menu_member = null;
		String menu_order = null;
		String menu_goods = null;
		while(st.hasMoreTokens()) {
			menu_member = st.nextToken();
			menu_order = st.nextToken();
			menu_goods = st.nextToken();
		}
		out.print("<html><body>");
		out.print(menu_member);
		out.print("<br>"+menu_order);
		out.print("<br>"+menu_goods);
		out.print("</body></html>");
	}

}
