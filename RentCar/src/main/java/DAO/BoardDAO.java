package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import VO.BoardVO;
import VO.CarConfirmVO;
import VO.CarListVO;
import VO.CarOrderVO;
import VO.MemberVO;

public class BoardDAO {
	private Connection con;
	// DB와 연결 후 개발자가 만든 쿼리문을 DB에 전송해 실행할 역할을 하는 Statement실행객체의 주소를 저장할 참조 변수 선언
	private PreparedStatement pstmt;
	// 만든 쿼리문을 실행해서 조회한 검색 결과를 테이블형식으로 임시로 저장할 ResultSet객체
	private ResultSet rs;
	private DataSource dataSource;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resourceRelease() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList boardListAll() {
		ArrayList list = new ArrayList();
		try {
		con = dataSource.getConnection();
		String sql = "select * from board";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			BoardVO vo = new BoardVO(rs.getInt("b_idx"),
									  rs.getInt("b_group"),
									  rs.getInt("b_level"),
									  rs.getInt("b_cnt"),
									  rs.getString("b_id"),
									  rs.getString("b_pw"),
									  rs.getString("b_name"),
									  rs.getString("b_email"),
									  rs.getString("b_title"),
									  rs.getString("b_content"),
									  rs.getDate("b_date"));
			list.add(vo);
		}
		
		}catch (Exception e) {
			System.out.println("boardListAll SQL 오류 : " + e);
		}finally {
			resourceRelease();
		}
		return list;
	}

	public int getTotalRecord() {
		int total = 0;
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) as cnt from board";
			pstmt = con.prepareStatement(sql);
			if(rs.next()) {
				total = rs.getInt("cnt");
			}
		}catch (Exception e) {
			System.out.println("getTotalRecord 오류 : " + e);
		}finally {
			resourceRelease();
		}
		
		return total;
	}

	public int insertBoard(BoardVO vo) {
		//새글 추가에 성공하거나 실패하면   1 또는 0을 저장할 변수
		int result = 0;
		String sql = null;
		try {
			//DB연결
			con = dataSource.getConnection();
			
			//insert SQL문 만들기 
			sql = "insert into board (b_idx, b_id, b_pw, b_name, b_email, b_title, b_content, b_group,  b_level, b_date, b_cnt) "
				+ " values(border_b_idx.nextVal,?,    ?,      ?,       ?,       ?,         ?,       0,        0, sysdate,   0 )";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getB_id());
			pstmt.setString(2, vo.getB_pw());
			pstmt.setString(3, vo.getB_name());
			pstmt.setString(4, vo.getB_email());
			pstmt.setString(5, vo.getB_title());
			pstmt.setString(6, vo.getB_content());
			
			result = pstmt.executeUpdate();
				
		} catch (Exception e) {
			System.out.println("BoardDAO의 insertBoard메소드 내부에서 오류:" + e);
		} finally {
			resourceRelease();
		}
		
		return result;		
	}
	
	//현재 게시판 board테이블에 저장된 글들을 조회
		//조건 :  선택한 검색 기준열과 입력한 검색어 단어가 포함된 내용이 있는 글들을 조회!
		public ArrayList boardList(String keyField, String keyWord) {
		
			String sql = null; //조건에 따라 select문장을 다르게 만들어서 저장할 용도 
			
			ArrayList<BoardVO>  list = new ArrayList<BoardVO>();//조회된 글들 저장 용도
			
			if( !keyWord.equals("")  ) { //검색어를 입력 했다면?
				
				if(keyField.equals("name")) {//검색 기준열이 (name)b_name열이면?
					
					sql = "select * from board "
						+ " where b_name like '%"+ keyWord + "%'"
						+ " order by b_idx asc";	
							
				}else if(keyField.equals("subject")) {//검색 기준열이 (subject)b_title열이면?
					
					sql = "select * from board "
							+ " where b_title like '%"+ keyWord + "%'"
							+ " order by b_idx asc";				
					
				}else {//검색 기준열이 (content)b_content열이면?
					
					sql = "select * from board "
							+ " where b_content like '%"+ keyWord + "%'"
							+ " order by b_idx asc";				
					
				}
				
			}else {//검색어를 입력하지 않고 찾기 버튼을 눌렀을때
				//모든 글 조회
				//조건 -> b_idx열의 글번호 데이터들을 기준으로 내림차순 정렬 후 조회!
				sql = "select * from board order by b_idx asc";
				
				//참고. 정렬 조회 -> order by 정렬기준열명  desc(내림차순) 또는 asc(오름차순);
			}
			//-----------------------------------------		
			try {
				  con = dataSource.getConnection(); //DB의 테이블과 연결 
				  
				  pstmt = con.prepareStatement(sql);
				  
				  rs = pstmt.executeQuery();
				  
				  //조회된 ResultSet의 정보를 한 행 단위로 꺼내서
				  //BoardVO객체에 한행씩 저장 후 BoardVO객체들을 ArrayList배열에 하나씩 추가해서 저장
				  while(rs.next()) {				  
					  BoardVO vo = new BoardVO(rs.getInt("b_idx"), 
							  				   rs.getInt("b_group"), 
							  				   rs.getInt("b_level"), 
							  				   rs.getInt("b_cnt"), 
							  				   rs.getString("b_id"), 
							  				   rs.getString("b_pw"), 
							  				   rs.getString("b_name"), 
							  				   rs.getString("b_email"), 
							  				   rs.getString("b_title"), 
							  				   rs.getString("b_content"), 
							  				   rs.getDate("b_date"));
					  list.add(vo);				           
				  }				
				
			} catch (Exception e) {
				System.out.println("BoardDAO클래스의 boardList메소드 내부에서 SQL실행 오류:" + e);		
			} finally {
				resourceRelease();
			}
			
			return list;//BoardService로 반환
		}
	
	

	

}
