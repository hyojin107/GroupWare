package vo;

import java.io.Serializable;

public class CertificateVO implements Serializable {
	private String cer_content;// 용도
	private String cer_submit;// 제출처
	private int cer_check;// 승인/반려
	private String cer_date;// 신청일자
	private int doc_no;// 문서양식번호2
	private int emp_no;// 사원번호2
	private int cer_no;// 증명서번호
	private String emp_name; //사원이름
	private String doc_name; //증명서 종류이름 
	private String grade_name;//직급
	private String department_name; //부서명
	private String emp_hire_date; //입사일
	private String emp_reg; //주민번호
	private String emp_addr; //주소
	private String department_tel; //내선번호
	private String chname; //승인반려한글나오기
	
	public String getChname() {
		return chname;
	}
	public void setChname(String chname) {
		this.chname = chname;
	}
	public String getCer_content() {
		return cer_content;
	}
	public void setCer_content(String cer_content) {
		this.cer_content = cer_content;
	}
	public String getCer_submit() {
		return cer_submit;
	}
	public void setCer_submit(String cer_submit) {
		this.cer_submit = cer_submit;
	}
	public int getCer_check() {
		return cer_check;
	}
	public void setCer_check(int cer_check) {
		this.cer_check = cer_check;
		if(cer_check==0) {
			chname="진행중";
		}else if(cer_check==1) {
			chname="승인완료";
		}else if(cer_check==2) {
			chname="반려";
		}
	}
	public String getCer_date() {
		return cer_date;
	}
	public void setCer_date(String cer_date) {
		this.cer_date = cer_date;
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
	public int getCer_no() {
		return cer_no;
	}
	public void setCer_no(int cer_no) {
		this.cer_no = cer_no;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
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
	public String getEmp_hire_date() {
		return emp_hire_date;
	}
	public void setEmp_hire_date(String emp_hire_date) {
		this.emp_hire_date = emp_hire_date;
	}
	public String getEmp_reg() {
		return emp_reg;
	}
	public void setEmp_reg(String emp_reg) {
		this.emp_reg = emp_reg;
	}
	public String getEmp_addr() {
		return emp_addr;
	}
	public void setEmp_addr(String emp_addr) {
		this.emp_addr = emp_addr;
	}
	public String getDepartment_tel() {
		return department_tel;
	}
	public void setDepartment_tel(String department_tel) {
		this.department_tel = department_tel;
	}
	
	
	
}
