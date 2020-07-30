package vo;

import java.io.Serializable;

public class PositionVO implements Serializable {
	private int pos_no;			// 직급번호	// 사원: 1, 대리: 2, 과장: 3, 부장: 4, 대표이사: 5
	private String pos_name;	// 직급명
	
	public int getPos_no() {
		return pos_no;
	}
	public void setPos_no(int pos_no) {
		this.pos_no = pos_no;
	}
	public String getPos_name() {
		return pos_name;
	}
	public void setPos_name(String pos_name) {
		this.pos_name = pos_name;
	}
	
}
