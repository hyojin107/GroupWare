package vo;

import java.io.Serializable;

public class PlanVO_join implements Serializable {
	private int plan_no;// 일정번호
	private String plan_title;// 일정명
	private String plan_content;// 내용
	private String plan_date;// 작성일자
	private String plan_start;// 일정시작일
	private String plan_end;// 일정종료일
	private int plan_del;// 삭제여부
	private int emp_no;// 사원번호
	private String plan_cnt; // 일정이 며칠인지
	private String emp_name; //사원이름 
	
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public int getPlan_del() {
		return plan_del;
	}
	public void setPlan_del(int plan_del) {
		this.plan_del = plan_del;
	}
	public String getPlan_end() {
		return plan_end;
	}
	public void setPlan_end(String plan_end) {
		this.plan_end = plan_end;
	}
	public String getPlan_start() {
		return plan_start;
	}
	public void setPlan_start(String plan_start) {
		this.plan_start = plan_start;
	}
	public String getPlan_date() {
		return plan_date;
	}
	public void setPlan_date(String plan_date) {
		this.plan_date = plan_date;
	}
	public String getPlan_content() {
		return plan_content;
	}
	public void setPlan_content(String plan_content) {
		this.plan_content = plan_content;
	}
	public String getPlan_title() {
		return plan_title;
	}
	public void setPlan_title(String plan_title) {
		this.plan_title = plan_title;
	}
	public int getPlan_no() {
		return plan_no;
	}
	public void setPlan_no(int plan_no) {
		this.plan_no = plan_no;
	}
	public String getPlan_cnt() {
		return plan_cnt;
	}
	public void setPlan_cnt(String plan_cnt) {
		this.plan_cnt = plan_cnt;
	}
	
}
