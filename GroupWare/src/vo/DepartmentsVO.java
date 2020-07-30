package vo;

import java.io.Serializable;

public class DepartmentsVO implements Serializable {
	private int department_no;			// 부서번호
	private String department_name;		// 부서명		//인사팀이면 관리자
	private String department_tel;		// 부서내선번호
	
	public int getDepartment_no() {
		return department_no;
	}
	public void setDepartment_no(int department_no) {
		this.department_no = department_no;
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
