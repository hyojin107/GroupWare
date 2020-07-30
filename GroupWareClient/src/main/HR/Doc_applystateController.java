package main.HR;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.email.GetEmailHamController;
import main.mainCtr.MainPageController;
import service.IBoardService;
import service.ICertificateService;
import vo.CertificateVO;
import vo.DepartmentsVO;

public class Doc_applystateController {

    public TableView<CertificateVO> getTbDco() {
		return tbDco;
	}

	public void setTbDco(TableView<CertificateVO> tbDco) {
		this.tbDco = tbDco;
	}
	private Doc_DetailController dolist;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDocapply; //증명서신청버튼
    
    @FXML
    private TableView<CertificateVO> tbDco;

    @FXML
    private TableColumn<?, ?> balhangnumber; //발행번호

    @FXML
    private TableColumn<?, ?> empno;//사원번호

    @FXML
    private TableColumn<?, ?> empname; //이름

    @FXML
    private TableColumn<?, ?> DocKind; //증명서종류

    @FXML
    private TableColumn<?, ?> churistate;//처리상태

    @FXML
    private Button btnDocchuri;//증명서처리버튼
   
    @FXML
    private Pagination pageDoc;

    private ICertificateService service;
    
    private List<CertificateVO> list;
    private CertificateVO item;
    private Map<String, String> map;
    
    private MainPageController main;
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }
    //증명서신청현황page
    @FXML
    void btnDocapplyclick(ActionEvent event) throws IOException {//증명서신청버튼클릭
    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/Doc_apply.fxml"));
         Parent root = loader.load();
         Doc_applyController ctr = loader.getController();
         ctr.setMain(main);
         main.getMainborder().setCenter(root);
    }
    
    @FXML
    void btnDocchuriclick(ActionEvent event) throws IOException {//증명서처리버튼클릭
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/Doc_List.fxml"));
        Parent root = loader.load();
        Doc_ListController ctr=loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
    }

    @FXML
    void tbDcoclick(MouseEvent event) {
    	if(tbDco.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	try {
			item=tbDco.getSelectionModel().getSelectedItem();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/Doc_Detail.fxml"));
	        Parent root = loader.load();
	        Doc_DetailController ctr=loader.getController();
	        ctr.setDoclist(this);
	        ctr.setCertiVO(item);
	        ctr.setMain(main);
	        
	        main.getMainborder().setCenter(root);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }
    private int rowPerPage = 15; //한페이지당 보여줄 게시글 개수	
	private int totalCount;	//총 게시글수 		
	private int pageCount; // 페이지 개수
    
	private void changeTable(int i ) {
		int start = i*rowPerPage;
    	int end = Math.min(start+rowPerPage, totalCount);//둘중에 작은놈을 채택
    	
    	Map<String, Integer> pageMap = new HashMap<String, Integer>();
    	pageMap.put("start", start);
    	pageMap.put("end",end);
    	
        tbDco.getItems().clear();
		for (int j = start; j < end; j++) {
			tbDco.getItems().add(list.get(j));

		}
		 
		
	}
	void paging() {
		totalCount = list.size();
		//총 페이지 개수 정해주기
		pageCount = (int) Math.ceil((double)totalCount / rowPerPage); 
		pageDoc.setPageCount(pageCount); 
		pageDoc.setCurrentPageIndex(0); //기본값 첫번째 페이지로 설정 
		changeTable(0);
	}
    
    @FXML
    void initialize() throws RemoteException {
    	
      	try {
    			Registry reg=LocateRegistry.getRegistry("192.168.0.117", 9999);
    			service=(ICertificateService)reg.lookup("cerService");
    		} catch (RemoteException e) {
    			e.printStackTrace();
    		}catch (NotBoundException e) {
    			e.printStackTrace();
    		}
    
		//관리자만 승인/반려버튼 보이게 
		if(MainPageController.getLoginUser().getDepartment_no()==1) {
			btnDocchuri.setVisible(true);
			list = service.getAllCertificate();
		}else list = service.getAllCertificate(MainPageController.getLoginUser().getEmp_no());
		paging();
		
		pageDoc.currentPageIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeTable(newValue.intValue());
			}
		});
		 
		balhangnumber.setCellValueFactory(new PropertyValueFactory<>("cer_no"));
		empno.setCellValueFactory(new PropertyValueFactory<>("emp_no"));
		empname.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
		DocKind.setCellValueFactory(new PropertyValueFactory<>("doc_name"));
		churistate.setCellValueFactory(new PropertyValueFactory<>("chname"));
	
      	
    }
}
