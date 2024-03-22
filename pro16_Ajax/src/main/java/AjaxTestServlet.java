

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ajax1.html에 데이터를 응답
@WebServlet("/ajaxTest1")
public class AjaxTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doHandle(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doHandle(request, response);
    }
    protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String data = request.getParameter("param");
        //응답할 데이터의 종류 (MIME-TYPE)설정 및 데이터 인코딩 방식 설정
        response.setContentType("text/html;charset=utf-8");
        
        PrintWriter out = response.getWriter();
        out.print("안녕하세요 서블릿이 응답한 데이터" + data);
    }

}
