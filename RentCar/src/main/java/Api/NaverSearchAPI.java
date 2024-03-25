package Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class NaverSearchAPI {


	public static String service(HttpServletRequest request, HttpServletResponse response) 
							throws ServletException, IOException {
		
		//1.인증 정보 설정
		String clientId = "s0QlCc7GgydLb5uboejC"; //RentCar프로젝트의 클라이언트 아이디
		String clientSecret = "uA07cRKmwD"; //RentCar프로젝트의 클라이언트 시크릿 
		
		//2.검색 조건 설정
		int startNum = 0; //검색 시작 위치
		String text = null; //입력한 검색어 
		
		try {
			//검색시작 위치 받아오기 
			startNum =  Integer.parseInt(request.getParameter("startNum") );//1  
					
			//입력한 검색어 받아오기 
			String searchText = request.getParameter("keyword");
			
			//입력한 검색어 한글깨짐 방지 하기 위해 문자처리 방식 UTF-8로 인코딩 합니다.
			text = URLEncoder.encode(searchText, "UTF-8");
				
		} catch (UnsupportedEncodingException e) {
			
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
		
		//3. 네이버 개발자 사이트로 요청할 API주소 만들기 
		//검색결과 데이터를 JSON으로 받기 위한 API입니다.
		//입력한 검색어를 쿼리스트링으로 보내는데 여기에는 display와 start매개변수도 추가 했습니다.
		//display속성은 한번에 가져올 검색 결과의 갯수를 설정 하며,
		//start속성은 검색 시작 위치 번호 값을 설정 합니다. 
		String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text
				       +"&display=10&start="+startNum; //JSON응답결과 받는 주소 
		
		//4. API주소로 요청
		Map<String, String> requestHeaders = new HashMap<>();
		
		//클라이언트의 클라이언트ID와 시크릿을 맵에 설정 
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        //API호출 합니다.
        String responseBody = get(apiURL,requestHeaders);

        
        return responseBody;
        
        
		
	}//
	
	//주어진 API 요청주소에 대해 HTTP GET 요청을 보내고,
	//JSON데이터로 응답을 받아 처리하는 메소드 
	 private static String get(String apiUrl, Map<String, String> requestHeaders){
		 
		//주어진 API요청 주소에 대한 HTTP프로토로을 이용한 연결을 설정 합니다.
		//URLConnection객체를 반환받아 저장 
		//요약 : 접속 
        HttpURLConnection con = connect(apiUrl);
        
        try {
        	//HTTP프로토콜 요청 방식을 GET으로 설정 합니다.
            con.setRequestMethod("GET");
            
            //요청시 우리가 설정한 clientID 와 clientSecret을  URLConnection객체에 저장 
            for(Map.Entry<String, String> header :  requestHeaders.entrySet()) {
            	
            	// URLConnection객체에 저장
            	
                //"X-Naver-Client-Id", clientId
                //"X-Naver-Client-Secret", clientSecret             
                con.setRequestProperty(header.getKey(), header.getValue());
                
            }

            // URLConnection객체의 메소드를 호출하여 HTTP통신 응답 코드를 반환 받아 저장 
            int responseCode = con.getResponseCode();
          
            //정상적인 응답을 할경우 
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
            	
            	//응답 본문을 읽어와 문자열로 반환합니다.
                return readBody(con.getInputStream());
                
            } else { // 오류 응답인 경우 
            	
            	//오류 스트림을 읽어와 문자열로 반환합니다.
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
        	//HTTP 요청 또는 또는 응답처리 중 예외가 발생한 경우
        	
        	//런타임 예외로 전환하여 호출자에게 전달합니다.
            throw new RuntimeException("API 요청과 응답 실패", e);
            
        } finally {
        	// HTTP 접속 연결을 닫습니다. // URLConnection객체 사용후 자원 해제 
            con.disconnect();
        }
    }

	 //주어진 API요청 주소(URL)에 대한 HTTP 연결을 생성하는 메소드 입니다. 
    private static HttpURLConnection connect(String apiUrl){
        try {
        	//주어진 API주소(URL)로 부터 URL객체를 생성합니다.
            URL url = new URL(apiUrl);
            	
            //URL객체를 사용하여 HTTP연결을 설정하고 URLConnection객체를 반환
            return (HttpURLConnection)url.openConnection();
        
        
        } catch (MalformedURLException e) {
        	//URL이 유효하지 않은 경우 발생하는 예외입니다.
        	//RuntimeException으로 전환하여 URL이 잘못되었음을 호출자에게 알립니다. 
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
            
        } catch (IOException e) {
        	//연결에 실패한 경우 발생하는 IO예외 입니다.
        	//RuntimeException으로 전환하여 연결이 실패 했다고  호출자에게 알립니다. 
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

  //InputStream통로를 얻어 응답할 검색 정보를 읽어 문자열로 반환하는 메소드 
    private static String readBody(InputStream body){
    	
    	//API주소와 연결된 검색 정보를 문자(2바이트) 단위로 읽어들이기 위해 
    	//InputStreamReader입력스트림 생성 
        InputStreamReader streamReader = new InputStreamReader(body);

        	//엔터키를 누른 정보 한줄단위로 검색한 데이터를 읽어 들기 위한 
        	//BufferedReader로 한번더 업그레이드 해서 생성 
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
        	
        	//검색한 문자열이 누적되어 저장되는 String객체와 같은 역할을 합니다. 
            StringBuilder responseBody = new StringBuilder();


            String line;
            
            //BufferedReader를 사용하여 한줄씩 읽어와 StringBuilder객체 메모리에 추가 합니다. 
            while ((line = lineReader.readLine()) != null) {
            	
                responseBody.append(line);
            
            }

            //StringBuilder객체 메모리에 추가된 검색한 정보를 문자열로 반환 
            return responseBody.toString();
            
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }//readyBody메소드 끝 
}
    
	








