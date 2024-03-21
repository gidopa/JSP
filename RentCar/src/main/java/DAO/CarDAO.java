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

	public void insertCarOrder(CarOrderVO vo, HttpSession session) {
		String id = (String) session.getAttribute("id");
		String sql = null;
		try {
			con = dataSource.getConnection();
			if (id == null) {
				sql = "insert into non_carorder(non_orderid," + "carno," + "carqty," + "carreserveday,"
						+ "carbegindate," + "carins," + "carwifi," + "carnave," + "carbabyseat," + "memberphone,"
						+ "memberpass)" + "values (non_carorder_non_orderid.nextval," + "?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, vo.getCarno());
				pstmt.setInt(2, vo.getCarqty());
				pstmt.setInt(3, vo.getCarreserveday());
				pstmt.setString(4, vo.getCarbegindate());
				pstmt.setInt(5, vo.getCarins());
				pstmt.setInt(6, vo.getCarwifi());
				pstmt.setInt(7, vo.getCarnavi());
				pstmt.setInt(8, vo.getCarseat());
				pstmt.setString(9, vo.getMemberphone());
				pstmt.setString(10, vo.getMemberpass());

			} else {
				if (vo.getId() == null) {
					System.out.println("null");
				} else {
					System.out.println(vo.getId());
				}
				sql = "insert into carorder(orderid," + "id," + "carno," + "carqty," + "carreserveday,"
						+ "carbegindate," + "carins," + "carwifi," + "carnave," + "carbabyseat," + "memberphone,"
						+ "memberpass)" + "values (carorder_orderid.nextval,"
						+ "?,?,?,?,?,?,?,?,?,(select hp from member where id='" + id + "'),?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				pstmt.setInt(2, vo.getCarno());
				pstmt.setInt(3, vo.getCarqty());
				pstmt.setInt(4, vo.getCarreserveday());
				pstmt.setString(5, vo.getCarbegindate());
				pstmt.setInt(6, vo.getCarins());
				pstmt.setInt(7, vo.getCarwifi());
				pstmt.setInt(8, vo.getCarnavi());
				pstmt.setInt(9, vo.getCarseat());
				pstmt.setString(10, vo.getMemberpass());
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertCarorder SQL :" + e);
		} finally {
			resourceRelease();
		}

	}

	public Vector<CarConfirmVO> getAllCarOrder(String memberphone, String memberpass) {
		Vector<CarConfirmVO> vector = new Vector<>();
		try {
			con = dataSource.getConnection();
			// 예약한 날짜가 오늘날짜보다 크고 예약당시 입력한 전하번호와 비밀번호로 예약한 렌트 정보를 조회하는데
			// carorder테이블의 열들과 carlist 테이블의 열들의 값들을 연결해서 조히
			/*
			 * natural join 두 테이블 사이에서 공통된 칼럼을 찾아 그 칼럼을 기준으로 조인 두 테이블간 동일한 이름을 가진 칼럼이 있을 때
			 * 사용됩니다 TO_DATE 문자열을 날짜로 변환하는 Oracle의 함수 , sysdate(현재 날짜)보다 예약한 날짜가 더 이후일 때를 뜻함
			 */
			String sql = "SELECT * FROM non_carorder NATURAL JOIN carlist "
					+ "WHERE sysdate < TO_DATE(carbegindate, 'YYYY-MM-DD') AND " + "memberphone=? AND memberpass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberphone);
			pstmt.setString(2, memberpass);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CarConfirmVO vo = new CarConfirmVO();
				vo.setOrderid(rs.getInt("NON_ORDERID"));// 렌트 예약 ID
				vo.setCarqty(rs.getInt("CARQTY")); // 대여수량
				vo.setCarreserveday(rs.getInt(4)); // 대여기간
				vo.setCarbegindate(rs.getString(5)); // 대여한 날짜
				vo.setCarins(rs.getInt("CARINS")); // 보험 적용 여부
				vo.setCarwifi(rs.getInt("CARWIFI")); // 와이피이 적용 여부
				vo.setCarnave(rs.getInt(8)); // 네비 적용 여부
				vo.setCarbabyseat(rs.getInt(9));// 베이비시트 적용 여부
				vo.setCarname(rs.getString(12));// 예약한 차량명
				vo.setCarprice(rs.getInt(14));// 하루 대여금액
				vo.setCarimg(rs.getString(17));// 차량 이미지명
				vector.add(vo);
			}
			return vector;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}

		return vector;
	}

	public CarConfirmVO getOneOrder(int orderid) {
		CarConfirmVO vo = new CarConfirmVO();
		try {
			con = dataSource.getConnection();
			String sql = "select * from non_carorder where non_orderid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setOrderid(rs.getInt("NON_ORDERID"));// 렌트 예약 ID
				vo.setCarqty(rs.getInt("CARQTY")); // 대여수량
				vo.setCarreserveday(rs.getInt("carreserveday")); // 대여기간
				vo.setCarbegindate(rs.getString("carbegindate")); // 대여한 날짜
				vo.setCarins(rs.getInt("CARINS")); // 보험 적용 여부
				vo.setCarwifi(rs.getInt("CARWIFI")); // 와이피이 적용 여부
				vo.setCarnave(rs.getInt("carnave")); // 네비 적용 여부
				vo.setCarbabyseat(rs.getInt("carbabyseat"));// 베이비시트 적용 여부
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}

		return vo;
	}

	public int carOrderUpdate(HttpServletRequest request) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			String sql = "update non_carorder set " + "carbegindate=?, carreserveday=?, carins=?, carbabyseat=?, "
					+ "carwifi=?, carnave=?, carqty=? " + "where non_orderid=? and memberpass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("carbegindate"));
			pstmt.setInt(2, Integer.parseInt(request.getParameter("carreserveday")));
			pstmt.setInt(3, Integer.parseInt(request.getParameter("carins")));
			pstmt.setInt(4, Integer.parseInt(request.getParameter("carbabyseat")));
			pstmt.setInt(5, Integer.parseInt(request.getParameter("carwifi")));
			pstmt.setInt(6, Integer.parseInt(request.getParameter("carnave")));
			pstmt.setInt(7, Integer.parseInt(request.getParameter("carqty")));
			pstmt.setInt(8, Integer.parseInt(request.getParameter("orderid")));
			pstmt.setString(9, request.getParameter("memberpass"));
			// 업데이트에 성공하면 성공한 레코드 개수 1반환 실패하면 0 반환
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}

		return result;
	}
	
	public int carMemberOrderUpdate(HttpServletRequest request) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			String sql = "update carorder set " + "carbegindate=?, carreserveday=?, carins=?, carbabyseat=?, "
					+ "carwifi=?, carnave=?, carqty=? " + "where orderid=? and memberpass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("carbegindate"));
			pstmt.setInt(2, Integer.parseInt(request.getParameter("carreserveday")));
			pstmt.setInt(3, Integer.parseInt(request.getParameter("carins")));
			pstmt.setInt(4, Integer.parseInt(request.getParameter("carbabyseat")));
			pstmt.setInt(5, Integer.parseInt(request.getParameter("carwifi")));
			pstmt.setInt(6, Integer.parseInt(request.getParameter("carnave")));
			pstmt.setInt(7, Integer.parseInt(request.getParameter("carqty")));
			pstmt.setInt(8, Integer.parseInt(request.getParameter("orderid")));
			pstmt.setString(9, request.getParameter("memberpass"));
			// 업데이트에 성공하면 성공한 레코드 개수 1반환 실패하면 0 반환
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}

		return result;
	}

	public int deleteOrder(int orderid, String memberpass) {
		int res = 0;
		try {
			con = dataSource.getConnection();
			String sql = "delete from non_carorder where non_orderid=? and memberpass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderid);
			pstmt.setString(2, memberpass);
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}
		return res;

	}

	public Vector<CarConfirmVO> getAllCarOrderMember(String memberid, String memberpass) {
		Vector<CarConfirmVO> vector = new Vector<>();
		try {
			con = dataSource.getConnection();
			// 예약한 날짜가 오늘날짜보다 크고 예약당시 입력한 전하번호와 비밀번호로 예약한 렌트 정보를 조회하는데
			// carorder테이블의 열들과 carlist 테이블의 열들의 값들을 연결해서 조히
			/*
			 * natural join 두 테이블 사이에서 공통된 칼럼을 찾아 그 칼럼을 기준으로 조인 두 테이블간 동일한 이름을 가진 칼럼이 있을 때
			 * 사용됩니다 TO_DATE 문자열을 날짜로 변환하는 Oracle의 함수 , sysdate(현재 날짜)보다 예약한 날짜가 더 이후일 때를 뜻함
			 */
			String sql = "SELECT * FROM carorder NATURAL JOIN carlist "
					+ "WHERE sysdate < TO_DATE(carbegindate, 'YYYY-MM-DD') AND " + "id=? AND memberpass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberid);
			pstmt.setString(2, memberpass);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CarConfirmVO vo = new CarConfirmVO();
				vo.setOrderid(rs.getInt("ORDERID"));// 렌트 예약 ID
				vo.setCarqty(rs.getInt("CARQTY")); // 대여수량
				vo.setCarreserveday(rs.getInt("carreserveday")); // 대여기간
				vo.setCarbegindate(rs.getString("carbegindate")); // 대여한 날짜
				vo.setCarins(rs.getInt("CARINS")); // 보험 적용 여부
				vo.setCarwifi(rs.getInt("CARWIFI")); // 와이피이 적용 여부
				vo.setCarnave(rs.getInt("carnave")); // 네비 적용 여부
				vo.setCarbabyseat(rs.getInt("carbabyseat"));// 베이비시트 적용 여부
				vo.setCarname(rs.getString("carname"));// 예약한 차량명
				vo.setCarprice(rs.getInt("carprice"));// 하루 대여금액
				vo.setCarimg(rs.getString("carimg"));// 차량 이미지명
				vector.add(vo);
			}
			return vector;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}

		return vector;
	}

	public int deleteMemberOrder(int orderid, String memberpass) {
		int res = 0;
		try {
			con = dataSource.getConnection();
			String sql = "delete from carorder where orderid=? and memberpass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderid);
			pstmt.setString(2, memberpass);
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}
		return res;

	}

	public CarConfirmVO getOneMemberOrder(int orderid) {
		CarConfirmVO vo = new CarConfirmVO();
		try {
			con = dataSource.getConnection();
			String sql = "select * from carorder where orderid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setOrderid(rs.getInt("ORDERID"));// 렌트 예약 ID
				vo.setCarqty(rs.getInt("CARQTY")); // 대여수량
				vo.setCarreserveday(rs.getInt("carreserveday")); // 대여기간
				vo.setCarbegindate(rs.getString("carbegindate")); // 대여한 날짜
				vo.setCarins(rs.getInt("CARINS")); // 보험 적용 여부
				vo.setCarwifi(rs.getInt("CARWIFI")); // 와이피이 적용 여부
				vo.setCarnave(rs.getInt("carnave")); // 네비 적용 여부
				vo.setCarbabyseat(rs.getInt("carbabyseat"));// 베이비시트 적용 여부
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceRelease();
		}

		return vo;
	}
}
