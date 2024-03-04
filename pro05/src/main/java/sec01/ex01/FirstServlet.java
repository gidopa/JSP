package sec01.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 요청을 받아 처리하는 서블릿 클래스 만들기 
//1. HTTPServlet클래스를 상속받아 만든다
//2. 생명주기 메소드 들 중에서 init메소드 오버라이딩
//3. doGet메소드 오버라이딩
public class FirstServlet extends HttpServlet{
/*
  init 메소드는 FirstServlet 클래스를 처음으로 요청했을때 단 한번만 호출되고
  두번째 요청했을때는 이미 톰캣 메모리에 올라가 있기 때문에 호출되지 않음
*/
	@Override
	public void init() throws ServletException {
		//역할 : 클라이언트가 웹 브라우저로 FirstServlet을 요청하면 FirstServlet클래스가 톰캣서버 메모리에 
		//      로드되는 시점에 개발자가 특정 작업(변수 초기화)을 재구현 해 놓기 위해 자동을 호출되는 콜백 메소드
		System.out.println("init called");
	}
	// 클라이언트가 get 요청날릴때마다 한번씩 실행된다
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 웹브라우저 주소창에서 FirstServlet서블릿을 요청할 주소를 입력하여 get요청 날라오면 
		// service메소드를 거쳐 호출되는 콜백 메소드.
		System.out.println("doGet called");
	}
	// 톰캣 서버 메모리에 올라가 있는 FirstServlet.class가 소멸되는 시점에 다른 처리 작업(자원해제)을 해야 할때 호출되는 콜백 메소드
	@Override
	public void destroy() {
		System.out.println("destroy called");
		
	}

	
}
