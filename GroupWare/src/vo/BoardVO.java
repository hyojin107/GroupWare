package vo;

import java.io.Serializable;

public class BoardVO implements Serializable {
	private int board_no;			// 게시판번호
	private String board_title;		// 제목
	private String board_date;		// 게시일자
	private String board_content;	// 내용
	private int emp_no;				// 사원번호
	private int board_del;			//자유게시글삭제
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}
	public int getBoard_del() {
		return board_del;
	}
	public void setBoard_del(int board_del) {
		this.board_del = board_del;
	}
	
	
	
}
