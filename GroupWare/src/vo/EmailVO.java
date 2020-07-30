package vo;

import java.io.Serializable;

public class EmailVO implements Serializable {
	private int emp_no;				// 사원번호
	private int email_del;			// 삭제여부		//삭제면0, 아니면 1
	private String email_content;	// 내용
	private String email_title;		// 제목
	private int email_no;			// 메일번호
	
	public int getEmail_no() {
		return email_no;
	}
	public void setEmail_no(int email_no) {
		this.email_no = email_no;
	}
	public String getEmail_title() {
		return email_title;
	}
	public void setEmail_title(String email_title) {
		this.email_title = email_title;
	}
	public String getEmail_content() {
		return email_content;
	}
	public void setEmail_content(String email_content) {
		this.email_content = email_content;
	}
	public int getEmail_del() {
		return email_del;
	}
	public void setEmail_del(int email_del) {
		this.email_del = email_del;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	
}
