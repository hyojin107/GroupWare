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

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import main.mainCtr.MainPageController;
import service.IAddressService;
import service.IEmployeeService;
import vo.Address_BookVO;
import vo.Departments_joinVO;
import vo.EmployeeVO;


public class juncheaddrbookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> departsearch;
     

    @FXML
    private TableView<Departments_joinVO> tableview;


    @FXML
    private TableColumn<?, ?> departname;

    @FXML
    private TableColumn<?, ?> empname;

    @FXML
    private TableColumn<?, ?> empmail;

    @FXML
    private TableColumn<?, ?> empphone;

    @FXML
    private TableColumn<?, ?> departnum;





    @FXML
    private Pagination pagination;
    
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}


    
    
    @FXML
    void comdepartsearch(ActionEvent event)  { //departsearch 콤보박스(부서별 검색)
    	
    	int departname = 0;
		//db에 있는거 가져오기 
		if(departsearch.getValue()=="인사총무팀"){
			departname=1;
		}else if(departsearch.getValue()=="영업1팀") {
			departname=2;
		}else if(departsearch.getValue()=="영업2팀"){
			departname=3;
		}else if(departsearch.getValue()=="품질관리팀"){
			departname=4;
		}else if(departsearch.getValue()=="개발팀") {
			departname=5;
		}
		
		try {
			list = service.getDepAddress(departname);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		paging();
		
    }



    
    private IEmployeeService empservice;
    private IAddressService service;
    private List<Departments_joinVO> list;
    private Map<String, String> map;
    private Address_BookVO item; 
	private int rowPerPage = 15; //한페이지당 보여줄 게시글 개수	
	private int totalCount;	//총 게시글수 		
	private int pageCount; // 페이지 개수
	private Object oldValue;  //Select 된 이전 값을 저장하기 위한 변수
	 


	
	
	
    private void changeTable(int i) {
    	int start = i * rowPerPage;
		int end = Math.min(start+rowPerPage, totalCount); //둘중에 작은놈을 채택
		
		Map<String,Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		
		tableview.getItems().clear();
		for(int j = start; j < end; j++) { 
			tableview.getItems().add(list.get(j)); 
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
    	
    	tableview.getSelectionModel().setSelectionMode(
    		    SelectionMode.MULTIPLE
    		);
      
    	
    	
   	
    	tableview.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
    	       Node node = event.getPickResult().getIntersectedNode();

    	       while (node != null && node != tableview && !(node instanceof TableRow)) {
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
    	
    	
    	
   /* 	tableview.setOnMouseClicked(event -> {
    		if(tableview.getSelectionModel().getSelectedItem() != null) {
    			if (event.getPickResult().getIntersectedNode().equals(oldValue)) {
    				tableview.getSelectionModel().clearSelection();
    				oldValue = null;
    			} else {
    				oldValue = event.getPickResult().getIntersectedNode();
    			}
    		}
    	});
    	
    	
   */ 	
    	
    /*	ObservableList<Departments_joinVO>data = FXCollections.observableArrayList();
    	
    	tableview.setItems(data);
    	
    	TableColumn<Departments_joinVO, Boolean> checkBox = new TableColumn<Departments_joinVO, Boolean>("select");
    	checkBox.setCellValueFactory(new PropertyValueFactory<Departments_joinVO, Boolean>("select"));
    	
    	 checkBox.setCellFactory(column -> new TableCell<Departments_joinVO, Boolean>(){
             public void updateItem(Boolean select, boolean empty) {
              super.updateItem(select, empty);
              if (select == null || empty) {
               setGraphic(null);
              } else {
               CheckBox box = new CheckBox();
               BooleanProperty checked = (BooleanProperty)column.getCellObservableValue(getIndex());
               Departments_joinVO cn = (Departments_joinVO)column.getTableView().getItems().get(getIndex());
               box.setSelected(checked.get());
               box.selectedProperty().bindBidirectional(checked);
               setGraphic(box);
              }
             }
            }
              );
    	
    	tableview.getColumns().add(checkBox);
    	tableview.setEditable(true);
    
*/
    
    	
    	
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
		
		list = service.addressAll(pageMap);
		
		paging();
		
		pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeTable(newValue.intValue());
			}
		});
		
//    	select.setCellValueFactory(new PropertyValueFactory<>(""));
		departname.setCellValueFactory(new PropertyValueFactory<>("department_name"));
		empname.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
		empmail.setCellValueFactory(new PropertyValueFactory<>("emp_email"));
		empphone.setCellValueFactory(new PropertyValueFactory<>("emp_phone"));
		departnum.setCellValueFactory(new PropertyValueFactory<>("department_tel"));
		
		
		 ObservableList<String> depart = FXCollections.observableArrayList("인사총무팀","영업1팀","영업2팀","품질관리팀","개발팀");

		
		departsearch.setItems(depart);
		
		ObservableList<String> gpselect = FXCollections.observableArrayList("");
		
	
		 
		
    	
		
  
    
    
    }
  }


