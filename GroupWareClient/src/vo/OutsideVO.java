package vo;

import java.io.Serializable;

public class OutsideVO implements Serializable {
	private int book_no;		// 주소록번호
	private String out_etc;		// 기타사항
	private String out_tel;		// 전화번호
	private String out_email;	// 이메일
	private String out_name;	// 이름
	private int out_no;			// 외부사람번호
	
	
	public int getOut_no() {
		return out_no;
	}
	public void setOut_no(int out_no) {
		this.out_no = out_no;
	}
	public String getOut_name() {
		return out_name;
	}
	public void setOut_name(String out_name) {
		this.out_name = out_name;
	}
	public String getOut_email() {
		return out_email;
	}
	public void setOut_email(String out_email) {
		this.out_email = out_email;
	}
	public String getOut_tel() {
		return out_tel;
	}
	public void setOut_tel(String out_tel) {
		this.out_tel = out_tel;
	}
	public String getOut_etc() {
		return out_etc;
	}
	public void setOut_etc(String out_etc) {
		this.out_etc = out_etc;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	
}
