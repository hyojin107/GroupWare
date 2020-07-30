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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.email.GetEmailHamController;
import main.mainCtr.MainPageController;
import service.INoticeService;
import util.AlertUtil;
import vo.NoticeVO_join;

public class NoticeDetailController {  
	int notiNO=0;
	private NoticeListController noti;
	
    public NoticeListController getNoti() {
		return noti;
	}
 
	public void setNoti(NoticeListController noti) {
		this.noti = noti;
	}
	
	private NoticeAddController noti2;
	
	public NoticeAddController getNoti2() {
		return noti2;
	}

	public void setNoti2(NoticeAddController noti2) {
		this.noti2 = noti2;
	} 
	
	
	
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}

	
    //List에서 선택된 게시글 상세보기위한 VO 
	NoticeVO_join joinVO = new NoticeVO_join();
	
	public void setNoticeVO(NoticeVO_join joinVO) {
		this.joinVO = joinVO; //리스트에서 선택한 게시글의 정보가 담긴 VO
		tfTitle.setText((String) joinVO.getNoti_title());	
		tDate.setText((String) joinVO.getNoti_date());
		tName.setText((String) joinVO.getEmp_name());
		tfCon.setText((String) joinVO.getNoti_content());
		notiNO=joinVO.getNoti_no();
		
    	//세션 (로그인값이랑 다르면 수정,삭제버튼안보임) 
    	int loginUser= MainPageController.getLoginUser().getEmp_no();
    	int empNO = joinVO.getEmp_no();
        if(loginUser != empNO) {
        	btnDelete.setVisible(false);
        	btnEdit.setVisible(false);
       }
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label pageTitle;

    @FXML
    private Text tDate;

    @FXML
    private Text tName;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfCon;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete; 
    
    
    void backtoList() throws IOException {

    	FXMLLoader loader = new FXMLLoader(NoticeEditController.class.getResource("/fxml/notice/NoticeList.fxml"));
        Parent root = loader.load();
		NoticeListController Ctr = loader.getController();
		Ctr.setMain(main);
		
		main.getMainborder().setCenter(root);

//    	  FXMLLoader loader = new FXMLLoader(NoticeEditController.class.getResource("/fxml/notice/NoticeList.fxml"));
//          Parent root = loader.load();
//          Stage stage = (Stage) btnBack.getScene().getWindow();
//          
//          Scene scene = new Scene (root);
//          stage.setScene(scene);
//          stage.setTitle("공지리스트");
//          stage.show();
    	
    }
    @FXML
    void btnBackClick(ActionEvent event) throws IOException {
    	backtoList();
    }
   
    @FXML
    void btnDeleteClick(ActionEvent event) throws IOException {
    	if(AlertUtil.confirm("삭제여부확인", "정말로 삭제 하시겠습니까?")) {
    	
    		int cnt = service.deleteNotice(notiNO);
    		if(cnt>0) {
    			AlertUtil.infoMsg("삭제성공", "게시글이 삭제되었습니다.");
    			backtoList();
    		}else { 
    			AlertUtil.infoMsg("삭제실패", "삭제 작업 실패");
    		}
    	
    	}
    }
      
    @FXML
    void btnEditClick(ActionEvent event) throws IOException {
    	int cnt=0;
      if(btnEdit.getText().equals("완료")) {
    	  joinVO.setNoti_title(tfTitle.getText());
    	  joinVO.setNoti_content(tfCon.getText());
    	  joinVO.setNoti_no(notiNO);
    	  //서버로 정보보내기
    	  try {
    		  cnt =service.updateNotice(joinVO);
    	  } catch (RemoteException e) {
    		  e.printStackTrace();
    	  } 
    	  if(cnt>0) {
    		  AlertUtil.infoMsg("수정완료", "수정되었습니다.");
    	  }
    	  tfTitle.setEditable(false);
    	  tfCon.setEditable(false);
    	  btnEdit.setText("수정");
    	  btnDelete.setDisable(false);
      }else {
    	  tfTitle.setEditable(true);
	  	  tfCon.setEditable(true);
	  	  btnEdit.setText("완료");
	  	  btnDelete.setDisable(true);
      }

  	
    }
    private INoticeService service;
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
