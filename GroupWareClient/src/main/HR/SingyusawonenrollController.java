 package main.HR;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.mainCtr.MainPageController;
import service.IEmployeeService;
import util.AlertUtil;
import vo.EmployeeVO;

public class SingyusawonenrollController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField password;

    @FXML
    private TextField name;

    @FXML
    private TextField mail;

    @FXML
    private TextField money;

    @FXML
    private TextField naesunnum;

    @FXML
    private TextField phonenum;

    @FXML
    private TextField identity;

    @FXML
    private TextField juso;

    @FXML
    private ComboBox<String> comjoblevel;

    @FXML
    private ComboBox<String> comdepartnm;

    @FXML
    private Button complete;

    @FXML
    private Button initail;

    @FXML
    private Button btnback;

    private MainPageController main;
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }

    @FXML
    void btncomplete(ActionEvent event) throws IOException {
    	EmployeeVO vo=new EmployeeVO();
    	vo.setEmp_pass(password.getText());
    	vo.setEmp_name(name.getText());
    	vo.setEmp_email(mail.getText());
    	vo.setEmp_salary(Integer.parseInt(money.getText()));
    	vo.setEmp_phone(phonenum.getText());
    	vo.setEmp_reg(identity.getText());
    	vo.setEmp_addr(juso.getText());
       
        if(comdepartnm.getValue().equals("인사총무팀")) {
        	vo.setDepartment_no(1);
    	}else if(comdepartnm.getValue().equals("영업1팀")) {
    		vo.setDepartment_no(2);
    	}else if(comdepartnm.getValue().equals("영업2팀")) {
    		vo.setDepartment_no(3);
    	}else if(comdepartnm.getValue().equals("품질관리팀")) {
    		vo.setDepartment_no(4);
    	}else if(comdepartnm.getValue().equals("개발팀")) {
    		vo.setDepartment_no(5);
    	}else {
    		vo.setDepartment_no(0);
    	}
        
       
        if(comjoblevel.getValue().equals("사원")) {
        	vo.setGrade_no(1);
        }else if(comjoblevel.getValue().equals("대리")) {
        	vo.setGrade_no(2);
        }else if(comjoblevel.getValue().equals("과장")) {
        	vo.setGrade_no(3);
        }else if(comjoblevel.getValue().equals("부장")) {
        	vo.setGrade_no(4);
        }else if(comjoblevel.getValue().equals("대표이사")) {
        	vo.setGrade_no(5);
        }
        
        service.insertEmp(vo);
        
        List<EmployeeVO> empVo = service.getAllEmp();
        AlertUtil.infoMsg("사원번호", name.getText() + "님의 사원번호는 " + empVo.get(0).getEmp_no() + "입니다.");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/imjikwonmanagement.fxml"));
        Parent root = loader.load();
        imjikwonmanagementController Ctr = loader.getController();
        Ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
        

    }

    @FXML
    void btninitial(ActionEvent event) {
    	password.clear();
    	name.clear();
    	mail.clear();
    	money.clear();
    	naesunnum.clear();
    	phonenum.clear();
    	identity.clear();
    	juso.clear();
    	
    }
    
    private IEmployeeService service;
    @FXML
    void initialize() {
    	try {
    		Registry reg=LocateRegistry.getRegistry("192.168.0.117", 9999);
    		service=(IEmployeeService)reg.lookup("empService");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	comdepartnm.setItems(FXCollections.observableArrayList("인사총무팀","영업1팀","영업2팀","품질관리팀","개발팀"));
    	comjoblevel.setItems(FXCollections.observableArrayList("대표","부장","대리","사원","과장"));

    }
}




