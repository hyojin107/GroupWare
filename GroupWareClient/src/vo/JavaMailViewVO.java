package vo;


public class JavaMailViewVO {
	private int mailNo;			// 메일번호
	private String sentDate;	// 날짜
	private String subject;		// 제목
	private String from;		// 발신자
	private Object content;		// 메일 내용
	
	
	public JavaMailViewVO() {}
	public JavaMailViewVO(int mailNo, String sentDate, String subject, String from, Object content) {
		this.mailNo = mailNo;
		this.sentDate = sentDate;
		this.subject = subject;
		this.from = from;
		this.content = content;
	}
	
	public int getMailNo() {
		return mailNo;
	}
	public void setMailNo(int mailNo) {
		this.mailNo = mailNo;
	}
	public String getSentDate() {
		return sentDate;
	}
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
