package vo;

import java.io.Serializable;

public class Attach_FileVO implements Serializable {
	private String file_info;	// 파일정보
	private String file_name;	// 파일명
	private int email_no;		// 메일번호
	
	public int getEmail_no() {
		return email_no;
	}
	public void setEmail_no(int email_no) {
		this.email_no = email_no;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_info() {
		return file_info;
	}
	public void setFile_info(String file_info) {
		this.file_info = file_info;
	}
	
}
