package vo;

import java.io.Serializable;

public class Employee_joinVO implements Serializable{
	
	private int emp_no; 				//사원번호
	private String emp_name;  			//사원이름
	private String emp_reg;				//주민번호
	private String emp_pass;			//비밀번호
	private String department_name; 	//부서명
	private String grade_name; 			//직급명
	private String emp_phone;			//핸드폰번호
	private String department_tel;		//내선번호
	private String emp_email; 			//이메일
	private int department_no; 			//부서번호
	private int grade_no;				//직급번호
	private int no;						//글번호(의미x)
	private String emp_addr;			// 주소
	private int emp_salary;				// 급여
	private String emp_retire_date;		// 퇴사일자
	private String emp_hire_date;		// 입사일자
	
	
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getEmp_addr() {
		return emp_addr;
	}
	public void setEmp_addr(String emp_addr) {
		this.emp_addr = emp_addr;
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
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getDepartment_tel() {
		return department_tel;
	}
	public void setDepartment_tel(String department_tel) {
		this.department_tel = department_tel;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
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
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getGrade_name() {
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	
	
	
	

}
