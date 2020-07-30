   package vo;

import java.io.Serializable;

public class PaymentListVO implements Serializable {
	private String pay_date;			// 작성일자
	private String pay_content;			// 내용
	private String pay_title;			// 제목
	private int doc_no;					// 문서양식번호
	private int emp_no;					// 사원번호
	private int pay_no;					// 결재번호
	private int grade_no;				// 직급번호
	private int department_no;			// 부서번호
	private int emp_vac_cnt;			// 휴가일수
	private String emp_addr;			// 주소
	private String emp_phone;			// 핸드폰번호
	private int emp_salary;				// 급여
	private String emp_retire_date;		// 퇴사일자
	private String emp_hire_date;		// 입사일자
	private String emp_email;			// 이메일
	private String emp_photo;			// 사원사진
	private String emp_reg;				// 주민번호
	private String emp_pass;			// 비밀번호
	private String emp_name;			// 이름
	private String emp_sign;			// 결재싸인파일
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
	public int getGrade_no() {
		return grade_no;
	}
	public void setGrade_no(int grade_no) {
		this.grade_no = grade_no;
	}
	public int getDepartment_no() {
		return department_no;
	}
	public void setDepartment_no(int department_no) {
		this.department_no = department_no;
	}
	public int getEmp_vac_cnt() {
		return emp_vac_cnt;
	}
	public void setEmp_vac_cnt(int emp_vac_cnt) {
		this.emp_vac_cnt = emp_vac_cnt;
	}
	public String getEmp_addr() {
		return emp_addr;
	}
	public void setEmp_addr(String emp_addr) {
		this.emp_addr = emp_addr;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public int getEmp_salary() {
		return emp_salary;
	}
	public void setEmp_salary(int emp_salary) {
		this.emp_salary = emp_salary;
	}
	public String getEmp_retire_date() {
		return emp_retire_date;
	}
	public void setEmp_retire_date(String emp_retire_date) {
		this.emp_retire_date = emp_retire_date;
	}
	public String getEmp_hire_date() {
		return emp_hire_date;
	}
	public void setEmp_hire_date(String emp_hire_date) {
		this.emp_hire_date = emp_hire_date;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public String getEmp_photo() {
		return emp_photo;
	}
	public void setEmp_photo(String emp_photo) {
		this.emp_photo = emp_photo;
	}
	public String getEmp_reg() {
		return emp_reg;
	}
	public void setEmp_reg(String emp_reg) {
		this.emp_reg = emp_reg;
	}
	public String getEmp_pass() {
		return emp_pass;
	}
	public void setEmp_pass(String emp_pass) {
		this.emp_pass = emp_pass;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_sign() {
		return emp_sign;
	}
	public void setEmp_sign(String emp_sign) {
		this.emp_sign = emp_sign;
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
