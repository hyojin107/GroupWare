package vo;

import java.io.Serializable;

public class ImgFileVO implements Serializable {
	private int emp_no;				// 사원번호
	private byte[] emp_photo;		// 사원사진
	private byte[] emp_sign;		// 결재싸인파일
	
	
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public byte[] getEmp_photo() {
		return emp_photo;
	}
	public void setEmp_photo(byte[] emp_photo) {
		this.emp_photo = emp_photo;
	}
	public byte[] getEmp_sign() {
		return emp_sign;
	}
	public void setEmp_sign(byte[] emp_sign) {
		this.emp_sign = emp_sign;
	}
	
	
}
