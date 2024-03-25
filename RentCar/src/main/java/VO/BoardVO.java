package VO;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardVO {
	private int b_idx, b_group, b_level, b_cnt;
	private String b_id, b_pw, b_name, b_email, b_title, b_content;
	private Date b_date;
	
	public BoardVO() {
	}
	
	public BoardVO(int b_idx, int b_group, int b_level, int b_cnt, String b_id, String b_pw, String b_name,
			String b_email, String b_title, String b_content) {
		super();
		this.b_idx = b_idx;
		this.b_group = b_group;
		this.b_level = b_level;
		this.b_cnt = b_cnt;
		this.b_id = b_id;
		this.b_pw = b_pw;
		this.b_name = b_name;
		this.b_email = b_email;
		this.b_title = b_title;
		this.b_content = b_content;
	}

	public BoardVO(int b_idx, int b_group, int b_level, int b_cnt, String b_id, String b_pw, String b_name,
			String b_email, String b_title, String b_content, Date b_date) {
		super();
		this.b_idx = b_idx;
		this.b_group = b_group;
		this.b_level = b_level;
		this.b_cnt = b_cnt;
		this.b_id = b_id;
		this.b_pw = b_pw;
		this.b_name = b_name;
		this.b_email = b_email;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_date = b_date;
	}
	
	
	
	
	
	
	
	
	
	/*
	 * b_pw varchar2(10), -- 글 비밀번호 b_name varchar2(50), -- 글쓴이 이름 b_email
	 * varchar2(50), -- 글 작성자의 이메일 b_title varchar2(100), -- 글 제목 b_contetnt
	 * varchar2(4000), -- 글 내용 b_group number, -- 주글과 답변글 그룹으로 묶어 줄 그룹번호 b_level
	 * number, -- 답변글의 들여쓰기 정도 값 b_date Date, -- 작성한 날짜 b_cnt number,-- 조회수
	 */
}

