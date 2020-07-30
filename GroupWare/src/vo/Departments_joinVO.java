package vo;

import java.io.Serializable;

public class Departments_joinVO implements Serializable {
	private String emp_name;			// 이름
	private String emp_phone;			// 핸드폰번호
	private String emp_email;			// 이메일
	private String department_name;		// 부서명		//인사팀이면 관리자
	private String department_tel;		// 부서내선번호
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getDepartment_tel() {
		return department_tel;
	}
	public void setDepartment_tel(String department_tel) {
		this.department_tel = department_tel;
	}
	
}
