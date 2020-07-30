package main.email;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.mainCtr.MainPageController;
import service.IEmailService;
import util.AlertUtil;
import vo.EmailVO;
import vo.Email_ReceptionVO;
import vo.EmployeeVO;

public class SendEmailController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf_addr;

    @FXML
    private TextField tf_title;

    @FXML
    private Button btn_addrMem;

    @FXML
    private TextArea ta_content;

    @FXML
    private Button btn_ok;

    @FXML
    private Button btn_cancle;
    
    private MainPageController main;
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }

    @FXML
    void btnCancle(ActionEvent event) throws IOException {
    	if(AlertUtil.confirm("취소", "작성하신 메일을 취소하시겠습니까?") == false) {
    		return;
    	}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/email/GetEmailList.fxml"));
        Parent root = loader.load();
        GetEmailListController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
    }

    @FXML
    void btnOk(ActionEvent event) {
    	try {
    		if(AlertUtil.confirm("확인", "작성하신 메일을 보내시겠습니까?") == false) {
        		return;
        	}
    		
    		if(tf_addr.getText().isEmpty() || tf_title.getText().isEmpty() || ta_content.getText().isEmpty())
    			AlertUtil.errorMsg("입력오류", "항목을 모두 입력해주세요.");
    		
    		emailVo = new EmailVO();
    		emailVo.setEmp_no(loginUser.getEmp_no());
    		emailVo.setEmail_title(tf_title.getText());
    		emailVo.setEmail_content(ta_content.getText());
			emailService.sendEmail(emailVo);
			
			int emailNo = emailService.getLastEmailNo(loginUser.getEmp_no());
			
			// 메일 수신자 등록
			String[] addr = tf_addr.getText().split(",");	// 메일수신자가 여러명일 경우 ,로 구분
			eRecVo = new Email_ReceptionVO();
			eRecVo.setEmail_no(emailNo);
			
			for(int i=0; i<addr.length; i++) {
				//메일 수신자 정규식
				if(!Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", addr[i].trim())) {
					AlertUtil.errorMsg("이메일 입력 오류", "이메일을 올바르게 입력해주세요");
					tf_addr.requestFocus();
					return;
				}
				eRecVo.setEmail_rec_addr(addr[i].trim());
				emailService.insertEmailReception(eRecVo);
				
				
				// 이메일 보내기 (웹)
				String[] type = addr[i].trim().split("@");
				if(type[1].equals("gmail.com")) {
					webSendEmail(addr[i].trim(), 1);
				}else {
					webSendEmail(addr[i].trim(), 0);
				}
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/email/GetEmailList.fxml"));
	        Parent root = loader.load();
	        GetEmailListController ctr = loader.getController();
	        ctr.setMain(main);
	        
	        main.getMainborder().setCenter(root);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    void webSendEmail(String to1, int mailType) {
    	try {
	    	String to = to1;		// 받는 이메일 주소
			String from = loginUser.getEmp_email(); 	// 보내는 이메일 주소
			
			final String username = "rmfnqdnpdj123@naver.com"; 	// 메일서버계정 
			final String password = "123zxcASD!@#"; 			// 메일서버비밀번호
			
			Properties props = new Properties();
			props.put("mail.smtp.auth", true);
			if(mailType == 0) {		//네이버일 경우
				final String host = "smtp.naver.com";			
				props.put("mail.smtp.host", host);
			}else {	//gmail일 경우
				final String host = "smtp.gmail.com";
				props.put("mail.smtp.host", host);
			}
			props.put("mail.smtp.port", 587);
			props.put("mail.smtp.starttls.enable", true);
			
			
			// 1) 인증 정보 제공후 Session 객체 획득
//			Session session = Session.getDefaultInstance( props, new Authenticator(){
			Session session = Session.getInstance( props, new Authenticator(){
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
		
			// 2) 메시지 송/수신설정 및 메시지 구성
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(tf_title.getText());
			message.setText(ta_content.getText());
			
			// 3) 메시지 전송
			Transport.send(message);
			System.out.println("전송 완료");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }
    
    
    @FXML
    void btn_addrMem(ActionEvent event) {
    	
    	
    }

    private EmailVO emailVo;
    private Email_ReceptionVO eRecVo;
    private IEmailService emailService;
    private EmployeeVO loginUser;
    @FXML
    void initialize() {
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			emailService = (IEmailService) reg.lookup("emailService");
			loginUser = MainPageController.getLoginUser();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    }
}







