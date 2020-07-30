package main.notice;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.email.GetEmailHamController;
import main.mainCtr.MainPageController;
import service.INoticeService;
import util.AlertUtil;
import vo.NoticeVO_join;

public class NoticeAddController {
 
    
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea tfCon;

    @FXML
    private TextField tfTitle;

    @FXML
    private Label tfName;

    @FXML
    private Label tDate;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancel;
    
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}

	int cnt=0;
    NoticeVO_join vo = new NoticeVO_join();
    @FXML
    void btnAddClick(ActionEvent event) throws IOException {
    
    	vo.setNoti_title(tfTitle.getText());
    	vo.setNoti_content(tfCon.getText());
    	//세션
    	int no = MainPageController.getLoginUser().getEmp_no();
    	String name = MainPageController.getLoginUser().getEmp_name();
    	vo.setEmp_no(no); 
    	vo.setEmp_name(name); 
    	vo.setNoti_date(tDate.getText());
    	if(tfTitle.getText().isEmpty() || tfCon.getText().isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "제목, 내용 둘 다 입력해주세요.");
    		return;
    	}
    	
    	//서버로 정보보내기
    	try {
			cnt=service.insertNotice(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		} if(cnt>0) {
			AlertUtil.infoMsg("등록성공", "새로운 공지가 등록되었습니다.");
			backToList();
		}
    	
    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
    	 backToList();
    }
    
    void backToList() throws IOException {
    	FXMLLoader loader = new FXMLLoader(NoticeAddController.class.getResource("/fxml/notice/NoticeList.fxml"));
        Parent root = loader.load();
		NoticeListController hamCtr = loader.getController();
		hamCtr.setMain(main);
		main.getMainborder().setCenter(root);

    	
//        FXMLLoader loader = new FXMLLoader(NoticeAddController.class.getResource("/fxml/notice/NoticeList.fxml"));
//        Parent root = loader.load();
//        Stage stage = (Stage) btnAdd.getScene().getWindow();
//        Scene scene = new Scene (root);
//        stage.setScene(scene);
//        stage.setTitle("공지리스트");
//        stage.show();
    }
    private INoticeService service;
    String time1="";
    String name="";
    @FXML
    void initialize() {
    	
    	//(service = BoardServiceImpl.getInstance();)
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (INoticeService) reg.lookup("notiService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
   
    	//글 작성시 작성날짜 오늘날짜로 미리보여주기
    	SimpleDateFormat format1 = new SimpleDateFormat ("yyyy/MM/dd");
    	Date time = new Date();
    	time1 = format1.format(time); 
    	tDate.setText(time1);

    	//글 작성시 작성자 미리보여주기 
    	String name = MainPageController.getLoginUser().getEmp_name();
    	tfName.setText(name);

    	 assert tfCon != null : "fx:id=\"tfCon\" was not injected: check your FXML file 'NoticeAdd.fxml'.";
         assert tfTitle != null : "fx:id=\"tfTitle\" was not injected: check your FXML file 'NoticeAdd.fxml'.";
         assert tDate != null : "fx:id=\"tDate\" was not injected: check your FXML file 'NoticeAdd.fxml'.";
         assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'NoticeAdd.fxml'.";
         assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'NoticeAdd.fxml'.";
         assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'NoticeAdd.fxml'.";



    }
}
