package VO;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private String id;
	private String pass;
	private String name;
	private Date reg_date;
	private int age;
	private String gender;
	private String address;
	private String email;
	private String tel;
	private String hp;
	
	public MemberVO() {}

	public MemberVO(String id, String pass, String name, int age, String gender, String address, String email, String tel,
			String hp) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.tel = tel;
		this.hp = hp;
	}


	public MemberVO(String id, String pass, String name, Date reg_date, int age, String gender, String address,
			String email, String tel, String hp) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.reg_date = reg_date;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.tel = tel;
		this.hp = hp;
	}
	
	
	
}
