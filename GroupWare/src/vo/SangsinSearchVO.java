package vo;

import java.io.Serializable;

public class SangsinSearchVO implements Serializable {
	private int emp_no;
	private String emp_name;
	private String grade_name;
	private String department_name;
	private int pay_order;
	private int pay_check;
	
	public int getPay_check() {
		return pay_check;
	}
	public void setPay_check(int pay_check) {
		this.pay_check = pay_check;
	}
	public int getPay_order() {
		return pay_order;
	}
	public void setPay_order(int pay_order) {
		this.pay_order = pay_order;
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
	public String getGrade_name() {
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
}
