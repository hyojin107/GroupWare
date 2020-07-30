package vo;

import java.io.Serializable;

public class EmailSendListVO  implements Serializable {
	private int email_no;			// 메일번호
	private int emp_no;				// 사원번호
	private int email_del;			// 삭제여부		//삭제면1, 아니면 0
	private String email_content;	// 내용
	private String email_title;		// 제목
	private String department_name; //부서명
	private String email_rec_addr;	//받는사람 주소
	private String email_date;		//보낸 날짜
	private int no;				//글번호(의미x)
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getEmail_date() {
		return email_date;
	}
	public void setEmail_date(String email_date) {
		this.email_date = email_date;
	}
	public String getEmail_rec_addr() {
		return email_rec_addr;
	}
	public void setEmail_rec_addr(String email_rec_addr) {
		this.email_rec_addr = email_rec_addr;
	}
	public int getEmail_no() {
		return email_no;
	}
	public void setEmail_no(int email_no) {
		this.email_no = email_no;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public int getEmail_del() {
		return email_del;
	}
	public void setEmail_del(int email_del) {
		this.email_del = email_del;
	}
	public String getEmail_content() {
		return email_content;
	}
	public void setEmail_content(String email_content) {
		this.email_content = email_content;
	}
	public String getEmail_title() {
		return email_title;
	}
	public void setEmail_title(String email_title) {
		this.email_title = email_title;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	
}
