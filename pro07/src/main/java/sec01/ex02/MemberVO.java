package sec01.ex02;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

/*
 VO(Value Object)역할을 하는 클래스
 	VO역할이란?
 	DB의 테이블에 저장된 정보를 조회한 후 조회한 레코드의 컬럼값들을 VO클래스 내부의 변수에 저장하거나 
 	변수에 저장된 값들을 VO클래스의 객체를 생성해 DB에 Insert시키기 위해 사용되는 클래스
 	
 	VO역할을 하는 클래스 만드는 방법
 	테이블에서 조회한 레코드의 컬럼값들을 변수에 저장해야 하므로 테이블의 열명,데이터 유형에 맞게 변수를 선언하고 getter, setter 메소드 추가
*/
@Getter
@Setter
public class MemberVO{
	 private String id;
	 private String pwd;
	 private String name;
	 private String email;
	 private Date joinDate;
	 
	 public MemberVO() {
		 System.out.println("MemberVO 기본생성자");
	 }
	 
}
