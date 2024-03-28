package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.mailService;
import smtp.NaverSMTP;
import smtp.SMTPServer;

@WebServlet("/mail/*")
public class MailController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 재요청할 VIEW 또는 서블릿 주소를 저장할 변수
		String nextPage = null;
		// 요청한 중앙화면 주소를 저장할 변수
		String center = null;
		String main = "/CarMain.jsp";
		String action = request.getPathInfo();
		System.out.println(action);
		if(action.equals("/form")) {
			center = "/EmailSend/EmailSendMain.jsp";
			request.setAttribute("center", center);
			nextPage = main;
		}else if(action.equals("/write")) {
			boolean res = false;
			try {
			 res = mailService.send(request);
			 }catch (Exception e) {
				 e.printStackTrace();
			 }
			center = "EmailSend/SendProcess2.jsp";
			request.setAttribute("success", res);
			request.setAttribute("center", center);
			nextPage = main;
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
