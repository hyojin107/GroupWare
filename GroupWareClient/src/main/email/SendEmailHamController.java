package main.email;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import main.mypage.MypageController;
import vo.EmailSendListVO;
import vo.EmployeeVO;

public class SendEmailHamController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text lb_Title;

    @FXML
    private Label lb_from;

    @FXML
    private Label lb_addr;

    @FXML
    private Label lb_date;

    @FXML
    private TextArea lb_content;
    
    @FXML
    private Button btn_back;
    
    private MainPageController main;
    public MainPageController getMain() {
      return main;
    }
    public void setMain(MainPageController main) {
      this.main = main;
    }
    
    @FXML
    void btnBack(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/email/SendEmailList.fxml"));
        Parent root = loader.load();
        SendEmailListController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
    }

    void setting(EmailSendListVO sendVo) throws MessagingException, IOException {
    	lb_Title.setText(sendVo.getEmail_title());
    	lb_from.setText(loginUser.getEmp_name() + "\"<"+loginUser.getEmp_email()+">");
    	lb_addr.setText(sendVo.getEmail_rec_addr());
    	lb_content.setText(sendVo.getEmail_content());
    	lb_date.setText(sendVo.getEmail_date());
    	
    }
    
    private EmployeeVO loginUser;
    @FXML
    void initialize() {
    	loginUser = MainPageController.getLoginUser();
    }
}



