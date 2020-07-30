package vo;

import java.io.Serializable;

public class Address_BookVO implements Serializable {
	private int out_no; // 번호 
	private String out_name; //이름
	private String out_email;
	private String out_tel;
	private String out_etc;
	private int book_no;		// 주소록번호
	private String book_name;	// 그룹명
	private int emp_no; 		// 사원번호
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
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}



	
	
}
