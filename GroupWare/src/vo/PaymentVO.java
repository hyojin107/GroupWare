package vo;

import java.io.Serializable;

public class PaymentVO implements Serializable {
	private String pay_date;		// 작성일자
	private String pay_content;		// 내용
	private String pay_title;		// 제목
	private int doc_no;				// 문서양식번호
	private int emp_no;				// 사원번호
	private int pay_no;				// 결재번호
	private int pay_state;			// 결재상태
	
	public String getPay_state() {
		if(pay_state == 0)
			return "진행중";
		else if(pay_state == 1)
			return "승인 완료";
		else
			return "반려";
	}
	public void setPay_state(int pay_state) {
		this.pay_state = pay_state;
	}
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
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
	public String getPay_title() {
		return pay_title;
	}
	public void setPay_title(String pay_title) {
		this.pay_title = pay_title;
	}
	public String getPay_content() {
		return pay_content;
	}
	public void setPay_content(String pay_content) {
		this.pay_content = pay_content;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	
}
