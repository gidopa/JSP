package sec02.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// 오라클 dbms의 XE 데이터베이스의 t_member테이블과 연결하여 select, insert, 
public class MemberDAO {
	// oracle DBMS서버의 DB에 접속할 정보 변수에 저장
	// ojdbc6.jar 드라이버 파일 내부에 만들어져 있는 드라이버 역할을 하는 OracleDriver.class파일의 경로 저장
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	// 오라클 DBMS 서버의 IP주소 및 사용할 DB 식별 이름
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	// 접속할 아이디
	private static final String USER = "scott";
	// 접속할 비밀번호
	private static final String PW = "tiger";
	// -----------------------------------------------------
	// 위의 4가지 접속 정보를 이용해 오라클 DB와 연결을 맺은은 접속정보를 가지는 Connection객체를 저장할 참조변수 선언
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

	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			String query = "select * from t_member order by joinDate desc";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");

				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();

		}
		return list;
	}

	public void addMember(MemberVO memberBean) {
		try {
			con = dataSource.getConnection();

			String id = memberBean.getId();
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();

			String query = "insert into t_member";
			query += " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			resourceRelease();

		}

	}

	public MemberVO findMember(String id) {
		MemberVO memInfo = null;
		try {
			con = dataSource.getConnection();
			String sql = "select * from t_member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");

				memInfo = new MemberVO();

				memInfo.setId(id);
				memInfo.setPwd(pwd);
				memInfo.setName(name);
				memInfo.setEmail(email);
				memInfo.setJoinDate(joinDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();

		}

		return memInfo;
	}

	public void modMember(MemberVO memberVO) {
		try {
			con = dataSource.getConnection();
			String sql = "update t_member set pwd=?, name=?, email=? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberVO.getPwd());
			pstmt.setString(2, memberVO.getName());
			pstmt.setString(3, memberVO.getEmail());
			pstmt.setString(4, memberVO.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();

		}
	}

	public void delMember(String id) {
		try {
			con = dataSource.getConnection();
			String sql = "delete from t_member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}
	}

}
