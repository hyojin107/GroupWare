package vo;

import java.io.Serializable;

public class NoticeVO_join implements Serializable {
	private int emp_no;				// 사원번호
	private String noti_content;	// 내용
	private String noti_date;		// 게시일자
	private String noti_title;		// 제목
	private String emp_name;
	private int noti_no;			// 공지사항번호
	private int noti_del;			// 삭제여부
	
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public String getNoti_content() {
		return noti_content;
	}
	public void setNoti_content(String noti_content) {
		this.noti_content = noti_content;
	}
	public String getNoti_date() {
		return noti_date;
	}
	public void setNoti_date(String noti_date) {
		this.noti_date = noti_date;
	}
	public String getNoti_title() {
		return noti_title;
	}
	public void setNoti_title(String noti_title) {
		this.noti_title = noti_title;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public int getNoti_no() {
		return noti_no;
	}
	public void setNoti_no(int noti_no) {
		this.noti_no = noti_no;
	}
	public int getNoti_del() {
		return noti_del;
	}
	public void setNoti_del(int noti_del) {
		this.noti_del = noti_del;
	}
	
	

	
}
