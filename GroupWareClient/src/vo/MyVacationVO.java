package vo;

import java.io.Serializable;

public class MyVacationVO  implements Serializable {
	private int emp_no;				// 사원번호
	private String emp_name;		// 이름 (EmployeeVO)
	private String pay_date;		// 신청서 작성일자 (PaymentVO)
	private String vac_date;		// 휴가기간
	private int vac_cnt;			// 휴가일수  (직접 계산)
	private int pay_state;			// 결재상태
	private String vac_form;		// 휴가 종류
	
	private int no = 0; 			// 글번호 (의미x)
	
	
	
	public String getVac_form() {
		return vac_form;
	}
	public void setVac_form(String vac_form) {
		this.vac_form = vac_form;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}

	public String getVac_date() {
		return vac_date;
	}
	public void setVac_date(String vac_date) {
		this.vac_date = vac_date;
	}
	public String getVac_cnt() {	// 반차는 0.5...
		if(vac_cnt == 0)
			return 0.5 + "";
		else
			return vac_cnt + "";
	}
	public void setVac_cnt(int vac_cnt) {
		this.vac_cnt = vac_cnt;
	}
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
	
	
	
}


