package vo;

import java.io.Serializable;

public class Address_MemberVO implements Serializable {
	private int book_no;	// 주소록번호
	private int emp_no;		// 사원번호
	
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	
}
