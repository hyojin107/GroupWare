package vo;

import java.io.Serializable;

public class AttendanceVO implements Serializable {
	private int att_no;			// 근태번호
	private int emp_no;			// 사원번호
	private String att_start;	// 출근시간
	private String att_end;		// 퇴근시간
	
	public int getAtt_no() {
		return att_no;
	}
	public void setAtt_no(int att_no) {
		this.att_no = att_no;
	}
	public String getAtt_start() {
		return att_start;
	}
	public void setAtt_start(String att_start) {
		this.att_start = att_start;
	}
	public String getAtt_end() {
		return att_end;
	}
	public void setAtt_end(String att_end) {
		this.att_end = att_end;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	
}
