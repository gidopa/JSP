package Service;

import java.util.ArrayList;
import java.util.List;

import DAO.BoardDAO;
import DAO.MemberDAO;
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

	
	

}
