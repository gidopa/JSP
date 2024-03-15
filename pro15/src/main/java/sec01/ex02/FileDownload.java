package sec01.ex02;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/*
    result.jsp에서 a태그를 클릭해 다운로드 요청이 들어오면 다운로드 기능을 구현하는 서블릿
*/
@WebServlet("/download.do")
public class FileDownload extends HttpServlet {
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
        response.setContentType("text/html;charset=utf-8");
        //다운로드할 파일의 경로
        String file_repo = "C:\\file_repo";
        // a태그로 요청한 다운로드할 파일명 얻기
        String fileName = request.getParameter("fileName");

        //파일 다운로드를 요청한 클라이언트의 브라우저와 연결된 출력스트림
        OutputStream out = response.getOutputStream();
        String donwFile = file_repo + "\\" + fileName;
        File f = new File(donwFile);
        /*
            웹브라우저 캐시
            웹개발을 하다보면 게시글 등의 데이터를 DB에 등록 했는데도 브라우저에서 새로고침을 해서 반영이 안되는 경우가 있고
            js나 html,css등의 정적자원을 서버에서 수정해도 반영이 안되는 경우도 있음.
            캐시 스토리지 공간이란 서버페이지와 불필요한 통신을 하지 않기 위해 있는 저장소
            최초 서버로부터 요청한 자원들을 내려받고 같은 자원을 새로 고침등을 통해 다시 요청하는경우 브라우저는
            자신의 케시 스토리지에 있는 자원을 재사용. 브라우저 캐시 기능이 성능상 이점이 있지만 실시간 검색 처럼 자주 변하는
            동적인 데이터 부분까지 캐시 스토리지를 사용하면 사요자는 변환된 데이터의 결과를 브라우저로 볼 수 없음.

            응답 헤더를 통한 캐시 스토리지 제어 서렴ㅇ
            HTTP 응답 메세지의 몇가지 헤더 속성을 통해 브라우저가 현재 페이지 내용을 캐시 스토리지에 저장하는 것을 사용하지 않도록
            할 수 있음. response 객체의 해당 속성들에 값을 설정해 브라우저가 캐시 스토리지를 사용하지 않고 매번 새로운 요청이
            들어왔을 때 결과를 얻어오도록 할 수 있음

            HTTp 1.1버전에서 지원하는 헤더 no-cache -> 브라우저는 응답받은 결과를 캐시 스토리지에 저장하지 않음
            뒤로가기 등을 통해 페이지 이동하는 경우 페이지를 캐싱할 수 있으므로 no-store 값 또한 추가
        */
        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
        //브라우저에서 다운로드 할 파일 클릭시
        //1. Content-Disposition - attachment : 다운로드시 무조건 파일 다운로드 다른 이름으로 저장 여부를 묻도록 설정
        response.setHeader("Content-Disposition", "attachment;fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
        /*
            Content-Disposition:inline
            브라우저 인식 파일 확장자를 가진 파일에 대해 브라우저에 바로 파일을 열고 그외 파일드른 파일 다운로드 대호 상자가 뜸
        */
        // 다운로드할 파일에 작성된 데이터들을 바이트 단위로 읽어들일 스트림 생성
        FileInputStream in = new FileInputStream(f);
        // 다운로드할 파일에서 데이터를 8KB씩 읽어와 저장할 배열
        byte[] buffer = new byte[1024 * 8];
        while (true) {
            int count = in.read(buffer);
            if (count == -1) {
                break;
            }
            //output스트림으로 전체 바이트 데이터들의 배열의 0 index부터 count 변수위치 끝까지 버퍼를 브라우저에 쏴줌,
            out.write(buffer,0,count);
        }

    }
}
