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
	
	

	

}
