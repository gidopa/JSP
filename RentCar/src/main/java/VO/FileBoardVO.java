package VO;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileBoardVO {
	private int b_idx;
	private String b_id, b_pw, b_name, b_email, b_title, b_content;
	private int b_group, b_level;
	private Date b_date;
	private int b_cnt; // 글 조회수
	private String ofile, sifle;
	private int downcount; // 다운로드 횟수
	
	public FileBoardVO() {
	}

	public FileBoardVO(int b_idx, String b_id, String b_pw, String b_name, String b_email, String b_title,
			String b_content, int b_group, int b_level, Date b_date, int b_cnt, String ofile, String sifle,
			int downcount) {
		super();
		this.b_idx = b_idx;
		this.b_id = b_id;
		this.b_pw = b_pw;
		this.b_name = b_name;
		this.b_email = b_email;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_group = b_group;
		this.b_level = b_level;
		this.b_date = b_date;
		this.b_cnt = b_cnt;
		this.ofile = ofile;
		this.sifle = sifle;
		this.downcount = downcount;
	}

	public FileBoardVO(int b_idx, String b_id, String b_pw, String b_name, String b_email, String b_title,
			String b_content, int b_group, int b_level, int b_cnt, String ofile, String sifle, int downcount) {
		super();
		this.b_idx = b_idx;
		this.b_id = b_id;
		this.b_pw = b_pw;
		this.b_name = b_name;
		this.b_email = b_email;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_group = b_group;
		this.b_level = b_level;
		this.b_cnt = b_cnt;
		this.ofile = ofile;
		this.sifle = sifle;
		this.downcount = downcount;
	}
	
	
	
	
	
	
	
	
}
