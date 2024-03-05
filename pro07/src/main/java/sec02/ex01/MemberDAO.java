package sec02.ex01;
import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


// 오라클 dbms의 XE 데이터베이스의 t_member테이블과 연결하여 select, insert, 
public class MemberDAO {
	// 위의 4가지 접속 정보를 이용해 오라클 DB와 연결을 맺은은 접속정보를 가지는 Connection객체를 저장할 참조변수 선언
	private Connection con;
	// DB와 연결 후 개발자가 만든 쿼리문을 DB에 전송해 실행할 역할을 하는 PreparedStatement실행객체의 주소를 저장할 참조 변수 선언
	private PreparedStatement pstmt;
	// 만든 쿼리문을 실행해서 조회한 검색 결과를 테이블형식으로 임시로 저장할 ResultSet객체
	private ResultSet rs;
	private DataSource dataSource; 
	
	//역할 : new MemberDAO(); 객체 생성 시 호출되는 생성자로 생성자 내부에서 커넥션풀 역할을 하는 DataSource 객체를 context.xml에 작성한 정보를 이용해 얻음
	public MemberDAO() {
		try {
			// 톰캣서버가 context.xml의 설정을 읽어와 톰캣 서버의 메모리에 context 객체들을 생성해 저장 해 줌
			// 이때 InitialContext 객체는 톰캣서버 실행시 context.xml에 의해 생성된 Context객체들에 접근하는 역할
			Context ctx = new InitialContext();
			//JDNI기법(key 또는 name속성의 값을 이용해 value를 얻는 방법)으로 접근하기 위해 기본경로(java:/comp/env)를 InitialContext객체에 지정
			//lookup은 톰캣 서버의 환경설정에 관련된 context객체들에 접근하기위한 기본경로 주소를 설정
			Context envContext = (Context)ctx.lookup("java:/comp/env"); 
			// 톰캣은 context.xml에 설정한 name 속성값 "jdbc/oracle"(JNDI 기법 사용을 위한 key)를 이용해 커넥션풀을 얻을 수 있음
			dataSource = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e){
			System.out.println("DataSource 커넥션풀 객체 얻기 실패 : " + e);
		}
	}
	
	// 오라클 DBMS 서버 내부에 XE데이터베이스 내부에 만든 t_member테이블의 모든 회원정보를 조회해서 제공하는 메소드
	public ArrayList listMembers(){// <- 멤버서블릿에서 회원정보 조회를 위해 호출하는 메소드
		ArrayList list = new ArrayList();
		try {
			//DB와 연결을 위해 dataSource커넥션풀 객체 내부에 DB와 연결된 Connection 객체 50개중 하나를 받아옴
			Connection con = dataSource.getConnection();
			
			// DB의 테이블 조회하는 쿼리문 작성
			String query = "select * from t_member";
			// Connection 객체의 prepareStatement메소드 호출 시 실행할 쿼리문을 매개변수로 전달하면 ? 를 제외한 문장을 
			// preparedStatement객체에 저장후 객체 자체를 반환 
			pstmt = con.prepareStatement(query);
			//pstmt.setString(1, "hong");
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
	
	public void addMember(MemberVO memberVO) {
		try {
			con = dataSource.getConnection();
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			String query = "insert into t_member";
			query += " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
