package vo;

import java.io.Serializable;

public class EmployeeVO implements Serializable {
	private int grade_no;					// 직급번호
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
	private int emp_no;					// 사원번호
	
	
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_sign() {
		return emp_sign;
	}
	public void setEmp_sign(String emp_sign) {
		this.emp_sign = emp_sign;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_pass() {
		return emp_pass;
	}
	public void setEmp_pass(String emp_pass) {
		this.emp_pass = emp_pass;
	}
	public String getEmp_reg() {
		return emp_reg;
	}
	public void setEmp_reg(String emp_reg) {
		this.emp_reg = emp_reg;
	}
	public String getEmp_photo() {
		return emp_photo;
	}
	public void setEmp_photo(String emp_photo) {
		this.emp_photo = emp_photo;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public String getEmp_hire_date() {
		return emp_hire_date;
	}
	public void setEmp_hire_date(String emp_hire_date) {
		this.emp_hire_date = emp_hire_date;
	}
	public String getEmp_retire_date() {
		return emp_retire_date;
	}
	public void setEmp_retire_date(String emp_retire_date) {
		this.emp_retire_date = emp_retire_date;
	}
	public int getEmp_salary() {
		return emp_salary;
	}
	public void setEmp_salary(int emp_salary) {
		this.emp_salary = emp_salary;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getEmp_addr() {
		return emp_addr;
	}
	public void setEmp_addr(String emp_addr) {
		this.emp_addr = emp_addr;
	}
	public int getEmp_vac_cnt() {
		return emp_vac_cnt;
	}
	public void setEmp_vac_cnt(int emp_vac_cnt) {
		this.emp_vac_cnt = emp_vac_cnt;
	}
	public int getDepartment_no() {
		return department_no;
	}
	public void setDepartment_no(int department_no) {
		this.department_no = department_no;
	}
	public int getGrade_no() {
		return grade_no;
	}
	public void setGrade_no(int grade_no) {
		this.grade_no = grade_no;
	}

	
}
