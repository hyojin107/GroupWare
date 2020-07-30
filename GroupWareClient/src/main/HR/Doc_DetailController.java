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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.mainCtr.MainPageController;
import service.ICertificateService;
import vo.CertificateVO;
import vo.EmployeeVO;

public class Doc_DetailController {//증명서 상세열람
	private Doc_applystateController doclist;
	int empNO=0;
	
  
	public Doc_applystateController getDoclist() {
		return doclist;
	}

	public void setDoclist(Doc_applystateController doclist) {
		this.doclist = doclist;
	}
	//List에서 선택된 게시글 상세보기 vo 뿌려주기 메서드
	CertificateVO joinVO = new CertificateVO();
	public void setCertiVO(CertificateVO joinVO) {
		this.joinVO=joinVO;
		txtDoc.setText(joinVO.getDoc_name());
		txtname.setText(joinVO.getEmp_name());
		txtgrade.setText(joinVO.getGrade_name());
		txtdepartment.setText(joinVO.getDepartment_name());
		String birth1=joinVO.getEmp_reg().substring(0,2);
		String birth2=joinVO.getEmp_reg().substring(2,4);
		String birth3=joinVO.getEmp_reg().substring(4,6);
		String birth = birth1+"."+birth2+"."+birth3;
		txtbirth.setText(birth);
		txtgigan.setText(joinVO.getEmp_hire_date());
		txtregno.setText(joinVO.getEmp_reg());
		txtaddr.setText(joinVO.getEmp_addr());
		
		txtyongdo.setText(joinVO.getDoc_name()+"        제출처 : "+joinVO.getCer_submit());
	
		String strs = "";
		switch(joinVO.getCer_check()) {
		case 0:
			strs = "진행중";
			break;
		case 1:
			strs = "승인";
			break;
		case 2:
			strs = "반려";
		}
		txtDocstate.setText(strs);
		empNO=joinVO.getEmp_no();
    	System.out.println(txtDocstate.getText());
    	if(txtDocstate.getText().equals("승인")) {
    		imgdojang.setVisible(true);
    	}else{
    		imgdojang.setVisible(false);
    	}
		
	} 
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text txtDoctitle;//증명서제목

    @FXML
    private Text txtDoc;

    @FXML
    private Text txtname;//이름

    @FXML
    private Text txtdepartment;//부서

    @FXML
    private Text txtgrade;//직급

    @FXML
    private Text txtbirth;//생년월일

    @FXML
    private Text txtgigan;//재직기간

    @FXML
    private Text txtregno;//주민번호

    @FXML
    private Text txtaddr;//주소

    @FXML
    private ComboBox<?> comboDocyongdo;//용도 콤보박스

    @FXML
    private Text text;

    @FXML
    private Text txtyongdo; //용도

    @FXML
    private TextField txtDocstate;//증명서 진행상태 
 
    @FXML
    private Button btnok;

    @FXML
    private Button btnnop;

    @FXML
    private Button btnback;

    @FXML
    private Button btndownload;
    
    @FXML
    private ImageView imgdojang;
   
    private ICertificateService service;
    private Doc_ListController doclist2;
 
    private CertificateVO item;
    private Map<String, Integer> map;
    
    private MainPageController main;
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }
   private boolean check;
    
    public boolean isCheck() {
	return check;
}

    //주환오빠필요한거
public void setCheck(boolean check) {
	this.check = check;
	if(joinVO.getCer_check() == 0) {
		btnnop.setVisible(true);
		btnok.setVisible(true);
		}
}

	@FXML
    void btnbackclick(ActionEvent event) throws IOException {//뒤로가기버튼클릭
    	String str = "";
    	if(check) str =  "/fxml/HR/Doc_List.fxml";
    	else str = "/fxml/HR/Doc_applystate.fxml";
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
        Parent root = loader.load();
        if(check) {
        	Doc_ListController ctr = loader.getController();
        	ctr.setMain(main);
        }else {
        	Doc_applystateController ctr = loader.getController();
        	ctr.setMain(main);
        }
        
        main.getMainborder().setCenter(root);
    	
    }
   
    @FXML
    void btndownloadclick(ActionEvent event) {//다운로드버튼클릭
    	
    }
    private EmployeeVO loginUser;
    @FXML
    void btnnopclick(ActionEvent event) throws RemoteException {//반려버튼클릭
    	Map<String, Integer> maps=new HashMap<String, Integer>();
    	//maps.put("cer_no",doclist.getTbDco().getSelectionModel().getSelectedItem().getCer_no());
    	//maps.put("emp_no", emp_no);
    	maps.put("cer_no", joinVO.getCer_no());
    	maps.put("cer_check",2);
    	//마지막 map에 담기
    	service.updateCerti(maps);
    	joinVO.setCer_check(2);
    
    	setCertiVO(joinVO);
 
    }
    
	
    @FXML
    void btnokclick(ActionEvent event) throws RemoteException {//승인버튼클릭
//    		if(btnok.getText().equals("승인")) {
//    			
//    		}
    	//if(btnok.getText(item.getChname()));
    	//map에담아서 보내기위해서 진행되는 것
    	Map<String, Integer> maps=new HashMap<String, Integer>();
    	//maps.put("emp_no",emp_no);
    	maps.put("cer_no",joinVO.getCer_no());
    	maps.put("cer_check",1);
    	
    	//마지막 map에 담기
    	service.updateCerti(maps);
    	
    	joinVO.setCer_check(1);
    	setCertiVO(joinVO);
    	
    }

    @FXML
    void comboDocyongdoclick(ActionEvent event) {//용도콤보버튼클릭

    }

    @FXML
    void txtDocstateclick(ActionEvent event) {//증명서 상태 text
    	//cer_check(진행중0/승인1/반려2)
    	txtDocstate.getText().toString();
    
    }

    @FXML
    void initialize() {
    	
    	try {
			Registry reg=LocateRegistry.getRegistry("192.168.0.117", 9999);
			service=(ICertificateService)reg.lookup("cerService");
		} catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace(); 
		}

    	
    }
}
