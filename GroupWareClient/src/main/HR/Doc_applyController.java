package main.HR;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.email.GetEmailHamController;
import main.mainCtr.MainPageController;
import service.ICertificateService;
import service.IPaymentService;
import util.AlertUtil;
import vo.Boardemp_joinVO;
import vo.CertificateVO;
import vo.EmployeeVO;


public class Doc_applyController {
  private Doc_DetailController docdetail;
  
    public Doc_DetailController getDocdetail() {
	return docdetail;
}

public void setDocdetail(Doc_DetailController docdetail) {
	this.docdetail = docdetail;
}


	@FXML
    private ResourceBundle resources; //증명서신청하기화면

    @FXML
    private URL location;

    @FXML
    private Button btnback;

    @FXML
    private TextField tftsubmit;//제출처

    @FXML
    private ComboBox<String> combokind; //증명서종류콤보박스

    @FXML
    private Text txtdate; //입사일

    @FXML
    private ComboBox<String> comboyongdo; //용도

    @FXML
    private Button btnok; //신청버튼
    
    private ICertificateService service;
    
    private CertificateVO item;
    
    private MainPageController main;
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }
    
    @FXML
    void btnbackclick(ActionEvent event) throws IOException {//뒤로가기버튼클릭
    	  FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/Doc_applystate.fxml"));
          Parent root = loader.load();
          Doc_applystateController ctr = loader.getController();
          ctr.setMain(main);
          
          main.getMainborder().setCenter(root);
    }

    @FXML
    void btnkindclick(ActionEvent event) {//증명서종류 콤버버튼클릭
    	
    	
    }
    private EmployeeVO loginUser;
    @FXML
    void mousekindclick(MouseEvent event) {
    }

	private Map<String, Integer> map;
    @FXML
    void btnokclick(ActionEvent event) throws IOException {//신청버튼클릭
    	//docdetail.joinVO.getEmp_hire_date();
    	item = new CertificateVO();
    	//증명서 신청에서 작성하는 내용을 vo에 담기 
    	if(combokind.getValue().equals("재직증명서")) {
    		item.setDoc_no(1);
    	}else if(combokind.getValue().equals("경력증명서")) {
    		item.setDoc_no(3);
    	}
    	item.setCer_submit(tftsubmit.getText());	//제출처								
    	item.setCer_content(comboyongdo.getValue()); //용도
    	item.setEmp_no(MainPageController.getLoginUser().getEmp_no());
    	

    	if(tftsubmit.getText().isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "제출처를 입력해주세요");
    		return;
    	}
    	
    	//서버로 정보보내기
    	try {
			service.insertCerti(item);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    //정보보내고 나면 해당 객체 상세페이지전환
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/Doc_applystate.fxml"));
        Parent root = loader.load();
        Doc_applystateController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
    	
    }
    

    @FXML
    void comboyongdoclick(ActionEvent event) {//용도콤보버튼클릭
    	
    }
    @FXML
    void mouseyongdoclick(MouseEvent event) {
    }


    @FXML
    void initialize() {
    	//서버연결
    	try {
			Registry reg=LocateRegistry.getRegistry("192.168.0.117", 9999);
			service=(ICertificateService)reg.lookup("cerService");
			
			txtdate.setText(MainPageController.getLoginUser().getEmp_hire_date().substring(0,10));
			
			comboyongdo.getItems().addAll("재직확인용","은행제출용","기관제출용");
			comboyongdo.setValue(comboyongdo.getItems().get(0));
			combokind.getItems().addAll("재직증명서","경력증명서");
			combokind.setValue(combokind.getItems().get(0));
		} catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    }
}
