package vo;

import java.io.Serializable;

public class Document_FormVO implements Serializable {
	private int doc_no;			// 문서양식번호
	private String doc_name;	// 문서양식명
	private int doc_del;		// 삭제여부		//삭제면 0, 아니면 1
	
	public int getDoc_no() {
		return doc_no;
	}
	public void setDoc_no(int doc_no) {
		this.doc_no = doc_no;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public int getDoc_del() {
		return doc_del;
	}
	public void setDoc_del(int doc_del) {
		this.doc_del = doc_del;
	}

}
