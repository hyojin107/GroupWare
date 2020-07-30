package main.email;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import main.mainCtr.MainPageController;
import vo.EmployeeVO;
import vo.JavaMailViewVO;

public class GetEmailHamController {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private HTMLEditor lb_content;

    @FXML
    private Text lb_Title;

    @FXML
    private Label lb_from;

    @FXML
    private Label lb_me;

    @FXML
    private Label lb_date;

//    @FXML
//    private TextArea lb_content;

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
    void btnReply(ActionEvent event) {
    	
    }
    
    @FXML
    void btnBack(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/email/GetEmailList.fxml"));
		Parent root = loader.load();
		GetEmailListController ctr = loader.getController();
		ctr.setMain(main);
		
		main.getMainborder().setCenter(root);
    }


    void setting(JavaMailViewVO jmVo) throws MessagingException, IOException {
    	lb_Title.setText(jmVo.getSubject());
    	lb_from.setText(jmVo.getFrom());
    	lb_me.setText("\"" + loginUser.getEmp_name() + "\"<"+loginUser.getEmp_email()+">");
    	lb_date.setText(jmVo.getSentDate());
    	
    	lb_content.setHtmlText((String)jmVo.getContent());
    	
    	
    	
//    	System.out.println("\n\n\n\n"+jmVo.getContent().toString());
    	
    }
    


	private EmployeeVO loginUser;
    @FXML
    void initialize() {
    	loginUser = MainPageController.getLoginUser();
    	
    	
    }
}





