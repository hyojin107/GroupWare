package vo;

import java.io.Serializable;

public class Temp_SaveVO implements Serializable {
	private String temp_content;	// 내용
	private String temp_title;		// 제목
	private String ref_line;		// 참조선
	private String pay_line;		// 결재선
	private int doc_no;				// 문서양식번호
	private int emp_no;				// 사원번호
	
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public int getDoc_no() {
		return doc_no;
	}
	public void setDoc_no(int doc_no) {
		this.doc_no = doc_no;
	}
	public String getPay_line() {
		return pay_line;
	}
	public void setPay_line(String pay_line) {
		this.pay_line = pay_line;
	}
	public String getRef_line() {
		return ref_line;
	}
	public void setRef_line(String ref_line) {
		this.ref_line = ref_line;
	}
	public String getTemp_title() {
		return temp_title;
	}
	public void setTemp_title(String temp_title) {
		this.temp_title = temp_title;
	}
	public String getTemp_content() {
		return temp_content;
	}
	public void setTemp_content(String temp_content) {
		this.temp_content = temp_content;
	}
}
