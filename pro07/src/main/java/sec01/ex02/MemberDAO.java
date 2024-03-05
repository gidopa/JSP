package sec01.ex02;
import java.sql.*;
import java.util.ArrayList;


// 오라클 dbms의 XE 데이터베이스의 t_member테이블과 연결하여 select, insert, 
public class MemberDAO {
	// oracle DBMS서버의 DB에 접속할 정보 변수에 저장
	// ojdbc6.jar 드라이버 파일 내부에 만들어져 있는 드라이버 역할을 하는 OracleDriver.class파일의 경로 저장
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	// 오라클 DBMS 서버의 IP주소 및 사용할 DB 식별 이름
	private static final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// 접속할 아이디
	private static final String USER = "scott";
	// 접속할 비밀번호
	private static final String PW = "tiger";
	//-----------------------------------------------------
	// 위의 4가지 접속 정보를 이용해 오라클 DB와 연결을 맺은은 접속정보를 가지는 Connection객체를 저장할 참조변수 선언
	private Connection con;
	// DB와 연결 후 개발자가 만든 쿼리문을 DB에 전송해 실행할 역할을 하는 PreparedStatement실행객체의 주소를 저장할 참조 변수 선언
	private PreparedStatement pstmt;
	// 만든 쿼리문을 실행해서 조회한 검색 결과를 테이블형식으로 임시로 저장할 ResultSet객체
	private ResultSet rs;
	// OracleDriver 클랫의 객체를 동적으로 생성해 DriverManager클래스의 변수에 저장(드라이버로딩)
	// Connection 객체 생성
	// Statement 실행 객체 생성
	private void connDB() {
		// oracleDriver 클래스를 JBM 메모리에 로딩, MemberDAO와 오라클 DBMS의 t_member테이블과 연동을 위해
		// Class.forName()메소드로 드라이버 클래스를 동적으로 로드
		try {
			Class.forName(DRIVER);
			// OracledDriver를 통해 DB와 접속하여 접속 정보를 가지고 있는 T4CConnection객체 얻기
			// Class.forName(DRIVER)로 생성된 ORacleDriver 객체는 DriverManager클래스에 등록되어 있으므로
			// 이 드라이버 역할을 하는 객체를 통해 MemberDAO와 오라클 DBMS서버의 테이블과 연결 맺은 정보를 가지는 T4CConnection객체를 얻을 수 있다
			con = DriverManager.getConnection(URL,USER,PW);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	// 오라클 DBMS 서버 내부에 XE데이터베이스 내부에 만든 t_member테이블의 모든 회원정보를 조회해서 제공하는 메소드
	public ArrayList listMembers(){// <- 멤버서블릿에서 회원정보 조회를 위해 호출하는 메소드
		ArrayList list = new ArrayList();
		try {
			//오라클 드라이버 로딩, Connection 객체 얻기
			connDB();
			// DB의 테이블 조회하는 쿼리문 작성
			String query = "select * from t_member where id=?";
			// Connection 객체의 prepareStatement메소드 호출 시 실행할 쿼리문을 매개변수로 전달하면 ? 를 제외한 문장을 
			// preparedStatement객체에 저장후 객체 자체를 반환 
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "hong");
			// 쿼리문 전송
			// 쿼리문의 결과를 임시로 저장하는 ResultSet객체의 참조변수 rs
			// 조회된 화면의 커서위치는 가장 처음에는 조회된 테이블의 제목열 행을 가리키고 있음.
			// 여기서는 preparedStatement를 사용하기 때문에 executeQeury()의 매개변수를 줄 필요가 없음
			rs = pstmt.executeQuery();
			// 커서가 테이블에서 한 행씩 내려가면서 조회가 되면 true 안되면 false 반환
			// 조회한 회원 레코드들이 rs에 표형태로 저장됨, 계속 반복해서 레크드 단위의 조회된 열 값들을 
			// MemberVO에 박아 넣는다. MemberVO 객를 ArrayList에 추가
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joindate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
		}catch (Exception e) {
			System.out.println("listMembers 내부 쿼리문 실행오류 :" + e);
		}
		return list; 
	} 
	
}
