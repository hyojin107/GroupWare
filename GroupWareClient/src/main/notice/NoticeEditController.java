package main.notice;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
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
import vo.NoticeVO;
import vo.NoticeVO_join;

public class NoticeEditController { 
	private INoticeService service;
	private Map<String,String> noticeMap;

    private NoticeDetailController noti3;
	
	public NoticeDetailController getNoti3() {
		return noti3;
	}

	public void setNoti3(NoticeDetailController noti3) {
		this.noti3 = noti3;
	}


	NoticeVO_join joinVO = new NoticeVO_join();
	public void setNoticeVO(NoticeVO_join joinVO) {
		this.joinVO = joinVO; //리스트에서 선택한 게시글의 정보가 담긴 VO
		tfTitle.setText((String) joinVO.getNoti_title());	
		tDate.setText((String) joinVO.getNoti_date());
		tfName.setText((String) joinVO.getEmp_name());
		tfCon.setText((String) joinVO.getNoti_content());

	}
	
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea tfCon;

    @FXML
    private TextField tfTitle;

    @FXML
    private Label tDate;

    @FXML
    private Label tfName;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancel;
    
    @FXML //수정완료버튼
    void btnAddClick(ActionEvent event) throws IOException {
    	NoticeVO_join vo = new NoticeVO_join(); 
    	vo.setNoti_title(tfTitle.getText());
    	vo.setNoti_content(tfCon.getText());
    	
    	int loginUser= MainPageController.getLoginUser().getEmp_no();
    	vo.setEmp_no(loginUser);
    	vo.setNoti_no(joinVO.getNoti_no());
    	if(tfTitle.getText().isEmpty() || tfCon.getText().isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "제목, 내용 둘 다 입력해주세요.");
    		return;
    	} 
    	
    	//서버로 정보보내기
    	try {
			service.updateNotice(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
  
  	    FXMLLoader loader = new FXMLLoader(NoticeEditController.class.getResource("/fxml/notice/NoticeDetail.fxml"));
        Parent root = loader.load();
		NoticeDetailController Ctr = loader.getController();
		Ctr.setMain(main);
		
		main.getMainborder().setCenter(root);

    	
//    	  FXMLLoader loader = new FXMLLoader(NoticeEditController.class.getResource("/fxml/notice/NoticeDetail.fxml"));
//          Parent root = loader.load();
//          Stage stage = (Stage) tfCon.getScene().getWindow();
//          Scene scene = new Scene (root);
//          stage.setScene(scene);
//          stage.setTitle("공지상세보기"); 
//          stage.show();
    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
  	    FXMLLoader loader = new FXMLLoader(NoticeEditController.class.getResource("/fxml/notice/NoticeList.fxml"));
        Parent root = loader.load();
   		NoticeListController Ctr = loader.getController();
   		Ctr.setMain(main);
   		main.getMainborder().setCenter(root);
    	  
//    	  FXMLLoader loader = new FXMLLoader(NoticeEditController.class.getResource("/fxml/notice/NoticeList.fxml"));
//          Parent root = loader.load();
//          Stage stage = (Stage) tfCon.getScene().getWindow();
//          
//          Scene scene = new Scene (root);
//          stage.setScene(scene);
//          stage.setTitle("공지등록"); 
//          stage.show();
    }
    
    
    
    
  
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
    	

    }
}
