package vo;

import java.io.Serializable;

public class CalendarVO  implements Serializable {
	private int cal_num;
	private String cal_conn;
	private String cal_title;
	private String cal_sdate;
	private String cal_edate;
	
	
	public int getCal_num() {
		return cal_num;
	}
	public void setCal_num(int cal_num) {
		this.cal_num = cal_num;
	}
	public String getCal_conn() {
		return cal_conn;
	}
	public void setCal_conn(String cal_conn) {
		this.cal_conn = cal_conn;
	}
	public String getCal_title() {
		return cal_title;
	}
	public void setCal_title(String cal_title) {
		this.cal_title = cal_title;
	}
	public String getCal_sdate() {
		return cal_sdate;
	}
	public void setCal_sdate(String cal_sdate) {
		this.cal_sdate = cal_sdate;
	}
	public String getCal_edate() {
		return cal_edate;
	}
	public void setCal_edate(String cal_edate) {
		this.cal_edate = cal_edate;
	}
	
	
}
