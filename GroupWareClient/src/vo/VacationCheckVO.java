package vo;

import java.io.Serializable;

public class VacationCheckVO implements Serializable{
	private int pay_no;					// 결재번호
	private String vac_end_date;		// 휴가종료일자
	private String vac_start_date;		// 휴가시작일자
	private String vac_form;			// 휴가 종류
	private String pay_date;		// 작성일자
	private String pay_content;		// 내용
	private String pay_title;		// 제목
	private int doc_no;				// 문서양식번호
	private int emp_no;				// 사원번호
	private int pay_state;			// 결재상태
	private String emp_name;			// 이름
	private String department_name;		// 부서명		//인사팀이면 관리자
	private int pay_order;
	
	public int getPay_order() {
		return pay_order;
	}
	public void setPay_order(int pay_order) {
		this.pay_order = pay_order;
	}
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public String getVac_end_date() {
		return vac_end_date;
	}
	public void setVac_end_date(String vac_end_date) {
		this.vac_end_date = vac_end_date;
	}
	public String getVac_start_date() {
		return vac_start_date;
	}
	public void setVac_start_date(String vac_start_date) {
		this.vac_start_date = vac_start_date;
	}
	public String getVac_form() {
		return vac_form;
	}
	public void setVac_form(String vac_form) {
		this.vac_form = vac_form;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getPay_content() {
		return pay_content;
	}
	public void setPay_content(String pay_content) {
		this.pay_content = pay_content;
	}
	public String getPay_title() {
		return pay_title;
	}
	public void setPay_title(String pay_title) {
		this.pay_title = pay_title;
	}
	public int getDoc_no() {
		return doc_no;
	}
	public void setDoc_no(int doc_no) {
		this.doc_no = doc_no;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public int getPay_state() {
		return pay_state;
	}
	public void setPay_state(int pay_state) {
		this.pay_state = pay_state;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
}
