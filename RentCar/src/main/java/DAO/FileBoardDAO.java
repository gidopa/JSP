package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
import VO.FileBoardVO;
import VO.MemberVO;

public class FileBoardDAO {
	private Connection con;
	// DB와 연결 후 개발자가 만든 쿼리문을 DB에 전송해 실행할 역할을 하는 Statement실행객체의 주소를 저장할 참조 변수 선언
	private PreparedStatement pstmt;
	// 만든 쿼리문을 실행해서 조회한 검색 결과를 테이블형식으로 임시로 저장할 ResultSet객체
	private ResultSet rs;
	private DataSource dataSource;

	public FileBoardDAO() {
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
//fileboard테이블에 저장된 글 조회
	public List<FileBoardVO> boardListAll() {
		List<FileBoardVO> list = new ArrayList<>();
		try {
		con = dataSource.getConnection();
		String sql = "select * from fileboard order by b_group asc";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			FileBoardVO vo = new FileBoardVO(rs.getInt("b_idx"),
											 rs.getString("b_id"),
											 rs.getString("b_pw"),
											 rs.getString("b_name"),
											 rs.getString("b_email"),
											 rs.getString("b_title"),
											 rs.getString("b_content"),
											 rs.getInt("b_group"),
											 rs.getInt("b_level"),
											 rs.getDate("b_date"),
											 rs.getInt("b_cnt"),
											 rs.getString("ofile"),
											 rs.getString("sfile"),
											 rs.getInt("downcount"));
			list.add(vo);
		}
		
		}catch (Exception e) {
			System.out.println("FileBoardDAO.boardListAll SQL 오류 : " + e);
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
			rs = pstmt.executeQuery();
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

	public int insertBoard(FileBoardVO vo) {
		int articleNO = getNewArticleNo();
		//새글 추가에 성공하거나 실패하면   1 또는 0을 저장할 변수
		String sql = null;
		try {
			//DB연결
			con = dataSource.getConnection();
			sql = "update fileboard set b_group = b_group + 1";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			//insert SQL문 만들기 
			sql = "insert into fileboard (b_idx, b_id, b_pw, b_name, b_email, b_title, b_content, b_group,  b_level, b_date, b_cnt,  sfile, downcount)"
								+ " values(?,      ?,    ?,      ?,       ?,       ?,         ?,       0,        0, sysdate,   0 ,    ?,        0)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,articleNO);
			pstmt.setString(2, vo.getB_id());
			pstmt.setString(3, vo.getB_pw());
			pstmt.setString(4, vo.getB_name());
			pstmt.setString(5, vo.getB_email());
			pstmt.setString(6, vo.getB_title());
			pstmt.setString(7, vo.getB_content());
			pstmt.setString(8, vo.getSifle());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("FileBoardDAO의 insertBoard메소드 내부에서 오류:" + e);
		} finally {
			resourceRelease();
		}
		
		return articleNO; //새글정보를 insert한 후 insert 한 후 insert한 글번호 반환 
		// 글번호를 반환해서 글번호 폴더 생성해 그 안에 업로드한 파일을 이동시켜 저장
	}
	
	private int getNewArticleNo() {
		try {
			con = dataSource.getConnection();
			
			String sql = "select max(b_idx) from fileboard";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return (rs.getInt(1) + 1); //insert할 글번호 만들어서 반환
			}				
		} catch (Exception e) {
			System.out.println("getNewArticleNO메소드 내부에서 오류 :");
			e.printStackTrace();
		} finally {
			resourceRelease();
		}		
		return 0;
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
						+ " order by b_group asc";	
							
				}else if(keyField.equals("subject")) {//검색 기준열이 (subject)b_title열이면?
					
					sql = "select * from board "
							+ " where b_title like '%"+ keyWord + "%'"
							+ " order by b_group asc";				
					
				}else {//검색 기준열이 (content)b_content열이면?
					
					sql = "select * from board "
							+ " where b_content like '%"+ keyWord + "%'"
							+ " order by b_group asc";				
					
				}
				
			}else {//검색어를 입력하지 않고 찾기 버튼을 눌렀을때
				//모든 글 조회
				//조건 -> b_idx열의 글번호 데이터들을 기준으로 내림차순 정렬 후 조회!
				sql = "select * from board order by b_group asc";
				
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
		
		public int getTotalRecord(String keyField, String keyWord) {
			
			String sql = null; //조건에 따라 select문장을 다르게 만들어서 저장할 용도 
			
			//조회된 글들의 갯수 저장
			int total = 0;
			
			if( !keyWord.equals("")  ) { //검색어를 입력 했다면?
				
				if(keyField.equals("name")) {//검색 기준열이 (name)b_name열이면?
					
					sql = "select count(*) as cnt from board "
						+ " where b_name like '%"+ keyWord + "%'"
						+ " order by b_group asc";	
							
				}else if(keyField.equals("subject")) {//검색 기준열이 (subject)b_title열이면?
					
					sql = "select count(*) as cnt from board "
							+ " where b_title like '%"+ keyWord + "%'"
							+ " order by b_group asc";				
					
				}else {//검색 기준열이 (content)b_content열이면?
					
					sql = "select count(*) as cnt from board "
							+ " where b_content like '%"+ keyWord + "%'"
							+ " order by b_group asc";				
					
				}
				
			}else {//검색어를 입력하지 않고 찾기 버튼을 눌렀을때
				//모든 글 조회
				//조건 -> b_idx열의 글번호 데이터들을 기준으로 내림차순 정렬 후 조회!
				sql = "select count(*) as cnt from board order by b_group asc";
				
				//참고. 정렬 조회 -> order by 정렬기준열명  desc(내림차순) 또는 asc(오름차순);
			}
			//-----------------------------------------		
			try {
				  con = dataSource.getConnection(); //DB의 테이블과 연결 
				  
				  pstmt = con.prepareStatement(sql);
				  
				  rs = pstmt.executeQuery();
				  
				  //조회된 ResultSet의 조회갯수를 한행 얻어 
				  if(rs.next()) {				  
					 total = rs.getInt("cnt");
				  }				
				
			} catch (Exception e) {
				System.out.println("BoardDAO클래스의 getTotalRecord메소드 내부에서 SQL실행 오류:" + e);		
			} finally {
				resourceRelease();
			}		
			return total;//BoardService로 반환
		}

		//글을 조회하면 조히수 증가 , 글을 조회
		public BoardVO boardRead(String b_idx) {
			BoardVO boardVO = null;
			String sql;
			try {
				  con = dataSource.getConnection(); //DB의 테이블과 연결 
				  sql = "update board set b_cnt=b_cnt + 1 where b_idx=?";
				  pstmt = con.prepareStatement(sql);
				  pstmt.setInt(1, Integer.parseInt(b_idx));
				  pstmt.executeUpdate();
				  sql = "select * from board where b_idx = ?";
				  pstmt = con.prepareStatement(sql);
				  pstmt.setInt(1, Integer.parseInt(b_idx));
				  rs = pstmt.executeQuery();
				  if(rs.next()) {
					  boardVO = new BoardVO(rs.getInt("b_idx"), 
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
	  		           		   
				  }
				  
			}catch (Exception e) {
				System.out.println("boardRead 오류 : " + e) ;
			}finally {
				resourceRelease();
			}
			return boardVO;
			
		}

		public boolean passCheck(String b_idx, String pass) {
			boolean result = false;
			try {
				con = dataSource.getConnection();
				String sql = "select * from board where b_idx=? and b_pw=? order by b_idx desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(b_idx) );
				pstmt.setString(2, pass);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result=true;
				}else {
					result=false;
				}
			}catch (Exception e) {
				System.out.println(" BoardDAO passCheck 오류 : " + e);
			}finally {
				resourceRelease();
			}
			return result;
		}

		public int modBoard(String idx,String email,String content,String title) {
			int result = -1;
			try {
				con = dataSource.getConnection();
				String sql = "update board set b_title=?, b_email=?, b_content=? where b_idx=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, email);
				pstmt.setString(3, content);
				pstmt.setString(4, idx);
				result = pstmt.executeUpdate();
			}catch (Exception e) {
				System.out.println("boarDAO modBoard 오류 : " + e); 
			}finally {
				resourceRelease();
			}
		
			return result;
		}

		public int deleteBoard(String idx) {
			int res = -1;
			
		try {
			con = dataSource.getConnection();
			String sql = "delete from board where b_idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idx);
			res= pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("boarDAO deleteBoard 오류 : " + e); 
		}finally {
			resourceRelease();
		}
		return res;
		}

		public void replyInsertBoard(String super_b_idx, String reply_id, String reply_name, String reply_email,
				String reply_title, String reply_content, String reply_pass) {
			String sql = null;
			
			try {
					con = dataSource.getConnection();//DB연결
					
					//1. 부모글(주글)의 글번호를 이용해 b_group열의 값과 , b_level열의 값을 조회 
					sql = "select b_group, b_level from board where b_idx=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, super_b_idx); //부모글(주글) 글번호 셋팅 
					rs = pstmt.executeQuery();
					rs.next();
					String b_group = rs.getString("b_group");//주글의 그룹번호
					String b_level = rs.getString("b_level");//주글의 들여쓰기정도값 
					
					//답변글 다는 규칙1.
					//주글(부모글)의 b_group열의 값보다 큰 값을 가지는 주글이 있다면
					//주글의 b_group을 1증가한 값으로 수정
					sql = "update board set b_group = b_group + 1 where b_group > ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, b_group);
					pstmt.executeUpdate();
					
					//답변글 다는 규칙2
					//-> 답변글을  insert할떄 주글(부모글)의 b_group열의 값에 1더한값으로 insert
					//답변글 다는 규칙3
					//-> 답변글을 insert할떄 주글(부모글)의 b_level열의 값에 1더한 값으로 insert
					//답변글 insert SQL문 만들기
					sql = "insert into board (b_idx, b_id, b_pw, b_name, b_email, b_title, b_content, b_group,  b_level, b_date, b_cnt) "
				    + " values(border_b_idx.nextVal,    ?,    ?,      ?,       ?,       ?,         ?,       ?,        ?, sysdate,   0 )";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, reply_id); //답변글을 작성하는 로그인된 사람의 아이디 
					pstmt.setString(2, reply_pass);//답글을 작성시 입력했던 답변글의 비밀번호 
					pstmt.setString(3, reply_name);//답변글 작성하는 사람 이름
					pstmt.setString(4, reply_email);//답변글 작성하는 사람 이메일주소 
					pstmt.setString(5, reply_title);//답변글 제목 
					pstmt.setString(6, reply_content);//답변글의 내용 
					pstmt.setInt(7, Integer.parseInt(b_group) + 1); //주글의 그룹값에 1더한값 추가 //규칙2.
					pstmt.setInt(8, Integer.parseInt(b_level) + 1); //주글의 레벨값에 1더한값 추가 //규칙3.
					pstmt.executeUpdate();
					
			} catch (Exception e) {
				System.out.println("BoardDAO의 replyInsertBoard메소드 내부에서 SQL문 실행 오류:" + e);
			} finally {
				resourceRelease();
			}
		}
	
	

	

}
