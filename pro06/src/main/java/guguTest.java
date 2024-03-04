

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/guguTest")
public class guguTest extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		int dan = Integer.parseInt(request.getParameter("dan"));
		pw.print("<table border=1 width=800 align=center");
		pw.print("<tr align=center bgcolor='#FFFF66'>");
		pw.print("<td colspan=2>" + dan + "단 출력 </td>");
		pw.print("</tr>");
		for(int i=1;i<10;i++) {
			pw.print("<tr align=center>");
			pw.print("<td width=400>");
			pw.print(dan + " * " + i);
			pw.print("</td>");
			pw.print("<td width=400>");
			pw.print(i * dan);
			pw.print("</td>");
			pw.print("</tr>");
		}
		pw.print("</table>");
	}

}
