package main.HR;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.ListCell;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import service.ICertificateService;
import vo.CertificateVO;
import vo.EmployeeVO;

public class Doc_ListController {

    @FXML
    private ResourceBundle resources; //증명서 list화면

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> sortbox; //진행콤보박스
  
    @FXML
    private TableView<CertificateVO> tbDcoList;
  
    @FXML
    private TableColumn<?, ?> empno;	//사원번호

    @FXML
    private TableColumn<?, ?> empname;//이름

    @FXML
    private TableColumn<?, ?> Dockind;//증명서종류

    @FXML
    private TableColumn<?, ?> requestdate; //요청일

    @FXML
    private TableColumn<?, ?> churistate;//처리상태

    @FXML
    private TableColumn<?, ?> number;//내선번호

    @FXML
    private Pagination pageDoc;
    
    @FXML
    private Button btnback;
   
   
    private ICertificateService service;
    private CertificateVO item;
    private Map<String, Integer> map;
    private List<CertificateVO> list;
	private boolean read;
    private Doc_DetailController doclist;
    
   
    public Doc_DetailController getDoclist() {
		return doclist;
	}


	public void setDoclist(Doc_DetailController doclist) {
		this.doclist = doclist;
	}
	  private MainPageController main;
	    public MainPageController getMain() {
	      return main;
	   }
	   public void setMain(MainPageController main) {
	      this.main = main;
	   }

	
	private EmployeeVO loginUser;
	
	 @FXML
	    void btnbackclick(ActionEvent event) throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/Doc_applystate.fxml"));
         Parent root = loader.load();
         Doc_applystateController ctr = loader.getController();
         ctr.setMain(main);
         
         main.getMainborder().setCenter(root);
		 
	    }
	@FXML
    void btnsortboxclick(ActionEvent event) throws RemoteException {//종류버튼클릭
		Map<String, Integer> map = new HashMap<String, Integer>();
    	
		//db에 있는거 가져오기 
		if(sortbox.getValue()=="진행중"){
			map.put("churistate",0);
		}else if(sortbox.getValue()=="승인완료") {
			map.put("churistate",1);
		}else if(sortbox.getValue()=="반려"){
			map.put("churistate",2);
		}
		
		try {
			list=service.getAllCertificate(map);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		paging();
	}
//   public void setRead(boolean read) {
//	   try {
//		this.read=read;
//		if(read) list = service.getAllCertificate(map);
//		else {
//			sortbox.setVisible(false);
//			List<CertificateVO> p=service.getAllCertificate(map);
//			list=new ArrayList<CertificateVO>();
//			int x=1;
//			System.out.println(p.toString());
//			for(CertificateVO vo : p) {
//				if(vo.getCer_check()==0) {
//					if(vo.getEmp_no()==emp_no && x !=0) {
//						CertificateVO cervo=service.getAllCertificate(vo.getCer_check());
//						list.add(cervo);
//					}
//				}
//			}
//		}
//	} catch (RemoteException e) {
//		e.printStackTrace();
//	}
//   }
	@FXML
    void btnmousesortboxclick(MouseEvent event) {
   }


    @FXML
    void tbDocListclick(MouseEvent event) {
    	//list클릭 후 상세 페이지 전환 및 증명서 및 내용 변경도되야함
    	item=tbDcoList.getSelectionModel().getSelectedItem();
    	if(item==null) return;
    	try {
			//FXMLLoader loader=new FXMLLoader(Doc_ListController.class.getResource("../fxml/Doc_Detail.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/Doc_Detail.fxml"));
			Parent root=loader.load();
			Doc_DetailController ctr=loader.getController();
			ctr.setMain(main);
			ctr.setCertiVO(item);
			ctr.setCheck(true);
			
			main.getMainborder().setCenter(root);
		
			
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    private int rowPerPage = 10; //한페이지당 보여줄 게시글 개수	
   	private int totalCount;	//총 게시글수 		
   	private int pageCount; // 페이지 개수
       
   	private void changeTable(int i ) {
   		int start = i*rowPerPage;
       	int end = Math.min(start+rowPerPage, totalCount);//둘중에 작은놈을 채택
       	
//       	Map<String, Integer> pageMap = new HashMap<String, Integer>();
//       	pageMap.put("start", start);
//       	pageMap.put("end",end);
       	
       	tbDcoList.getItems().clear();
   		for (int j = start; j < end; j++) {
   			tbDcoList.getItems().add(list.get(j));

   		}
   		 
   		
   	}
   	void paging() {
   		//if(list == null) return;
   		totalCount = list.size();
   		//총 페이지 개수 정해주기
   		pageCount = (int) Math.ceil((double)totalCount / rowPerPage); 
   		pageDoc.setPageCount(pageCount); 
   		pageDoc.setCurrentPageIndex(0); //기본값 첫번째 페이지로 설정 
   		changeTable(0);
   		
   	}
    
    
    @FXML
    void initialize() throws RemoteException {
    	
    	//서버연결
    	try {
			Registry reg=LocateRegistry.getRegistry("192.168.0.117", 9999);
			service=(ICertificateService)reg.lookup("cerService");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}

      	//야메로 다 들고오는 방법 list = service.getAllBoard();
		list = service.getAllCertificate();
		paging();
		
		pageDoc.currentPageIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeTable(newValue.intValue());
			}
		});
    	//맨뒤에vo명칭담기
		empno.setCellValueFactory(new PropertyValueFactory<>("emp_no"));
		empname.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
		Dockind.setCellValueFactory(new PropertyValueFactory<>("doc_name"));
		requestdate.setCellValueFactory(new PropertyValueFactory<>("cer_date"));
		churistate.setCellValueFactory(new PropertyValueFactory<>("chname"));
		number.setCellValueFactory(new PropertyValueFactory<>("department_tel"));
    	
		sortbox.getItems().addAll("진행중","승인완료","반려");
		
    }
}
