package vo;

import java.io.Serializable;

public class ReferenceVO implements Serializable {
	private int emp_no;		// 사원번호
	private int pay_no;		// 결재번호
	
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
}
