package Service;

import java.util.ArrayList;
import java.util.List;

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

	
	

}
