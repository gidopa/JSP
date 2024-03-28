package Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import DAO.BoardDAO;
import DAO.FileBoardDAO;
import DAO.MemberDAO;
import VO.BoardVO;
import VO.FileBoardVO;
import VO.MemberVO;

public class FileBoardService {
	private FileBoardDAO fileBoardDAO;
	private MemberDAO memberDAO;
	
	public FileBoardService() {
		fileBoardDAO = new FileBoardDAO();
		memberDAO = new MemberDAO();
	}

	// DB의 모든 글 조회 
	public List<FileBoardVO> serviceBoardListAll() {
		List<FileBoardVO> list = new ArrayList<>();
		list = fileBoardDAO.boardListAll();
		return list;
	}

	// /list.bo or /searchList.bo 요청시 호출
	public String serviceFileBoardCenterView() {
		return "boarders/FIleBoardList.jsp";
	}

	public MemberVO serviceMemberOne(String loginId) {
		MemberVO memberVO = new MemberVO();
		memberVO = memberDAO.memberOne(loginId);
		return memberVO;
		
	}

	public String serviceFileBoardWriteView() {
		return "boarders/FileBoardwrite.jsp";
	}

	public int serviceInsertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//파일업로드 후 업로드한 파일명을 가지는 Map 반환받음
		//Map은 input name속성에 대한 value에 설정된 값과 작성한 글의 정보들을 가지고 있음
		Map<String,String> articleMap = uploadFile(request, response);
		String writer = articleMap.get("writer");
		String email = articleMap.get("email");
		String title = articleMap.get("title");
		String writer_id = articleMap.get("writer_id");
		String content = articleMap.get("content");
		String sfile = articleMap.get("fileName");
		String password = articleMap.get("password");
		FileBoardVO fileBoardVO = new FileBoardVO();
		fileBoardVO.setB_name(writer);
		fileBoardVO.setB_email(email);
		fileBoardVO.setB_title(title);
		fileBoardVO.setB_content(content);
		fileBoardVO.setB_pw(password);
		fileBoardVO.setB_id(writer_id);
		fileBoardVO.setSifle(sfile);
		// 작성한 새글 정보를 DB의 fileBoard테이블에 insert하고 추가한 글의 번호를 조회해 받아옴
		int articleNO = fileBoardDAO.insertBoard(fileBoardVO);
		if(sfile != null && sfile.length() != 0) { //글을 추가하면서 업로드한 파일이 있으면
			//temp 폴더에 업로드된 파일에 접근해 글 번호 폴더로 이동시키기 위해 경로 저장
			File srcFile = new File("c:\\file_repo\\temp\\" + sfile);
			// 글번호 폴더 생성을 위해 경로를 File 객체에 저장
			File destDir = new File("c:\\file_repo\\temp\\" + articleNO);
			// 글번호에 대한 정보를 받아와 글번호폴더 생성
			destDir.mkdir();
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
			
		}
		return articleNO;
	}

	private Map<String, String> uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> articleMap = new HashMap<>();
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
                        //업로드할 파일명
                        String fileName = item.getName().substring(idx+1);
                        //업로드할 파일의 경로 + 파일명을 주소로 만들어서 접근할 파일 객체 생성
                        File uploadFile = new File(currentDirPath + "\\temp\\" +fileName);
                        articleMap.put(item.getFieldName(), fileName);
                        item.write(uploadFile);
                    }

				}
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
		return articleMap;
	}
	
	
	
	
	

	
	

}
