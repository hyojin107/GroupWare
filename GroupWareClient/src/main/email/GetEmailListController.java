package main.email;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.mainCtr.LoginController;
import main.mainCtr.MainPageController;
import vo.EmployeeVO;
import vo.JavaMailViewVO;

public class GetEmailListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button btn_write;

    @FXML
    private TableView<JavaMailViewVO> table;

    @FXML
    private TableColumn<?, ?> colNo;

    @FXML
    private TableColumn<?, ?> colFrom;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private Pagination pagination;
    
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}
    
    
    @FXML
    void btnWrite(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/email/SendEmail.fxml"));
        Parent root = loader.load();
        SendEmailController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
    	
    }
    
	
    
    void emailReceive(String addr, String pwd, int mail) throws MessagingException,	IOException {
    	String username = addr; 	// 계정
		String password = pwd;	// 비번
		String host = "";
		if(mail == 0) {
			host = "imap.naver.com";			
		}else if(mail == 1) {
			host = "imap.gmail.com";			
		}
		int port = 993;
		Properties props = new Properties();
		props.put("mail.imaps.host", host);
		props.put("mail.imaps.port", port);
		props.put("mail.imaps.starttls.enable", true);
		
		// 1) 세션 객체 생성
		Session session = Session.getDefaultInstance(props);
		
//		try {
			// 2) 프로토콜에 해당하는 Store 객체 생성 및 연결
			Store store = session.getStore("imaps");
			store.connect(host, username, password);
			
			// 3) 메시지 폴더 객체 생성
			Folder folder = store.getFolder("INBOX");
			// 읽기 전용으로 폴더 개방
			folder.open(Folder.READ_ONLY);
			
			// 4) 메시지 접근, 최근 메시지 순으로 확인하기 위해 전체 메시지 건수 조회
			int totalCount = folder.getMessageCount();
			
			// 전체 메시지 건수를 기준으로 페이징
			Message[] messages = folder.getMessages(1, totalCount);
			
			//////////////////////////////////////////////////////////////////
			
			for(int i=messages.length-1; i>=0; i--) {
				mailNo = messages[i].getMessageNumber();
				sentDate = messages[i].getSentDate();
				
				subject = messages[i].getSubject();
				from = ((InternetAddress)messages[i].getFrom()[0]).toUnicodeString();
				if(subject.equals("")) subject = "(제목없음)";
				
				messageWriting(messages[i]);
				time = new SimpleDateFormat("yyyy/MM/dd  a hh:mm");
				jmData.add(new JavaMailViewVO(mailNo, time.format(sentDate), subject, from, content));
			}
			ObservableList<JavaMailViewVO> jmList = FXCollections.observableArrayList(jmData);
			table.setItems(jmList);
			
			
			// 5) folder 폐쇄전 수정가능 모드에서 삭제된 메시지가 있다면 삭제 반영여부를 결정
			folder.close(false);
			store.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
    }
    
    void messageWriting(Part part) throws MessagingException,	IOException {
//		System.out.println("Content-Type : " + part.getContentType());
		
		content = part.getContent();		//내용
		
		if(part.isMimeType("text/*")){ // 문자열 Content 에 대한 처리
//			System.out.println("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ"+content);
		}else if(part.isMimeType("multipart/*")){ // Multipart Content 처리
			Multipart mp = (Multipart) content;
			int bodyCnt = mp.getCount();
			//System.out.println("========================= CNT : " + bodyCnt);
			for(int i=0; i<bodyCnt; i++){
				messageWriting(mp.getBodyPart(i));
			}
		}else if(part.isMimeType("message/rfc822")){ // 중첩 메시지에 대한 처리
			messageWriting((Part) content);
		}
		else if(part.isMimeType("image/*")){ // 이미지 처리
			InputStream in = (InputStream) content;
			String name = part.getFileName();
			if(name==null || name.trim().length()==0){
				name = UUID.randomUUID().toString();
			} 
//			Files.copy(in, Paths.get("d:/d_other/연습용/"+name), StandardCopyOption.REPLACE_EXISTING);
		}
		else if(part.isMimeType("application/octet-stream")){ // 첨부파일 처리
			if(part instanceof MimeBodyPart){
				String name = part.getFileName();
				if(name==null || name.trim().length()==0){
					name =UUID.randomUUID().toString();
				} 
				//((MimeBodyPart)part).saveFile(new File("첨부파일저장경로/"+name));
//				((MimeBodyPart)part).saveFile(new File("d:/d_other/연습용/"+name));
			}
		}else{
//			System.out.println(content);
		} 
		
		
	}
    
    
    
    List<JavaMailViewVO> jmData = new ArrayList<JavaMailViewVO>();
    SimpleDateFormat time = new SimpleDateFormat("yyyy/MM/dd  a hh:mm");
    Object content;
    int mailNo;
    Date sentDate;
    String subject;
    String from;
    
    @FXML
    void tableCliecked(MouseEvent e) throws IOException, MessagingException {
    	if(e.getClickCount()==2) {
    		JavaMailViewVO jmVo = (JavaMailViewVO) table.getSelectionModel().getSelectedItem();
    		
//    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/email/GetEmailHam.fxml"));
//    		Parent root = loader.load();
//    		GetEmailHamController geCtr = loader.getController();
//    		
//    		
//    		Scene scene = new Scene(root);
//    		Stage sub = new Stage(StageStyle.DECORATED);
//    		sub.setScene(scene);
//    		sub.setTitle("받은 메일함 상세보기");
//    		sub.show();
    		
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/email/GetEmailHam.fxml"));
    		Parent root = loader.load();
    		GetEmailHamController hamCtr = loader.getController();
    		hamCtr.setting(jmVo);
    		hamCtr.setMain(main);
    		
    		main.getMainborder().setCenter(root);
    	}
    }
    
    
    ObservableList<JavaMailViewVO> jmList;
    private EmployeeVO loginUser;
    @FXML
    void initialize() throws MessagingException, IOException {
    	loginUser = MainPageController.getLoginUser();
    	int mail = 0;
    	String[] splits = loginUser.getEmp_email().split("@");
    	if(splits[1].equals("naver.com")) {
    		mail = 0;
    	}else {
    		mail = 1;	//구글
    	}
    	
    	colNo.setCellValueFactory(new PropertyValueFactory<>("mailNo"));
    	colDate.setCellValueFactory(new PropertyValueFactory<>("sentDate"));
    	colTitle.setCellValueFactory(new PropertyValueFactory<>("subject"));
    	colFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
    	
    	emailReceive(loginUser.getEmp_email(), loginUser.getEmp_pass(), mail);
    	
    }
}






