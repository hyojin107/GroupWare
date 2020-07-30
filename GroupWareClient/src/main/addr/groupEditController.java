package main.addr;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.mainCtr.MainPageController;
import service.IAddressService;
import util.AlertUtil;
import vo.Address_BookVO;
import vo.Salary_SpecsVO;

public class groupEditController {
    Address_BookVO vo = new Address_BookVO();
    private List<Address_BookVO> list;
    private MainPageController main;
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }
	 private IAddressService service;
	 int empNo= MainPageController.getLoginUser().getEmp_no();
	 int cnt=0;
	 
	 void backToList() throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addr/myaddrbook.fxml"));
	        Parent root = loader.load();
	        myaddrbookController ctr = loader.getController();
	        ctr.setMain(main);
	        main.getMainborder().setCenter(root);
	 }
	 
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfGroupName;

    @FXML
    private Button btnAdd;

    @FXML
    private ComboBox<String> coboGroup;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnBack;

    @FXML
    void btnAddClick(ActionEvent event) throws IOException {
//    	insert into address_book values (SEQ_BOOKNO.NEXTVAL,#book_name#,#emp_no#)
    	vo.setBook_name(tfGroupName.getText());
    	vo.setEmp_no(empNo);
    	if(tfGroupName.getText().isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "그룹명을 입력해주세요");
    		return;
    	}
    	try {
			cnt=service.addGroup(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		} if(cnt>0) {
			AlertUtil.infoMsg("등록성공", "그룹이 등록되었습니다.");
		}
		backToList();
    }	
    
    @FXML
    void btnBackClick(ActionEvent event) throws IOException {
    	backToList();
    }

    @FXML
    void btnDeleteClick(ActionEvent event) {
    	
    }

    @FXML
    void coboGroupClick(ActionEvent event) {
    	
    }


    


    @FXML
    void initialize() {
     	try {
			Registry reg=LocateRegistry.getRegistry("192.168.0.117", 9999);
			service=(IAddressService)reg.lookup("addrService");
		} catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
     	
     	coboGroup.getItems().add(list.get(0).getBook_name());
     	for(int i = 1; i<list.size(); i++) {
     		if(list.get(i).getBook_name().equals(list.get(i-1).getBook_name())) {
     			coboGroup.getItems().add(list.get(i).getBook_name());
     		}
     	}
     	
     	
     	
    }
}





























