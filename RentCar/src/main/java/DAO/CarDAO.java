package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import VO.CarListVO;

public class CarDAO {
	private Connection con;
	// DB와 연결 후 개발자가 만든 쿼리문을 DB에 전송해 실행할 역할을 하는 Statement실행객체의 주소를 저장할 참조 변수 선언
	private PreparedStatement pstmt;
	// 만든 쿼리문을 실행해서 조회한 검색 결과를 테이블형식으로 임시로 저장할 ResultSet객체
	private ResultSet rs;
	private DataSource dataSource;

	public CarDAO() {
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

	public Vector<CarListVO> getAllCarList() {
		Vector<CarListVO> vector = new Vector<>();
		// 조회된 하나의 행 정보를 저장
		CarListVO vo = null;
		try {
			con = dataSource.getConnection();
			String sql = "select * from carlist";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new CarListVO(rs.getInt("carNo"), rs.getString("carName"), rs.getString("carCompany"),
						rs.getInt("carPrice"), rs.getInt("carUsePeople"), rs.getString("carInfo"),
						rs.getString("carImg"), rs.getString("carCategory"));
				vector.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}

		return vector;
	}

	public Vector getCategoryList(String category) {
		Vector vector = new Vector();
		CarListVO vo = null;
		try {
			con = dataSource.getConnection();
			String sql = "select * from carlist where carcategory =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new CarListVO(rs.getInt("carNo"), rs.getString("carName"), rs.getString("carCompany"),
						rs.getInt("carPrice"), rs.getInt("carUsePeople"), rs.getString("carInfo"),
						rs.getString("carImg"), rs.getString("carCategory"));
				vector.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}
		return vector;
	}

	public CarListVO getOneCar(int carno) {
		CarListVO vo = null;
		try {
			con = dataSource.getConnection();
			String sql = "select * from carlist where carno =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, carno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new CarListVO(rs.getInt("carNo"), rs.getString("carName"), rs.getString("carCompany"),
						rs.getInt("carPrice"), rs.getInt("carUsePeople"), rs.getString("carInfo"),
						rs.getString("carImg"), rs.getString("carCategory"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}
		return vo;
	}

}
