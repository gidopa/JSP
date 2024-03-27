package Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import DAO.BoardDAO;
import DAO.MemberDAO;
import VO.BoardVO;
import VO.MemberVO;

public class BoardService {
	private BoardDAO boardDAO;
	private MemberDAO memberDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
		memberDAO = new MemberDAO();
	}
	
	//회원 아이디를 매개변수로 받아 회원 조회한 후 반환
	public MemberVO serviceMemberOne(String id) {
		
		return memberDAO.memberOne(id);
	}

	public ArrayList serviceBoardListALl() {
		return boardDAO.boardListAll();
	}

	public int getTotalRecord() {
		return boardDAO.getTotalRecord();
	}
	
	public int serviceInsertBoard(HttpServletRequest request) {
		//요청한 값들 얻기 (입력한 새글 정보들 얻기)
		String writer = request.getParameter("w");
		String email = request.getParameter("e");
		String title = request.getParameter("t");
		String content = request.getParameter("c");
		String pass = request.getParameter("p");
		String id = request.getParameter("i");
		
		//요청한 값들을 BoardVO객체의 각변수에 저장
		BoardVO vo = new BoardVO();
				vo.setB_name(writer);
				vo.setB_email(email);
				vo.setB_title(title);
				vo.setB_content(content);
				vo.setB_pw(pass);
				vo.setB_id(id);
		
		return boardDAO.insertBoard(vo);
		
	}
	
	//선택한 option검색기준열과  입력한 검색어를 이용해 글목록 검색 요청이 들어옴 
		public ArrayList serviceBoardList(HttpServletRequest request) {
			

			//		ArrayList를 BoardController로 리턴 
			return boardDAO.boardList(request.getParameter("keyField"), 
									  request.getParameter("keyWord"));
		}
		
		//선택한 opetion검색기준열과 입력한 검색어를 포함하고 있는 글갯수 검색 요청이 들어옴 
		public int  serviceGetTotalRecord(HttpServletRequest  request) {
			
			return boardDAO.getTotalRecord(request.getParameter("keyField"),
					                       request.getParameter("keyWord"));
			
		}

		public BoardVO serviceBoardRead(String b_idx) {
			BoardVO boardVO = null;
			boardVO = boardDAO.boardRead(b_idx);
			
			return boardVO;
		}

		public boolean servicePassCheck(HttpServletRequest request) {
			boolean result = false;
			String b_idx = request.getParameter("b_idx"); 
			String pass = request.getParameter("pass"); 
			result = boardDAO.passCheck(b_idx,pass);
			return result;
		}

		public int modBoard(HttpServletRequest request) {
			int res = -1;
			String email = request.getParameter("email");
			String content = request.getParameter("content");
			String title = request.getParameter("title");
			String idx = request.getParameter("idx");
			res = boardDAO.modBoard(idx,email,content,title);
			return res;
		}

		public int deleteBoard(HttpServletRequest request) {
			int res = -1;
			String idx = request.getParameter("b_idx");
			res = boardDAO.deleteBoard(idx);
			return res;
		}

		public void serviceReplyInsertBoard(HttpServletRequest request) {
			String super_b_idx = request.getParameter("super_b_idx");//부모 글번호
			String reply_id = request.getParameter("id"); //답변글 작성자 아이디
			String reply_name = request.getParameter("writer"); //답변글 작성자 이름
			String reply_email = request.getParameter("email"); //답변글 작성자 이메일			
			String reply_title = request.getParameter("title"); //답변글 제목
			String reply_content = request.getParameter("content"); //답변글 내용
			String reply_pass = request.getParameter("pass"); //답변글 비밀번호
			
			//사원 BoardDAO의 메소드를 호출하면서 매개변수로 전달해 작성한 새로운 답변글 내요을 insert시킵니다.
			boardDAO.replyInsertBoard(super_b_idx,
										reply_id,
										reply_name,
										reply_email,
										reply_title,
										reply_content,
										reply_pass
									  );
			
		
		}

	
	

}
