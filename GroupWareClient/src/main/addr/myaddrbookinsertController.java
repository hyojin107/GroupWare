 package main.addr;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import service.IAddressService;
import util.AlertUtil;
import vo.Address_joinVO;

public class myaddrbookinsertController {
	
	  @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ComboBox<?> groupname;

	    @FXML
	    private TextField name;

	    @FXML
	    private TextField mail;

	    @FXML
	    private TextField phonenum;

	    @FXML
	    private Button complete;

	    @FXML
	    private Button cancel;


	    private MainPageController main;
	    public MainPageController getMain() {
	      return main;
	   }
	   public void setMain(MainPageController main) {
	      this.main = main;
	   }
	   
		
	    

    @FXML
    void btncancel(ActionEvent event) throws IOException {  //취소버튼 눌렀을 때
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addr/myaddrbook.fxml"));
        Parent root = loader.load();
        myaddrbookController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);

    }

    private IAddressService service;
    
    Address_joinVO vo = new Address_joinVO();
    
    @FXML
    void btncomplete(ActionEvent event) throws IOException{ //완료버튼 눌렀을 때
    	
    	
    	
        if(name.getText().isEmpty()||mail.getText().isEmpty()||phonenum.getText().isEmpty()) {
        	AlertUtil.errorMsg("입력오류", "모두 입력해주세요.");
        	return;
        }
    	
    	
    	try {
	    	vo.setOut_name(name.getText());
	    	vo.setOut_email(mail.getText());
	    	vo.setOut_tel(phonenum.getText());
    		
			service.insertOutside(vo);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    
    	//정보 등록 후 나의 주소록 페이지로 가기
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addr/myaddrbook.fxml"));
        Parent root = loader.load();
        myaddrbookController ctr = loader.getController();
        ctr.setMain(main);
        main.getMainborder().setCenter(root);
    	
        ctr.setaddrlist(this);
        ctr.setaddVO(vo);	
    	
    	
    	
    }
    
    

    @FXML
    void comgroupname(ActionEvent event) { //그룹명 선택

    }

    @FXML
    void etcfield(ActionEvent event) { //기타사항

    }

    @FXML
    void joblevelfield(ActionEvent event) { //직장/직급

    }

    @FXML
    void mailfield(ActionEvent event) { //이메일

    }

    @FXML
    void namefield(ActionEvent event) { //이름

    }

    @FXML
    void phonenumfield(ActionEvent event) { //전화번호

    }

    @FXML
    void initialize() {
    	
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (IAddressService)reg.lookup("addrService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	
    	
        
    }




}





























