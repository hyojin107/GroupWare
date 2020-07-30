package vo;

import java.io.Serializable;

public class Email_ReceptionVO implements Serializable {
	private String email_rec_addr;	// 받은사람메일주소
	private int email_no;			// 메일번호
	
	public int getEmail_no() {
		return email_no;
	}
	public void setEmail_no(int email_no) {
		this.email_no = email_no;
	}
	public String getEmail_rec_addr() {
		return email_rec_addr;
	}
	public void setEmail_rec_addr(String email_rec_addr) {
		this.email_rec_addr = email_rec_addr;
	}
	
}
