package vo;

import java.io.Serializable;

public class Address_joinVO implements Serializable{
	
	private String book_name;			// 그룹명
	private String out_name;			// 이름
	private String out_email;			// 이메일
	private String out_tel;		// 전화번호		
	
	
	
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
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
	

}
