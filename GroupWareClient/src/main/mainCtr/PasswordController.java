package main.mainCtr;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.IEmployeeService;
import util.AlertUtil;
import vo.EmployeeVO;

public class PasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_phone;

    @FXML
    private Button btn_ok;

    @FXML
    private Button btn_cancle;

    @FXML
    void btnCancle(ActionEvent event) {
    	Stage stage = (Stage) btn_cancle.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void btnOk(ActionEvent event) {
    	try {
    		String id = tf_id.getText();
    		String email = tf_email.getText();
    		String phone = tf_phone.getText();
    		
    		if(id.isEmpty() || email.isEmpty() || phone.isEmpty()) {
    			AlertUtil.errorMsg("입력오류", "빈 항목이 있습니다.");
    			return;
    		}
    		if(!Pattern.matches("^[0-9]{9}$", id)) {
    			AlertUtil.errorMsg("입력 오류", "아이디를 정확하게 입력해주세요");
    			tf_id.requestFocus();
    			return;
    		}
    		
			EmployeeVO empVo = service.getEmpLogin(Integer.parseInt(id));
			if(empVo == null) {
				AlertUtil.errorMsg("입력오류", "해당 아이디가 존재하지 않습니다.");
				return;
			}
			if(!empVo.getEmp_email().equals(email) || !empVo.getEmp_phone().equals(phone)) {
				AlertUtil.errorMsg("입력오류", "해당 정보가 일치하지 않습니다. 다시 입력해주세요.");
				return;
			}else {
				try {
					String to = empVo.getEmp_email();		// 받는 이메일 주소
					String from = "hyojin1076@naver.com"; 	// 보내는 이메일 주소
					
					final String username = "hyojin1076@naver.com"; 	// 메일서버계정 
					final String password = "gywlsdlcjswo"; 			// 메일서버비밀번호
					final String host = "smtp.naver.com";
					
					Properties props = new Properties();
					props.put("mail.smtp.auth", true);
					props.put("mail.smtp.host", host);
					props.put("mail.smtp.port", 587);
					props.put("mail.smtp.starttls.enable", true);
					
					// 1) 인증 정보 제공후 Session 객체 획득
					Session session = Session.getDefaultInstance( props, new Authenticator(){
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					});
					// 2) 메시지 송/수신설정 및 메시지 구성
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(from));
					message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
					message.setSubject("GroupWare 임시 비밀번호 발송");
					
					char [] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
							'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
							'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
							'u', 'v', 'w', 'x', 'y', 'z',
							'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
							'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
							'U', 'V', 'W', 'X', 'Y', 'Z',
							'!', '@', '#', '$', '%', '^', '&', '*' };
					int idx = 0;
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < 8; i++) {
						idx = (int) (charSet.length * Math.random());
						sb.append(charSet[idx]);
					}
					String msg= "";
					msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
					msg += "<h3 style='color: blue;'>";
					msg += empVo.getEmp_name() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
					msg += "<p>임시 비밀번호 : ";
					msg += sb.toString() + "</p></div>";
					
					StringBuffer content = new StringBuffer();
					content.append(msg);
					
					// 3) HTML 소스를 Content 로 설정하되, 적절한 MIME 타입을 설정한다.
					message.setContent(content.toString(), "text/html;charset=UTF-8");
					
					
					// 3) 메시지 전송			
					Transport.send(message);
					AlertUtil.infoMsg("전송성공", "해당 이메일로 임시 비밀번호가 전송되었습니다.");			
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("field", "emp_pass");
					map.put("data", sb.toString());
					map.put("empNo", id);
					
					service.updateEmp(map);
					
					Stage stage = (Stage) btn_ok.getScene().getWindow();
			    	stage.close();
					
				} catch (MessagingException e) {
					AlertUtil.errorMsg("전송오류", "임시 비밀번호 전송에 실패하였습니다.");
					e.printStackTrace();
				}
				
			}
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }
    
    private IEmployeeService service;
    @FXML
    void initialize() {
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (IEmployeeService) reg.lookup("empService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    }
}


