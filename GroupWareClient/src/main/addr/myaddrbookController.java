package main.addr;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import service.IAddressService;
import util.AlertUtil;
import vo.Address_BookVO;
import vo.Address_joinVO;
import vo.OutsideVO;


public class myaddrbookController {
	
	    private List<Address_BookVO> list;
	    private IAddressService service;

		private int rowPerPage = 15; //한페이지당 보여줄 게시글 개수	
		private int totalCount;	//총 게시글수 		
		private int pageCount; // 페이지 개수
		private Object oldValue;  //Select 된 이전 값을 저장하기 위한 변수
	
	private MainPageController main;
	public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}
	
	Address_joinVO adjoVO = new Address_joinVO();
	public void setaddVO(Address_joinVO adjoVO) {
		this.adjoVO=adjoVO;
		
	}

	public myaddrbookinsertController addrlist;
	
	public myaddrbookinsertController getaddrlist() {
		return addrlist;
	}

	public void setaddrlist(myaddrbookinsertController addrlist) {
		this.addrlist = addrlist;
	}
	
	
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ComboBox<?> cbGroup;

	    @FXML
	    private TableView<Address_BookVO> mytableview;

	    @FXML
	    private TableColumn<?, ?> groupname;

	    @FXML
	    private TableColumn<?, ?> empname;

	    @FXML
	    private TableColumn<?, ?> empmail;

	    @FXML
	    private TableColumn<?, ?> empphone;

	    @FXML
	    private Button btnAdd;

	    @FXML
	    private Pagination pagination;

	    @FXML
	    private Button btnDelete;

	    @FXML
	    private Button btngroup;

	    @FXML //주소록 추가화면으로 이동
	    void btnAddClick(ActionEvent event) throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addr/myaddrbookinsert.fxml"));
	        Parent root = loader.load();
	        myaddrbookinsertController ctr = loader.getController();
	        ctr.setMain(main);
	        main.getMainborder().setCenter(root);
	    }

	    @FXML //선택삭제
	    void btnDeleteClick(ActionEvent event) {
	    	int selectedIndex = mytableview.getSelectionModel().getSelectedIndex();
	        if (selectedIndex != 0) {
	            try {
					service.deleteMyAddress(selectedIndex);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
	            AlertUtil.infoMsg("삭제완료", "선택한 주소가 삭제되었습니다.");
	        } else {
	            // 아무것도 선택하지 않았다. 
	        	AlertUtil.errorMsg("선택오류", "삭제할 항목을 선택해주세요.");
	        }
	    }

	    @FXML
	    void btngroupClick(ActionEvent event) throws IOException {
	    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addr/groupEdit.fxml"));
	         Parent root = loader.load();
	         groupEditController ctr = loader.getController();
	         ctr.setMain(main);
	         main.getMainborder().setCenter(root);
	        

	    }

	    @FXML
	    void cbGroupClick(ActionEvent event) {

	    }
    
    
    
    private void changeTable(int i) {
    	int start = i * rowPerPage;
		int end = Math.min(start+rowPerPage, totalCount); //둘중에 작은놈을 채택
		
		Map<String,Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		
		mytableview.getItems().clear();
		for(int j = start; j < end; j++) { 
			mytableview.getItems().addAll(list.get(j)); 
			}	
    }
    void paging() {
    	totalCount = list.size();
		//총 페이지 개수 정해주기
		pageCount = (int) Math.ceil((double)totalCount / rowPerPage); 
		
		pagination.setPageCount(pageCount);
		
		pagination.setCurrentPageIndex(0); //기본값 첫번째 페이지로 설정 
		changeTable(0);
    }
   
    
    
    
    
    @FXML
    void initialize() throws RemoteException {
    
    	mytableview.getSelectionModel().setSelectionMode(   //행 다중 선택
    		    SelectionMode.MULTIPLE
    		);
      
    	mytableview.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
    	       Node node = event.getPickResult().getIntersectedNode();

    	       while (node != null && node != mytableview && !(node instanceof TableRow)) {
    	           node = node.getParent();
    	       }

    	       if (node instanceof TableRow) {
    	           
    	           event.consume();

    	           TableRow row = (TableRow) node;
    	           TableView tv = row.getTableView();

    	           tv.requestFocus();

    	           if (!row.isEmpty()) {
    	               
    	               int index = row.getIndex();
    	               if (row.isSelected()) {
    	                   tv.getSelectionModel().clearSelection(index);
    	               } else {
    	                   tv.getSelectionModel().select(index);
    	               }
    	           }
    	       }
    	});		
    	
 
    	
    	try {
			Registry reg=LocateRegistry.getRegistry("192.168.0.117", 9999);
			service=(IAddressService)reg.lookup("addrService");
		} catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
    
    	
    	//야메로 다 들고오는 방법 list = service.getAllBoard();
    	Map<String,Integer> pageMap = new HashMap<String, Integer>();
    	pageMap.put("start", 0);
		pageMap.put("end", 999999999);
		list = service.getoutsideAll(pageMap);
		
		paging();
		
		pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeTable(newValue.intValue());
			}
		});
		

		groupname.setCellValueFactory(new PropertyValueFactory<>("book_name"));
		empname.setCellValueFactory(new PropertyValueFactory<>("out_name"));
		empmail.setCellValueFactory(new PropertyValueFactory<>("out_email"));
		empphone.setCellValueFactory(new PropertyValueFactory<>("out_tel"));
		

    	
    	
    	
    }
}



























