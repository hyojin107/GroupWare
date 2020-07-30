package main.mainCtr;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.IAttendanceService;
import service.IEmployeeService;
import util.AlertUtil;
import vo.EmployeeVO;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf_id;

    @FXML
    private PasswordField  tf_pwd;

    @FXML
    private Button btn_login;

    @FXML
    void btnLoginClicked(ActionEvent event) {
    	
    	try {
    		String id = tf_id.getText();
    		String pwd = tf_pwd.getText();
    		if(id.isEmpty() || pwd.isEmpty()) {
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
			if(!empVo.getEmp_pass().equals(pwd)) {
				AlertUtil.errorMsg("입력오류", "아이디와 비밀번호가 다릅니다.");
				return;
			}
			int cnt = attService.selectStartDate(empVo.getEmp_no());
			System.out.println(cnt);
			if(cnt == 0) attService.insertStartDate(empVo.getEmp_no());
			
			Stage s = (Stage) btn_login.getScene().getWindow();
			s.close();
			
//			FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("../../fxml/main/MainPage.fxml"));
//			FXMLLoader loader = new FXMLLoader(Class.forName("main.mainCtr.LoginController").getResource("../../fxml/main/MainPage.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main/MainPage.fxml"));
			
			Parent root = loader.load();
    		Scene scene = new Scene(root);
    		
    		MainPageController mainCtr = loader.getController();
    		mainCtr.setLoginVO(empVo);
    		
    		Stage sub = new Stage(StageStyle.DECORATED);
    		sub.setScene(scene);
    		sub.setTitle("GroupWare");
    		sub.show();
			
			
    		
    		
			
//			FXMLLoader loader = FXMLLoader.load(LoginController.class.getResource("../../fxml/main/MainPage.fxml"));
//			Parent root = loader.load();
//			MainPageController change = loader.getController();
//			change.setLogin(this);
//			change.setLoginVO(empVo);
//			
//			Scene scene = new Scene(root);
//			s.setScene(scene);
//			s.setTitle("GroupWare");
//			s.show();
			
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void btnPwdClicked(MouseEvent event) {
    	try {
//    		Parent root = FXMLLoader.load(LoginController.class.getResource("../../fxml/main/Password.fxml"));
//			Parent root = FXMLLoader.load(Class.forName("main.mainCtr.LoginController").getResource("../../fxml/main/Password.fxml"));
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/main/Password.fxml"));
    		Scene scene = new Scene(root);
    		
    		Stage sub = new Stage(StageStyle.DECORATED);
    		sub.setScene(scene);
    		sub.setTitle("비밀번호찾기");
    		sub.show();
			
		} catch (Exception  e) {
			e.printStackTrace();
		}
    	
    	
    }
    
    private IEmployeeService service;
    private IAttendanceService attService;
    @FXML
    void initialize() {
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (IEmployeeService) reg.lookup("empService");
			attService = (IAttendanceService) reg.lookup("attService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	
    }
}



