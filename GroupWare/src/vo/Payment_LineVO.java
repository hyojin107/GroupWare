package vo;

import java.io.Serializable;

public class Payment_LineVO implements Serializable {
	private int pay_no;			// 결재번호
	private int pay_order;		// 결재순서
	private int pay_check;		// 승인/반려	//진행중: 0, 승인: 1, 반려: 2
	private int emp_no;			// 사원번호
	private String emp_name;
	private String grade_name;
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getGrade_name() {
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public int getPay_order() {
		return pay_order;
	}
	public void setPay_order(int pay_order) {
		this.pay_order = pay_order;
	}
	public int getPay_check() {
		return pay_check;
	}
	public void setPay_check(int pay_check) {
		this.pay_check = pay_check;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	
}
