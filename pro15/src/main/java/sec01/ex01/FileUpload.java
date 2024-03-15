package sec01.ex01;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	파일 업로드 기능을 처리하는 서블릿 FileUpload
	라이브러리가 제공하는 DiskFileItemFactory 클래스를 이용해 파일이 업로드 되는 경로 위치와 한번에 업로드 가능한
	파일의 최대 파일 크기 설정.
	그리고 ServletFileUpload 클래스를 이용해 요청한 업로드된 파일과 입력한 텍스트의 정보를 가져와 파일 업로드 기능 구현
	요청한 파라미터 값을 출력
*/
@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    /*
        - 업로드 할 파일이 저장되는 폴더 경로와 연결된 File객체 생성
        - 업로드할 파일을 임시로 저장해 둘 객체 diskFileItemFactory
        - 메모리 최대 크기 1MB로 설정
        - 파일들의 크기가 1MB가 넘을경우 업로드할 폴더 경로 설정
        지정한 1MB를 넘기전까지 factory가 데이터를 들고 있다가 1MB를 넘어가면 정해진 경로에 업로드
        - 업로드할 임시 메모리의 주소를 생성자로 전달해 저장한 파일업로드를 실제 처리할 객체 생성  46Line
    */
    protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String encoding = "utf-8";
        File currentDirPath = new File("C:\\file_repo");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024);
        factory.setRepository(currentDirPath);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
			/*
				uploadForm.jsp에서 첨부한 파일 2개와 입력한 파라미터 3개의 정보들을
				request에서 꺼내와 각각의 DiskFileItem객체들에 저장한 후 DiskFileItem객체들을 ArrayList 배열에 추가하고 리턴
			*/
            List<FileItem> items = upload.parseRequest(request);
            //저장된 객체의 갯수만큼 반복하면서 객체의 정보가 파일이 아니면 name의 속성값 받아옴
            for (int i = 0; i < items.size(); i++) {
                FileItem item = (FileItem) items.get(i);
                // isFormField 파일이 아닌경우 true 반환
                if (item.isFormField()) {
                    System.out.println(item.getFieldName() + "=" + item.getString(encoding));
                }else{
					System.out.println("<input type='file'의 name 속성명 : " + item.getFieldName());
					System.out.println("업르도 요청 시 첨부한 파일명 : " + item.getName());
					System.out.println("첨부한 파일 크기 : " + item.getSize()+"bytes");

                    if(item.getSize()>0) {
                        // 업로드할 파일명을 얻어 파일명의 뒤에서부터 \\문자열이 포함되어 있는지 검색
                        // 검색했는데 있으면  \의 index 위치를 반환
                        int idx = item.getName().lastIndexOf("\\");
                        System.out.println(idx);
                        if(idx == -1){
                            idx = item.getName().lastIndexOf("/");
                            System.out.println("첨부할 파일명에 / 없음 " + idx);
                        }
                        log(item.getName());
                        //업로드할 파일명
                        String fileName = item.getName().substring(idx+1);
                        //업로드할 파일의 경로 + 파일명을 주소로 만들어서 접근할 파일 객체 생성
                        File uploadFile = new File(currentDirPath + "\\" +fileName);
                        item.write(uploadFile);
                    }

				}
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
