package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import VO.CarConfirmVO;
import VO.CarListVO;
import VO.CarOrderVO;
import VO.MemberVO;

public class MemberDAO{
	private Connection con;
	// DB와 연결 후 개발자가 만든 쿼리문을 DB에 전송해 실행할 역할을 하는 Statement실행객체의 주소를 저장할 참조 변수 선언
	private PreparedStatement pstmt;
	// 만든 쿼리문을 실행해서 조회한 검색 결과를 테이블형식으로 임시로 저장할 ResultSet객체
	private ResultSet rs;
	private DataSource dataSource;

	public MemberDAO() {
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

	// MemberServiceImpl에서 호출
	// 회원정보가 담긴 vo를 받아 DB에 쿼리 날림
	public void insertMember(MemberVO vo) {
		try {
			con = dataSource.getConnection();
			String sql = "insert into member(id,pass,name,reg_date,age,gender,address,email,tel,hp) "
					+ "values(? , ?, ?, sysdate, ?, ?, ?, ? , ? ,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setInt(4, vo.getAge());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getTel());
			pstmt.setString(9, vo.getHp());
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("MemberDAO의 sql 오류" + e);
		} finally {
			resourceRelease();
		}
	}

	public int userCheck(String loginId, String loginPass) {
		int check = -1;
		try {
			con = dataSource.getConnection();
			String sql = "select * from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (loginPass.equals(rs.getString("pass"))) {
					check = 1; // 아이디 O , 비밀번호 O
				} else { // 아이디 O , 비밀번호 X
					check = 0;
				}
			} else { // 아이디 X
				check = -1;
			}
		} catch (Exception e) {
			System.out.println("DAO.userCheck : " + e);
		} finally {
			resourceRelease();
		}
		return check;
	}

	public MemberVO memberOneIdPass(String id) {
		MemberVO vo = null;
		try {
			con = dataSource.getConnection();
			String sql = "select id,pass from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
				vo.setId(id);
				vo.setPass(rs.getString("pass"));
			}
			
		}catch (Exception e) {
			System.out.println("DAO.memberOneIdPass sql 에러 : "+e);
		}finally {
			resourceRelease();
		}
		return vo;
	}

}
