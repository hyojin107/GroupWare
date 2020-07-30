package vo;

import java.io.Serializable;

public class VacationVO implements Serializable {
	private int pay_no;					// 결재번호
	private String vac_end_date;		// 휴가종료일자
	private String vac_start_date;		// 휴가시작일자
	private String vac_form;			// 휴가 종류
	
	public int getPay_no() {
		return pay_no;
	}
	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}
	public String getVac_start_date() {
		return vac_start_date;
	}
	public void setVac_start_date(String vac_start_date) {
		this.vac_start_date = vac_start_date;
	}
	public String getVac_end_date() {
		return vac_end_date;
	}
	public void setVac_end_date(String vac_end_date) {
		this.vac_end_date = vac_end_date;
	}
	public String getVac_form() {
		return vac_form;
	}
	public void setVac_form(String vac_form) {
		this.vac_form = vac_form;
	}
	
}
