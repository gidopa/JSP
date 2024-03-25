package Api;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NaverSearchAPI.do")
public class SearchAPI extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientId = "MF_H6Wp0AOgQZwnhfTTR";
		String clientSecret = "XZwTch60rv";
		// 검색 조건 설정
		int startNum =0; // 검색 시작 위치
		String searchText = request.getParameter("keyword");
		String text = null; // 입력한 검색어
	       try {
	    	   startNum = Integer.parseInt(request.getParameter("startNum"));
	    	   text = URLEncoder.encode(searchText,"utf-8");
	       } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("검색어 인코딩 실패",e);
	       }
	    // 검색 URI JSON응답 결과를 받는 주소
	    // 입력한 검색어를 쿼리파라미터로 보냄. display,start,sort 속성 추가 
	    // https://developers.naver.com/docs/serviceapi/search/blog/blog.md#%EA%B0%9C%EC%9A%94 문서확인
        String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text + "&display=10&start="+startNum;  
        // 헤더맵에 id,secret 설정
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        String responseBody = get(apiURL,requestHeaders);
        System.out.println(responseBody);
        response.setContentType("test/html;charset=utf-8");
        response.getWriter().write(responseBody);
        
        request.setAttribute("center", "SearchView.jsp");
        request.setAttribute("responseBody", responseBody);
		String nextPage = "/CarMain.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);

	}
	
	// URI와 인증정보로 get요청 날리고 JSON을 받아 처리 
	  private static String get(String apiUrl, Map<String, String> requestHeaders){
		  // URI에 대한 HTTP프로토콜 연결 설정
	        HttpURLConnection con = connect(apiUrl);
	        try {
	        	//HTTP프로토콜 요청 방식을 GET으로 설정
	            con.setRequestMethod("GET");
	            // 맵에서 header의 정보를 key, value로 분리(id, secret)
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }

	            // 응답 상태 확인 
	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출 200
	            	//요청에 대한 응답(body)을 받아옴 
	                return readBody(con.getInputStream());
	            } else { // 오류 발생
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }
	  
	  //주어진 API요청 주소에 대한 HTTP 연결을 생성
	  private static HttpURLConnection connect(String apiUrl){
	        try {
	        	//주어진 API주소(URL)로 부터 URL 객체를 생성
	            URL url = new URL(apiUrl);
	            //url 객체로 HTTP 연결을 오픈 
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) { // URL이 유효하지 않을때
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) { // 연결에 실패
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }
	  
	  private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);

	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();


	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }


	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
	        }
	    }

	
}
