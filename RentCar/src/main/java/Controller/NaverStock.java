package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@WebServlet("/stock.do")
public class NaverStock extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String URL = "https://finance.naver.com/item/main.naver?code=035720";
		Document doc;
		try {
			doc = Jsoup.connect(URL).get();
			// 날짜 정보 추출
			Elements elements = doc.select(".date");
			/*
			 * <em class="date">2024.03.27 15:07 <span>기준(장중)</span></em> 
			 * <span class="date">(2023.12)</span>
			 * <span class="date">(2023.12)</span>
			 */
			String text = elements.text();
			System.out.println(text);
			/**
			 * 2024.03.27 15:10 기준(장중) (2023.12) (2023.12)
			 */
			StringTokenizer st = new StringTokenizer(text," ");
			String date = st.nextToken() +  " " + st.nextToken() ;
			
//			System.out.println(date);
			System.out.println("----------------------------");
			String[] res = text.split(" ");
//			for(String str : res) {
//				System.out.println(str + "\n");
//
//			}
//			----------------------------------------------------------------------
			// 주가 정보
			String sign = null;
			Elements todayList = doc.select(".new_totalinfo dl>dd");
//			System.out.println(todayList);
			String[] info = todayList.get(3).text().split(" ");
			String stockItem = todayList.get(1).text().split(" ")[0] + " : " + todayList.get(1).text().split(" ")[1];
			String stockPrice = info[0] + " : " +info[1];
			if(info[5].equals("마이너스")) {
				sign = "-";
			}else {
				sign = "+";
			}
			String fluctuation = "등락률 :  "+ sign + info[6] + "%";
			String openingPrcie = todayList.get(5).text().split(" ")[0]+ " : " + todayList.get(5).text().split(" ")[1];
			String highPrice = todayList.get(6).text().split(" ")[0]+ " : " + todayList.get(6).text().split(" ")[1];
			String lowPrice = todayList.get(8).text().split(" ")[0]+ " : " + todayList.get(8).text().split(" ")[1];
			String tradeVolume = todayList.get(10).text().split(" ")[0]+ " : " + todayList.get(10).text().split(" ")[1];
			String tradeAmount = todayList.get(11).text().split(" ")[0]+ " : " + todayList.get(11).text().split(" ")[1];
			
			String result = date + "<br>" + stockItem + "<br>" + stockPrice + "<br>" + fluctuation + "<br>" + openingPrcie + "<br>" + highPrice + "<br>" 
			+ lowPrice + "<br>" + tradeVolume + "<br>" + tradeAmount;
			
			out.write(result);
		}catch (Exception e) {
			System.out.println("NaverStock에서 오류" + e);
		}
	}

}
