package vo;

import java.io.Serializable;

public class Salary_SpecsVO implements Serializable {
	private int doc_no;			// 문서양식번호
	private int salary;			// 수령급여액
	private String sal_date;	// 급여일자
	private int emp_no;			// 사원번호
	
	private String salDate;//    to_char(sal_date,'yyyymm') 
	private String salYear;//	 to_char(sal_date, 'yyyy' ) 
	private String salMonth;//	 to_char(sal_date, 'mm') 
	private String sal_title;//	 to_char(sal_date, 'mm') 
	private String sal_name="인사/총무과";
	

	public String getSal_name() {
		return sal_name;
	}
	public void setSal_name(String sal_name) {
		this.sal_name = sal_name;
	}
	

	public String getSalDate() {
	return salDate;
	}
	public void setSalDate(String salDate) {
		this.salDate = salDate;
	}
	public String getSalYear() {
		return salYear;
	}
	public void setSalYear(String salYear) {
		this.salYear = salYear;
	}
	public String getSalMonth() {
		return salMonth;
	}
	public void setSalMonth(String salMonth) {
		this.salMonth = salMonth;
	}
	public String getSal_title() {
		return sal_title;
	}
	public void setSal_title(String sal_title) {
		this.sal_title = sal_title;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getSal_date() {
		return sal_date;
	}
	public void setSal_date(String sal_date) {
		this.sal_date = sal_date;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getDoc_no() {
		return doc_no;
	}
	public void setDoc_no(int doc_no) {
		this.doc_no = doc_no;
	}
}
